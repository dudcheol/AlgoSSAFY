package study2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 오등큰수 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		int[] count = new int[1000001];
		int[] answer = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			count[arr[i]]++;
		}

		Stack<Integer> stack = new Stack<Integer>();
		stack.push(arr[n - 1]);
		answer[n - 1] = -1;

		for (int i = n - 2; i >= 0; i--) {
			while (!stack.isEmpty() && count[stack.peek()] <= count[arr[i]]) {
				stack.pop();
			}
			if (stack.isEmpty()) {
				answer[i] = -1;
			} else {
				answer[i] = stack.peek();
			}
			stack.push(arr[i]);
		}

		StringBuilder sb = new StringBuilder();
		for (int m : answer) {
			sb.append(m).append(" ");
		}
		System.out.println(sb);
	}
}
