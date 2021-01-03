/**
 * Backjoon - 9935. 문자열 폭발
 * StringBoom_9935.java
 * @date 2020-12-02
 * @author hansolKim
 * */

package p9000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class StringBoom_9935 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		String boom = br.readLine();
		
		Stack<Character> st = new Stack<>();
		
		for(int i=str.length()-1; i>=0; i--) {
			
			st.push(str.charAt(i));
			
			boolean isEquals = true;
			for(int j=0; j<boom.length(); j++) {
				if(boom.length() > st.size() || boom.charAt(j) != st.get(st.size()-1-j)) {
					isEquals = false;
					break;
				}
			}
			
			if(isEquals) {
				for(int j=0; j<boom.length(); j++) {
					st.pop();
				}
			}
			
		}
		
		StringBuilder sb = new StringBuilder();
		while(!st.isEmpty()) {
			sb.append(st.pop());
		}
		
		String answer = sb.length() == 0 ? "FRULA" : sb.toString();
		System.out.println(answer);
		br.close();

	}
}