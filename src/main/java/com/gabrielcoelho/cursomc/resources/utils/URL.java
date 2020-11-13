package com.gabrielcoelho.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

	public static List<Integer> decodeIntList(String val){
		return Arrays.asList(val.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
	}
	
	public static String decodeParam(String val) {
		try {
			return URLDecoder.decode(val, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}
