package com.jafa.stream;

import java.util.ArrayList;
import java.util.List;

import com.jafa.domain.Person;

public class StreamMap2 {
	public static void main(String[] args) {
		List<Person> personList = new ArrayList<>();
		personList.add(new Person(1L, "홍길동", 10));
		personList.add(new Person(2L, "James", 32));
		personList.add(new Person(3L, "루니", 27));
		
		double asDouble = personList.stream()
			.mapToInt(Person::getAge)
			.average().getAsDouble();
		System.out.println(asDouble);
			
	}
}
