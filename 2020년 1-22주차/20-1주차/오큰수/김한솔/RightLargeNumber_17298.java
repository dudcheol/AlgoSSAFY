/**
 * Backjoon - 17298. 오큰수
 * RightLargeNumber_17298.java
 * @date 2020-12-14
 * @author hansolKim
 **/

package p17000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class RightLargeNumber_17298 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		int[] input = new int[N];
		int[] answers = new int[N]; 
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(st.nextToken());
			input[i] = num;
		}
		
		Stack<Integer> s = new Stack<>();
		for(int i=N-1; i>=0; i--) {
			
			while(!s.isEmpty() && input[i] >= s.peek()) {
				s.pop();
			}
			
			if(!s.isEmpty()) { 
				answers[i] = s.peek();
			} else { // 스택에 값이 존재하지 않을 때 ---> 오른쪽에 큰 수가 없음을 의미
				answers[i] = -1;
			}
			
			s.add(input[i]);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			sb.append(answers[i]).append(" ");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}
