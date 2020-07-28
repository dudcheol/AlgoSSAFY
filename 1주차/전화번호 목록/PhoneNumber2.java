package com.study.first;

import java.util.Arrays;

public class PhoneNumber2 {

	public static void main(String[] args) {
		String[] phone_book = {"119", "97674223", "1195524421"};
		
		Arrays.sort(phone_book);
		boolean answer = true;
		for(int i=0; i<phone_book.length-1;i++)
		{
			if(phone_book[i].length() <= phone_book[i + 1].length()) {
				if(phone_book[i].equals(phone_book[i + 1].substring(0, phone_book[i].length())))
				{
					answer = false;
				}  
			}			
		}
		System.out.println(answer);
    }

}

