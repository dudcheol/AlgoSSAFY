/**
 * Samsung SW Expert - 6808. 규영이와 인영이의 카드게임
 * CardGameWithKyuyoungAndInyoung_6808.java
 * @date 2020-09-07
 * @author hansolKim
 **/

package ssafy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CardGameWithKyuyoungAndInyoung_6808 {

	private static StringBuilder sb;
	private static int winCnt, loseCnt; // 이기는 경우의 수, 지는 경우의 수
	private static int[] kyu, in; // 규영이와 인영이의 카드 뭉치
	private static int[] shuffle;
	private static boolean[] used;
	private static boolean[] isSelected;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#").append(test_case).append(" ");

			// 초기화
			init();

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 9; i++) {
				int card = Integer.parseInt(st.nextToken());
				kyu[i] = card;
				used[card] = true;
			}

			int idx = 0;
			for (int i = 1; i <= 18; i++) {
				if (!used[i]) {
					in[idx++] = i;
				}
			}

			permutation(0);

			sb.append(winCnt).append(" ").append(loseCnt).append("\n");
		}

		System.out.println(sb);
	}

	private static void permutation(int cnt) {
		if (cnt == 9) {
			int result = game(kyu, shuffle);

			if (result == 1) {
				winCnt++;
			} else if (result == -1) {
				loseCnt++;
			}
			return;
		}

		for (int i = 0; i < 9; i++) {
			if (isSelected[i])
				continue;

			isSelected[i] = true;
			shuffle[cnt] = in[i];
			permutation(cnt + 1);
			isSelected[i] = false;
		}
	}

	private static void init() { // 초기화 과정
		winCnt = 0;
		loseCnt = 0;
		kyu = new int[9];
		in = new int[9];
		shuffle = new int[9];
		used = new boolean[19];
		isSelected = new boolean[9];
	}

	private static int game(int[] arrA, int[] arrB) {
		int sumA = 0;
		int sumB = 0;

		for (int i = 0; i < arrA.length; i++) {
			if (arrA[i] > arrB[i]) {
				sumA += (arrA[i] + arrB[i]);
			} else {
				sumB += (arrA[i] + arrB[i]);
			}
		}

		if (sumA > sumB) {
			return 1; // 승리
		} else if (sumA < sumB) {
			return -1; // 패배
		} else {
			return 0; // 무승부
		}
	}

}
