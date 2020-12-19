package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BOJ2800_괄호제거 {
	static String line;
	static Stack<Integer> stack = new Stack<>();
	static ArrayList<int[]> gwalho = new ArrayList<>();
	
	static Map<String, Integer> map = new HashMap<>(); // 괄호 제거한 
	static int[] result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		line = br.readLine();
		
		
		for (int i = 0; i < line.length(); i++) {
			if(line.charAt(i) == '(')
				stack.push(i);
			
			else if(line.charAt(i) == ')') {
				gwalho.add(new int[] {stack.pop(), i});
			}
		}
		
		for (int i = 1; i <= gwalho.size(); i++) {
			result = new int[i];
			comb(0, 0, gwalho.size(), i);
		}
		
		ArrayList<String> arr = new ArrayList<>();
		
		for (String key: map.keySet()) {
			arr.add(key);
		}
		
		Collections.sort(arr);
		
		for (int i = 0; i < arr.size(); i++) {
			sb.append(arr.get(i)).append("\n");
		}
		System.out.println(sb);
	}

	private static void comb(int cur, int cnt, int n, int r) {
		if(cnt == r) {

			ArrayList<Integer> excep = new ArrayList<>();
			for (int i = 0; i < r; i++) { 	
				excep.add(gwalho.get(result[i])[0]);
				excep.add(gwalho.get(result[i])[1]);
			}
			Collections.sort(excep);
			int idx = 0;
			String tmp = "";
			for (int i = 0; i < line.length(); i++) {
				if(idx < excep.size() && excep.get(idx) == i) {
					idx++;
				} else
					tmp += line.charAt(i);
			}
			map.put(tmp, 1);

			return;
		}
		
		
		for (int i = cur; i < n; i++) {
			result[cnt] = i;
			comb(i + 1, cnt + 1, n, r);
		}
	}

}
