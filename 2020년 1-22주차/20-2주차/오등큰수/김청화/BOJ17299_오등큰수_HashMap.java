package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ17299_오등큰수_HashMap {
	static int N, numbers[], answer[];
	static Map<Integer, Integer> map = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		
		numbers = new int[N];
		answer = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
			map.put(numbers[i], map.getOrDefault(numbers[i], 0) + 1);
		}
		
		Stack<int[]> stack = new Stack<>();
		stack.push(new int[] {0, map.get(numbers[0])});
		
		for (int i = 1; i < N; i++) {
			
			if(map.get(numbers[i]) > stack.peek()[1]) {
				while(!stack.isEmpty() && map.get(numbers[i]) > stack.peek()[1]) {
					answer[stack.pop()[0]] = numbers[i];
				}
			}
			
			stack.push(new int[] {i, map.get(numbers[i])});
		}
		
		while(!stack.isEmpty()) {
			answer[stack.pop()[0]] = -1;
		}
		
		for (int i = 0; i < answer.length; i++) {
			sb.append(answer[i]).append(" ");
		}
		System.out.println(sb);
	}

}
