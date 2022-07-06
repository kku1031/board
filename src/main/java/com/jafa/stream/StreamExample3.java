package com.jafa.stream;

import java.util.Arrays;
import java.util.stream.Stream;

public class StreamExample3 {

	public static void main(String[] args) {
		String[] arr = {"홍길동","고길동","나길동","김길동"};
		
		//배열을 스트림으로 생성
		Stream<String> stream = Arrays.stream(arr);
		stream.forEach(System.out::println);
	}

}
