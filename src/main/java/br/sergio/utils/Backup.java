package br.sergio.utils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Backup<T> implements Serializable {
    
    private Map<String, Object> fields = new HashMap<>();
    private T target;

    public Backup(T target) {
        setTarget(target);
    }

    public void record() {
        try {
            Field[] array = target.getClass().getDeclaredFields();
            for(Field field : array) {
                int mod = field.getModifiers();
                if(Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                fields.put(field.getName(), field.get(Modifier.isStatic(mod) ? null : target));
            }
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void restore() {
        try {
            Field[] array = target.getClass().getDeclaredFields();
            for(Field field : array) {
                int mod = field.getModifiers();
                if(Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                field.set(Modifier.isStatic(field.getModifiers()) ? null : target, 
                    fields.get(field.getName()));
            }
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public T getTarget() {
        return target;
    }

    public void setTarget(T target) {
        if(this.target != target) {
            fields.clear();
        }
        this.target = Objects.requireNonNull(target);
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        }
        if(o == this) {
            return true;
        }
        if(o instanceof Backup<?> backup) {
            return target.equals(backup.target) && fields.equals(backup.fields);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(target, fields);
    }

}
