package vacation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 문자열폭발 {
	static Stack<Character> st;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String word = br.readLine();
		String bomb = br.readLine();
		st = new Stack<Character>();

		for (int index = 0; index < word.length(); index++) {
			st.push(word.charAt(index));

			if (st.peek() == bomb.charAt(bomb.length() - 1) && st.size() >= bomb.length()) {// 폭발문자열의 마지막글자와 일치
				boolean flag = true;

				for (int i = 1; i < bomb.length(); i++) {// 폭발문자열과 일치하는지 검사
					if (st.get(st.size() - i - 1) != bomb.charAt(bomb.length() - i - 1)) {// 일치하지않는다면
						flag = false;
						break;
					}
				}
				if (flag) {// 문자열일치
					for (int i = 0; i < bomb.length(); i++) {// 문자열 폭발
						st.pop();
					}
				}

			}
		}
		StringBuilder sb = new StringBuilder();
		while (!st.isEmpty()) {
			sb.append(st.pop());
		}
		//ㅡ.ㅡ............출력 시간초과 무엇
		System.out.println(sb.length() == 0 ? "FRULA" : sb.reverse());
	}
}
