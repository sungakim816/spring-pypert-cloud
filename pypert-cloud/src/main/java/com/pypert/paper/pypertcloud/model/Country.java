package com.pypert.paper.pypertcloud.model;

import lombok.Data;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Country {
	private String countryCode;
	private String countryName;
	
	public Country() {
		
	}

}
