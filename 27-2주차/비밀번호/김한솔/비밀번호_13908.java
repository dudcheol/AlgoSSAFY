/**
 * Backjoon - 12865. 평범한 배낭
 * 평범한배낭_12865.java
 * @date 2021-01-27
 * @author hansolKim
 **/

package p13000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 비밀번호_13908 {
	
	static int n, m, answer;
	static int[] selected;
	static ArrayList<Integer> includes;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		includes = new ArrayList<>();
		
		if(m != 0) {
			st = new StringTokenizer(br.readLine());
		}
		
		for(int i=0; i<m; i++) {
			includes.add(Integer.parseInt(st.nextToken()));
		}
		
		answer = 0;
		selected = new int[n];
		makePassword(0);
		System.out.println(answer);
		br.close();
	}

	private static void makePassword(int cnt) {
		if(cnt == n) {
			for(int inc : includes) {
				boolean isInclude = false;
				for(int i=0; i<n; i++) {
					if(inc == selected[i]) {
						isInclude = true;
						break;
					}
				}
				if(!isInclude) {
					return;
				}
			}
			
			answer++;
			return;
		}
		
		for(int i=0; i<10; i++) {
			selected[cnt] = i;
			makePassword(cnt+1);
		}
	}
}