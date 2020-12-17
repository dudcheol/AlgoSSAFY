/**
 * Backjoon - 2800. 괄호 제거
 * 괄호제거_2800.java
 * @date 2020-12-17
 * @author hansolKim
 **/

package p2000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class 괄호제거_2800 {

	static int N;
	static int[] order;
	static boolean[] selected;
	static ArrayList<String> answer;
	static String str;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		str = br.readLine();
		
		for(int i=0; i<str.length(); i++) {
			if(str.charAt(i) == '(') {N++;}
		}
		
		answer = new ArrayList<>();
		order = new int[N];
		selected = new boolean[N];
		sb = new StringBuilder();
		subset(0);
		Collections.sort(answer);
		for(String s : answer) { sb.append(s).append("\n"); }
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static void subset(int cnt) {
		if(cnt == N) {
			int count = 0;
			for(int i=0; i<N; i++) {
				if(selected[i]) count++;
			}
			
			if(count == 0) { return;}
			
			int idx = 0;
			StringBuilder makeStr = new StringBuilder();
			Stack<Integer> stk = new Stack<>();
			for(int i=0; i<str.length(); i++) {
				if(str.charAt(i) == '(') {
					stk.add(makeStr.length());
				} else if(str.charAt(i) == ')') {
					if(selected[idx]) {
						makeStr.replace(stk.peek(), stk.pop()+1, "");
					} else {			
						stk.pop();
						makeStr.append(")");
					}
					idx++;
					continue;
				} 
					
				makeStr.append(str.charAt(i));
			}
			
			if(!answer.contains(makeStr.toString())) {
				answer.add(makeStr.toString());
			}
			return;
		}
		
		selected[cnt] = true;
		subset(cnt+1);
		selected[cnt] = false;
		subset(cnt+1);
	}

}
