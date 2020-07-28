package com.study.first;

import java.util.HashMap;

public class PhoneNumber {

	public static void main(String[] args) {
		String[] phone_book = {"119", "97674223", "1195524421"};

		boolean answer = true;
		HashMap<String, String> map = new HashMap<String, String>(); 
		
		for (int i = 0; i < phone_book.length; i++) {
			map.put(phone_book[i], "prefix");
		}
		
		for(String phone : phone_book) {
			for(int index = 0; index < phone.length(); index++) { // index만큼 다 하기에는 시간이 오래 걸림(Q)
				// 해시맵에 containsKey 메소드에 키값을 넘겨주면 해당 값이 HashMap에 있을 경우 true
				String sub = phone.substring(0, index);
				if(map.containsKey(sub)) { // prefix기 때문에 0 ~ index까지 잘라서 map에 있는지 
					answer = false;
					System.out.println(answer);
					break;
				}
			}				
			System.out.println(answer);
		}
	}
}

