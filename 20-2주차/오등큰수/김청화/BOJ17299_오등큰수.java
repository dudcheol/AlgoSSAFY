package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ17299_오등큰수 {
	static int N, numbers[], answer[], cnt[] = new int[1000001];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		
		numbers = new int[N];
		answer = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
			cnt[numbers[i]]++;
		}
		
		Stack<int[]> stack = new Stack<>();
		stack.push(new int[] {0, cnt[numbers[0]]});
		
		for (int i = 1; i < N; i++) {

			if(cnt[numbers[i]] > stack.peek()[1]) {
				while(!stack.isEmpty() && cnt[numbers[i]] > stack.peek()[1]) {
					answer[stack.pop()[0]] = numbers[i];
				}
			}

			stack.push(new int[] {i, cnt[numbers[i]]});
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
