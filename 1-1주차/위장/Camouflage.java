package com.study.first;

import java.util.HashMap;

public class Camouflage {

	public static void main(String[] args) {

		String[][] clothes = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};
        HashMap<String, Integer> map = new HashMap<String, Integer>();
 
        for (int i = 0; i < clothes.length; i++) {
            map.put(clothes[i][1], map.getOrDefault(clothes[i][1], 0) + 1);
            
        }
 
        int answer = 1;
        for (int value : map.values()) {
            answer*=(value + 1); // 아예 안 입는 경우까지 고려해서 value + 1
        }
        answer -= 1; // 아예 안 입은 경우

		System.out.println(answer);
		
	}

}
