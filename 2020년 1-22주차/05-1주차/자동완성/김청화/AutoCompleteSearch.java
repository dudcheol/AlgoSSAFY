package programmers;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

// 14, 19, 21, 22 통과 X
// 프로그래머스 자동완성 https://programmers.co.kr/learn/courses/30/lessons/17685
public class AutoCompleteSearch {

	//static String[] words = { "go", "gone", "guild" };
	//static String[] words = { "abc", "def", "ghi", "jklm" };
	static String[] words = { "word", "war", "warrior", "world" };
	
	public static void main(String[] args) {
		int result;
		
		result = solution(words);
		System.out.println(result);
	}
	
	public static int solution(String[] words) {
		int answer = 0; // 입력해야 할 문자수 계속 더해줌
		boolean isIn = false;
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		// 1. map에 word를 하나씩 늘려가면서 map 안에 넣어줌
		for (int i = words.length - 1; i >= 0; i--) {
			for (int j = 1; j <= words[i].length(); j++) {					
				map.put(words[i].substring(0, j), map.getOrDefault(words[i].substring(0, j), 0) + 1); // 중복 개수 세어줌
				if((map.getOrDefault(words[i].substring(0, j), 0) + 1) == 1) {
					break;
				}
			}
		}
		
		// 2. key로 탐색하는데 value 값이 1이면 word_num에 + 해 줌 
		for (int i = 0; i < words.length; i++) {
			for (int j = 1; j < words[i].length(); j++) {
				
				if (map.get(words[i].substring(0, j)) == 1) {
					answer += words[i].substring(0, j).length();
					isIn = true;
					break;
				}
			}
			if(!isIn)
				answer += words[i].length();
			isIn = false;
		}

		return answer;
	}
}
