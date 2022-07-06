package com.jafa.stream;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class PathStreamEx {
	public static void main(String[] args) throws IOException {
		Path path = Paths.get("c:/storage");
		Files.list(path)
			 .forEach(p -> System.out.println(p.getFileName()));
		
		List<File> files = Files.list(path)
				.map(p->p.toFile())
				.collect(Collectors.toList());
		System.out.println(files);
	}
}
