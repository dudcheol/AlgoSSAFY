/**
 * Backjoon - 17299. 오등큰수
 * 오등큰수_17299.java
 * @date 2020-12-17
 * @author hansolKim
 **/

package p17000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class 오등큰수_17299 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] input = new int[N];
		int[] cnt = new int[1000001];
		
		for(int i=0; i<N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
			cnt[input[i]]++;
		}
		
		int[] answer = new int[N];
		Stack<Integer> s = new Stack<>();
		
		StringBuilder sb = new StringBuilder();
		for(int i=N-1; i>=0; i--) {
			int cur = input[i];
			while(!s.isEmpty() && cnt[s.peek()] <= cnt[cur]) {
				s.pop();
			}
			
			if(s.isEmpty()) { answer[i] = -1; }
			else { answer[i] = s.peek(); }
			s.add(cur);
		}
		
		for(int i=0; i<N; i++) { sb.append(answer[i]).append(" "); }
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}