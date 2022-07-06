package com.jafa.stream;

import java.util.stream.IntStream;

public class IntStreamEx {
	public static void main(String[] args) {
		IntStream.range(1, 10).forEach(System.out::print);
		System.out.println("======================");
		IntStream.rangeClosed(1, 10).forEach(System.out::print);
		System.out.println("======================");
		IntStream.rangeClosed(1, 10)
				.filter(v->v%2==0)
				.forEach(System.out::print);
	}
}
