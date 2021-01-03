/**
 * Backjoon - 17281. 야구 게임
 * 야구게임_17281.java
 * @date 2020-12-26
 * @author hansolKim
 **/

package p17000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 야구게임_17281 {

	static int N, answer;
	static int[] batOrder; // 타격 순서
	static int[][] attackInfo; // 공격 정보
	static boolean[] selected, base;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // 이닝 수 입력

		attackInfo = new int[N][10]; // 공격 정보

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int j = 1; j <= 9; j++) {
				attackInfo[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		batOrder = new int[10];
		selected = new boolean[10];
		answer = 0;
		permutation(1);
		System.out.println(answer);

		br.close();
	}

	private static void permutation(int cnt) {
		if (cnt == 10 && batOrder[4] == 1) { 
			int score = 0;
			int curIdx = 1; // 1번타자부터 시작
			for (int i = 0; i < N; i++) { // N번째 이닝까지
				int outCount = 0; // 이닝당 아웃카운트 초기화
				base = new boolean[4]; // 이닝당 베이스 초기화
				
				while (outCount < 3) { // 아웃카운트가 3개가 되면 이닝 증가
					
					// 현재 타자의 기록
					int curResult = attackInfo[i][batOrder[curIdx]]; // 현재타자의 결과
					
					switch (curResult) {
					case 0: // 아웃일때
						outCount++;
						break;
					case 1: // 안타
						score += hit(1);
						break;
					case 2:
						score += hit(2);
						break;
					case 3:
						score += hit(3);
						break;
					case 4:
						score += hit(4);
						break;
					}
					if(++curIdx == 10) { curIdx = 1;}
				}

			}

			if(answer < score) { 
				answer = score; 
			}
			return;
		}

		for (int i = 1; i <= 9; i++) {
			if (selected[i])
				continue;
			batOrder[cnt] = i;
			selected[i] = true;
			permutation(cnt + 1);
			selected[i] = false;
		}
	}

	// 안타를 쳤을 때 함수
	private static int hit(int type) {
		int score = 0;
		for(int i=3; i>0; i--) {
			// 현재 루에서 type만큼 더함
			if(base[i]) { // 현재 베이스에 주자가 존재하는 경우
				base[i] = false;
				if(i+type>3) { // 홈에 들어오는 경우
					score++;
				} else {
					base[i+type] = true;
				}
			}
		}
		
		if(type>3) score++;
		else base[type] = true;
		return score;
	}

} 