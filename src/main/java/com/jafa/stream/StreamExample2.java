package com.jafa.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StreamExample2 {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("홍길동");
		list.add("길동");
		list.add("고");
		list.add("스트림생성");
		
		
		// 스트림 생성
		Stream<String> stream = list.stream();
		
		// 파라미터로 람다식 전달
		// 파라미터 타입 생략가능
		// 파라미터가 하나이면 소괄호 생략 가능
		stream.forEach( e -> {			
				System.out.println(e);
				System.out.println("길이"+e.length());
				System.out.println("=================");					
		});
		
		System.out.println("람다식");
			
		// 중괄호 내 코드가 한 줄인 경우
		// 중괄호 생략 가능 -> 세미콜론은 반드시 삭제해야함
		// stream2.forEach( e -> {system.out,prinln(e);});
		
		list.stream().forEach(e -> System.out.println(e));
		
		//메소드 참조
		System.out.println("메소드 참조");
		list.stream().forEach(System.out::println);
		}
		
	}

