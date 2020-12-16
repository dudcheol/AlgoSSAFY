package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ6189_옥상정원꾸미기 {
	static int N, numbers[];
	static long answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		numbers = new int[N];
		
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(br.readLine());
		}
		
		stack.push(numbers[0]);
		for (int i = 1; i < N; i++) {
			while(!stack.isEmpty()) {
				
				if(numbers[i] >= stack.peek()) {
					stack.pop();
				}
				else break;
				
			}

			answer += stack.size();
			stack.push(numbers[i]);
		}

		System.out.println(answer);
	}

}
