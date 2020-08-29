/**
 * programmers - 2018 카카오 블라인드 채용. N진수 게임
 * NdecimalNumberGame.java
 * @date 2020-08-28
 * @author hansolKim
 **/

package programmers;

import java.util.ArrayList;

public class NdecimalNumberGame {

	public static void main(String[] args) {
		NdecimalNumberGame game = new NdecimalNumberGame();
		int n = 16;
		int t = 16;
		int m = 2;
		int p = 2;
		game.solution(n, t, m, p);
	}

	public String solution(int n, int t, int m, int p) {
		StringBuilder sb = new StringBuilder();
		StringBuilder answer = new StringBuilder();
		ArrayList<Integer> list = new ArrayList<>();

		sb.append(0); // 0부터 말하기 때문에

		// 1. 1~t까지의 숫자를 n진수의 형태로 모두 만든 후 String으로 저장한다.

		if(n == 2) { // 1-1. 2진수면 binaryString함수 사용
			for (int num = 1;; num++) {
				sb.append(Integer.toBinaryString(num));
				if (sb.length() >= m * t) {
					break;
				} // t개의 숫자를 찾으려면 최소한 m*t개의 숫자는 찾아놔야 한다.
			}
		} else { // 1-2. 그외 직접 구현
			for (int num = 1;; num++) {
				int temp = num;

				// 1-2-1. n진수 만드는 방법 : 해당 숫자를 n으로 나누면서 나머지 값을 저장한다.
				while (temp > 0) {
					list.add(temp % n); // 나머지 저장
					temp /= n;
				}

				// 1-2-2. 저장된 숫자들을 역순으로 하면 n진수 형태 완성
				for (int i = list.size() - 1; i >= 0; i--) {
					if (list.get(i) >= 10) {
						sb.append((char) (55 + list.get(i))); // 'A', 'B' (16진수)형태로 저장
					} else {
						sb.append(list.get(i));
					}
				}

				// 1-2-3. 리스트 초기화
				list.clear();

				// 1-2-4. 최소한의 개수가 있는 지 확인 : 있으면 탈출
				if (sb.length() >= m * t) {
					break;
				}
			}
		}
		
		// 2. 나머지 연산을 사용해야 하므로 m명이 있는데 마지막 순서라면 0으로 변경
		if (m == p) { p = 0; }

		// 3. 순서를 돌면서 튜브차례의 숫자를 정답 스트링에 저장한다.
		for (int i = 0; i < sb.length(); i++) {
			if ((i + 1) % m == p) { // (p번째)튜브 차례인 경우
				answer.append(sb.charAt(i));
				
				if (answer.length() == t) { break; } // 추가하고나서 t개의 개수를 찾은 경우
			}
		}

		System.out.println(answer);
		return answer.toString();
	}

}
