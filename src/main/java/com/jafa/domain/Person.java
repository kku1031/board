package com.jafa.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {
	private Long id;
	private String name;
	private int age;
}
