package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ12846_무서운아르바이트 {
	static int n, t[];
	static long ans;
	static Stack<Integer> stack = new Stack<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		t = new int[n];
		// 1. 입력받는 급여의 크기가 stack의 첫번째 수보다 작을 경우 -> stack에서 더 작은 수가 나올때까지 빼면서 급여 구해주고 갱신
		// 2. 입력받는 급여의 크기가 stack의 첫번째 수보다 클 경우 -> stack에 추가 (더 큰 넓이가 될 수 있으므로 추가)
		for (int i = 0; i < n; i++) {
			t[i] = Integer.parseInt(st.nextToken());
		}
		
		// pop된 것은 다시 들어갈 일 없음
		for (int i = 0; i < n; i++) {
			// i(오른쪽 위치)가 stack.pook()(왼쪽위치)보다 낮으면 가능한 급여를 구하면서 가장 큰 값을 찾아냄
			// i는 현재위치(오른쪽 위치), stack.peek()은 왼쪽 위치를 나타냄
			// 스택에는 크기 오름차순으로 idx가 들어감 -> 스택에 마지막에 있는 일당 기준으로 급여 계산하여 구함
			// 스택이 빌 때까지 pop 해주며 일당과 가능한 급여를 계산해서 가장 큰 급여 크기를 찾음
			while(!stack.isEmpty() && t[stack.peek()] > t[i]) { 
				long money = t[stack.pop()];
				int days = i;
				if(!stack.isEmpty()) {
					days = i - stack.peek() - 1; // 일 수
				}
				if(ans < days * money) {
					ans = days * money;
				}
				
			}
			stack.push(i); // 오른쪽으로 이동
		}
		
		while(!stack.isEmpty()) { // 마지막으로 stack에 남아있는 애들 빼면서 확인
			long money = t[stack.pop()];
			int days = n;
			if(!stack.isEmpty()) {
				days = n - stack.peek() - 1;
			}
			if(ans < days * money) {
				ans = days * money;
			}
		}
		System.out.println(ans);
	}

}
