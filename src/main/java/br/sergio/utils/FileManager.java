package br.sergio.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public final class FileManager {
	
	private FileManager() {}
	
	public static Object readObject(File file) throws IOException, ClassNotFoundException {
		try(ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
			return inputStream.readObject();
		}
	}

	public static <T> T readObject(File file, Class<T> clazz) throws IOException, ClassNotFoundException {
		return clazz.cast(readObject(file));
	}
	
	public static void writeObject(File file, Serializable serializable) throws IOException {
		createFile(file.toPath());
		try(ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
			outputStream.writeObject(serializable);
		}
	}
	
	public static void writeText(File file, String text) throws IOException {
		writeText(file, text, false, false);
	}
	
	public static void appendText(File file, String text) throws IOException {
		appendText(file, text, false);
	}
	
	public static void appendText(File file, String text, boolean skipLine) throws IOException {
		writeText(file, text, skipLine, true);
	}
	
	private static void writeText(File file, String text, boolean skipLine, boolean append) throws IOException {
		createFile(file.toPath());
		Files.writeString(file.toPath(), (skipLine ? "\n" : "") + text, StandardOpenOption.CREATE, 
			StandardOpenOption.WRITE, append ? StandardOpenOption.APPEND : 
			StandardOpenOption.TRUNCATE_EXISTING);
	}
	
	public static void writeLines(File file, List<String> lines) throws IOException {
		writeText(file, StringUtils.toTextLines(lines));
	}
	
	public static void appendLines(File file, List<String> lines) throws IOException {
		appendText(file, StringUtils.toTextLines(lines), true);
	}
	
	public static String readLine(File file, int index) throws IOException {
		return readLines(file).get(index);
	}
	
	public static List<String> readLines(File file) throws IOException {
		return Files.readAllLines(file.toPath());
	}
	
	public static String readText(File file) throws IOException {
		return Files.readString(file.toPath());
	}
	
	public static void setLine(File file, int index, String newLine) throws IOException {
		List<String> lines = readLines(file);
		lines.set(index, newLine == null ? "" : newLine);
		writeLines(file, lines);
	}
	
	public static void removeLine(File file, int index) throws IOException {
		List<String> lines = readLines(file);
		lines.remove(index);
		writeText(file, StringUtils.toTextLines(lines));
	}
	
	public static int indexOf(File file, String line) throws IOException {
		return readLines(file).indexOf(line);
	}
	
	public static int lastIndexOf(File file, String line) throws IOException {
		return readLines(file).lastIndexOf(line);
	}
	
	public static void copyDirectory(Path source, Path target, boolean replaceIfExists) throws IOException {
		if(Files.exists(source) && !Files.isDirectory(source) || Files.exists(target) && !Files.isDirectory(target)) {
			throw new IOException("source or target is not a directory");
		}
		if(Files.exists(target)) {
			if(replaceIfExists) {
				delete(target);
				createDirectory(target);
			} else {
				return;
			}
		}
	    Files.walkFileTree(source, new SimpleFileVisitor<Path>() {

			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				Path newDir = target.resolve(source.relativize(dir));
				Files.createDirectories(newDir);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				Path newFile = target.resolve(source.relativize(file));
				copyFile(file, newFile, replaceIfExists);
				return FileVisitResult.CONTINUE;
			}

		});
	}
	
	public static void copyFile(Path source, Path target, boolean replaceIfExists) throws IOException {
		if(!Files.exists(target) || replaceIfExists) {
			Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
		}
	}
	
	public static void copyFileToDirectory(Path sourceFile, Path targetDirectory, boolean replaceIfExists) throws IOException {
		Files.createDirectories(targetDirectory);
		Files.copy(sourceFile, targetDirectory.resolve(sourceFile.getFileName()), StandardCopyOption.values());
	}
	
	public static void moveDirectory(Path source, Path target, boolean replaceIfExists) throws IOException {
		copyDirectory(source, target, replaceIfExists);
		delete(source);
	}
	
	public static void moveFile(Path source, Path target, boolean replaceIfExists) throws IOException {
		copyFile(source, target, replaceIfExists);
		delete(source);
	}
	
	public static void moveFileToDirectory(Path sourceFile, Path targetDirectory, boolean replaceIfExists) throws IOException {
		copyFileToDirectory(sourceFile, targetDirectory, replaceIfExists);
		delete(sourceFile);
	}

	public static void clearDirectory(Path dir) throws IOException {
		if(!Files.isDirectory(dir)) {
			throw new IOException("parameter must be a directory");
		}
		Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				Files.delete(file);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path subdir, IOException exc) throws IOException {
				try {
					if(!dir.equals(subdir)) {
						Files.delete(dir);
					}
				} catch(Throwable t) {
					exc.addSuppressed(t);
					throw exc;
				}
				return FileVisitResult.CONTINUE;
			}

		});
	}

	public static boolean isEmpty(Path dir) throws IOException {
		return Files.list(dir).toArray().length == 0;
	}
	
	public static void delete(Path path) throws IOException {
		if(!Files.exists(path)) {
			return;
		}
		if(!Files.isDirectory(path)) {
			Files.delete(path);
			return;
		}
		Files.walkFileTree(path, new SimpleFileVisitor<Path>() {

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				Files.delete(file);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
				try {
					Files.delete(dir);
				} catch(Throwable t) {
					exc.addSuppressed(t);
					throw exc;
				}
				return FileVisitResult.CONTINUE;
			}

		});
	}
	
	public static void createFile(Path file) throws IOException {
		if(!Files.exists(file)) {
			createDirectory(file.getParent());
			Files.createFile(file);
		}
	}

	public static void createDirectory(Path dir) throws IOException {
		if(dir != null && !Files.exists(dir)) {
			Files.createDirectories(dir);
		}
	}
	
	public static void writeBytes(Path file, byte[] bytes) throws IOException {
		writeBytes(file, bytes, false);
	}
	
	public static void appendBytes(Path file, byte[] bytes) throws IOException {
		writeBytes(file, bytes, true);
	}
	
	private static void writeBytes(Path file, byte[] bytes, boolean append) throws IOException {
		Files.write(file, bytes, StandardOpenOption.CREATE, StandardOpenOption.WRITE, 
			append ? StandardOpenOption.APPEND : StandardOpenOption.TRUNCATE_EXISTING);
	}
	
	public static byte[] readBytes(Path file) throws IOException {
		return Files.readAllBytes(file);
	}
	
	public static void renameFile(Path file, String newName) throws IOException {
		Files.move(file, file.resolveSibling(newName), StandardCopyOption.REPLACE_EXISTING);
	}
	
	public static void setHidden(Path file, boolean hidden) throws IOException {
		Files.setAttribute(file, "dos:hidden", hidden);
	}
	
}
