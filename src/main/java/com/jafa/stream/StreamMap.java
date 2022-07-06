package com.jafa.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.jafa.domain.Person;

public class StreamMap {
	public static void main(String[] args) {
		List<Person> personList = new ArrayList<>();
		personList.add(new Person(1L, "홍길동", 10));
		personList.add(new Person(2L, "James", 32));
		personList.add(new Person(3L, "루니", 27));
		
		//람다식에서 반환타입이 있는 경우 중괄호 생략
		// 반드시 세미콜론, return까지 생략해야함
		
		
		List<String> personName = personList.stream()
			.map(p -> p.getName() +"_2022/07/06")
			.collect(Collectors.toList());
		
		
		List<Integer> personAge = personList.stream()
			.map(Person::getAge)
			.collect(Collectors.toList());
		System.out.println(personAge);
		
		personList.stream()
			.map(Person::getAge) // [10,32,27]
			.filter(age -> age>=19).forEach(System.out::println); //[32,27]		
		
	}
}
