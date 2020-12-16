package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ17298_오큰수 {
	static int N, numbers[], answer[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		
		numbers = new int[N];
		answer = new int[N];
		Stack<int[]> stack = new Stack<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		stack.push(new int[] {numbers[0], 0}); //  첫 번째 값은 일단 넣음
		for (int i = 0; i < N; i++) {
			int[] cur = stack.peek();
			
			if(cur[0] < numbers[i]) {
				
				while(!stack.isEmpty()) { // 만약 numbers[i]가 더 크면 numbers[i]보다 작은 값들은 다 pop해줌
					if(stack.peek()[0] < numbers[i]) {
						answer[stack.pop()[1]] = numbers[i];
					} else {
						break;
					}
				}
			}
			
			stack.push(new int[] {numbers[i], i}); // 현재 값 stack에 넣음
		}
		
		while(!stack.isEmpty()) { // 만약 stack에 남아있는 값이 있으면 오큰수가 없는 애들임
			answer[stack.pop()[1]] = -1;
		}
		
		for (int i = 0; i < N; i++) {
			sb.append(answer[i]).append(" ");
		}
		System.out.println(sb);
	}

}
