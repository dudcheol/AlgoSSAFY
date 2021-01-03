/**
 * Backjoon - 6198. 옥상 정원 꾸미기
 * 옥상정원꾸미기_6198.java
 * @date 2020-12-15
 * @author hansolKim
 **/

package p6000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class 옥상정원꾸미기_6198 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] input = new int[N];
		
		for(int i=0; i<N; i++) { input[i] = Integer.parseInt(br.readLine()); }
		
		Stack<long[]> st = new Stack<>();
		long answer = 0;
		
		for(int i=N-1; i>=0; i--) {
			int num = input[i];
			if(st.isEmpty()) {
				st.add(new long[] {num, 0}); // 현재 높이와 cnt 데이터 삽입
				continue;
			}
			
			long cnt = 0;
			while(!st.isEmpty() && num > st.peek()[0]) { // 오른쪽에서 크거나 같은 부분이 나올때까지 pop
				cnt += (st.pop()[1] + 1);
			}
			
			st.add(new long[] {num, cnt});
			
			answer += cnt;
		}
		
		System.out.println(answer);
		br.close();
	}
}