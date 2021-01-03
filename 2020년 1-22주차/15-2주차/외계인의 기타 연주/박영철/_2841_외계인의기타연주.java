package week15;

import java.util.*;
import java.io.*;

public class _2841_외계인의기타연주 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		int answer = 0;

		Stack<Integer>[] s = new Stack[7];
		for (int i = 1; i <= 6; i++) {
			s[i] = new Stack<Integer>();
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());

			while (!s[n].isEmpty() && s[n].peek() > p) {
				s[n].pop();
				answer++;
			}

			if(s[n].isEmpty() || s[n].peek() < p) {
				s[n].push(p);
				answer++;
			}
		}
		
		System.out.println(answer);
	}

}
