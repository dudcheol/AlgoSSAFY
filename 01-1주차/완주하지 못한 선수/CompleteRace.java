package com.study.first;
import java.util.*;

public class CompleteRace {

	public static void main(String[] args) {
		String[] participant = {"mislav", "stanko", "mislav", "ana"};
		String[] completion = {"stanko", "ana", "mislav"};
		
		HashMap<String, Integer> map = new HashMap<>(); // 해시맵 생성
        for (String element : participant) map.put(element, map.getOrDefault(element, 0) + 1);
        for (String element : completion) map.put(element, map.getOrDefault(element, 0) - 1);
        // getOrDefault -> 존재하면 default값으로 넣어주고 

        for (String key : map.keySet()) {
            if (map.get(key) >= 1) {
                System.out.println(key);
            }
        }
    }
}

