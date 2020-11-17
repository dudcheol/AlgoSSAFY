package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

// Map 사용
public class BOJ1713_후보추천하기Map {
	static int N, C;
	static Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
	static Map<Integer, Integer> IdxMap = new HashMap<Integer, Integer>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder string = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		C = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		boolean flag;
		for (int i = 0; i < C; i++) {	
			int num = Integer.parseInt(st.nextToken());
			
			if(countMap.containsKey(num)) {
				countMap.put(num, countMap.get(num) + 1);
			} else {
				if(countMap.size() >= N) {
					int min = Integer.MAX_VALUE;
					int minidx = Integer.MAX_VALUE;
					int minkey = 0;
					
					for (Integer k : countMap.keySet()) { // 가장 작은 값 찾아내기
						if(min > countMap.get(k)) {
							min = countMap.get(k);
							minkey = k;
						}
					}
					
					for (Integer k : IdxMap.keySet()) { // 작은 값 중에서 idx가 더 작은 값이 있다면 그게 빼야할 idx
						if(min == countMap.get(k)) {
							if(minidx > IdxMap.get(k)) {
								minidx = IdxMap.get(k);
								minkey = k;
							}
						}
					}
					countMap.remove(minkey);
					IdxMap.remove(minkey);
				}
				
				countMap.put(num, 1);
				IdxMap.put(num, i);
			}
		}
		
		List<Integer> list = new ArrayList<>(IdxMap.keySet());
		Collections.sort(list);
		for (int i = 0; i < list.size(); i++) {
			string.append(list.get(i)).append(" ");
		}
		
		System.out.println(string);
	}
}

