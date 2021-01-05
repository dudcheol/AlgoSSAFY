/**
 * Backjoon - 1662. 압축
 * 압축_1662.java
 * @date 2021-01-05
 * @author hansolKim
 **/

package p1000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class 압축_1662 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		
		Stack<Integer> s = new Stack<>();
		
		for(int i=0; i<str.length(); i++) {
			char ch = str.charAt(i);
			
			// 숫자인 경우
			if(ch-'0'>=0 && ch-'0'<=9) {
				// 여는괄호 앞에 붙은 숫자인지 확인
				if(i+1<str.length() && str.charAt(i+1) == '(') {
					s.add(ch-'0');
					s.add(-1);
					i++;
				} else {
					s.add(1);
				}
			} else { // 닫는 괄호의 경우
				// 여는 괄호가 있는 부분까지 모두 더함
				int num = 0;
				while(s.peek() >= 0) {
					num += s.pop();
				}
				
				// 여는괄호 삭제
				s.pop();
				
				// K(Q)구조에서 Q가 num, 현재 pop한 값이 K
				num *= s.pop();
				// 스택에 삽입
				s.add(num);
			}
		}
		
		int answer = 0;
		while(!s.isEmpty()) {
			answer += s.pop();
		}
		System.out.println(answer);
		br.close();
	}
}