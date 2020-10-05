/**
 * Backjoon - 12100. 2048 (Easy)
 * TwoZeroFourEight_12100.java
 * @date 2020-10-05
 * @author hansolKim
 **/

package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TwoZeroFourEight_12100 {

	static int N, answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		int[][] map = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		answer = 0;
		game(map, 0);
		System.out.println(answer);
	}

	private static void game(int[][] map, int cnt) { // map배열, 횟수

		// 기저조건
		if (cnt == 5) {
			int tempAnswer = 0;
			for (int i = 0; i < N; i++) {
				Arrays.sort(map[i]);
				if (tempAnswer < map[i][N - 1]) {
					tempAnswer = map[i][N - 1];
				}
			}

			if (answer < tempAnswer) {
				answer = tempAnswer;
			}
			return;
		}

		int[][] tempMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			tempMap[i] = map[i].clone();
		}

		// 상하좌우로 이동
		for (int i = 0; i < 4; i++) {

			// 현재 i번째 방향적용으로 블록 이동
			moveBlock(tempMap, i);
			
//			if(cnt == 3) {
//				System.out.println(i+"방향 " + cnt+"회");
//				for (int j = 0; j < N; j++) {
//					System.out.println(Arrays.toString(tempMap[j]));
//				}
//				System.out.println();
//			}

			// 변경사항 적용 후
			game(tempMap, cnt + 1);

			// 현재 i번째 방향 미적용 상태로 복귀
			for (int j = 0; j < N; j++) {
				tempMap[j] = map[j].clone();
			}
		}
	}

	private static void moveBlock(int[][] map, int dir) {

		int newIdx, targetIdx;
		switch (dir) {
		case 0: // 상
			for(int i=0; i<N; i++) {
				newIdx = 0;
				for(int j=0; j<N; j++) {
					if(map[j][i] == 0) continue; // 0인 경우
					
					int value = map[j][i];
					map[j][i] = 0;
					if(j == N-1) { // 마지막 한칸 남은 경우 ---> 비교하지말고 newIdx의 삽입
						map[newIdx][i] = value;
						continue;
					}
					
					targetIdx = j+1;
					while(targetIdx<N-1 && map[targetIdx][i] == 0) {
						targetIdx++;
					}
					
					if(value == map[targetIdx][i]) { // 두 블럭이 같은 경우 ---> 합침
						map[targetIdx][i] = 0;
						map[newIdx][i] = value * 2;
					} else { // 다른 경우
						map[newIdx][i] = value;
					}
					newIdx++;
				}
			}
			break;
		case 1: // 하
			for(int i=0; i<N; i++) {
				newIdx = N-1;
				for(int j=N-1; j>=0; j--) {
					if(map[j][i] == 0) continue; // 0인 경우
					
					int value = map[j][i];
					map[j][i] = 0;
					if(j == 0) { // 마지막 한칸 남은 경우 ---> 비교하지말고 newIdx의 삽입
						map[newIdx][i] = value;
						continue;
					}
					
					targetIdx = j-1;
					while(targetIdx>0 && map[targetIdx][i] == 0) {
						targetIdx--;
					}
					
					if(value == map[targetIdx][i]) { // 두 블럭이 같은 경우 ---> 합침
						map[targetIdx][i] = 0;
						map[newIdx][i] = value * 2;
					} else { // 다른 경우
						map[newIdx][i] = value;
					}
					newIdx--;
				}
			}
			break;
		case 2: // 좌
			for(int i=0; i<N; i++) {
				newIdx = 0;
				for(int j=0; j<N; j++) {
					if(map[i][j] == 0) continue; // 0인 경우
					
					int value = map[i][j];
					map[i][j] = 0;
					if(j == N-1) { // 마지막 한칸 남은 경우 ---> 비교하지말고 newIdx의 삽입
						map[i][newIdx] = value;
						continue;
					}
					
					targetIdx = j+1;
					while(targetIdx<N-1 && map[i][targetIdx] == 0) {
						targetIdx++;
					}
					
					if(value == map[i][targetIdx]) { // 두 블럭이 같은 경우 ---> 합침
						map[i][targetIdx] = 0;
						map[i][newIdx] = value * 2;
					} else { // 다른 경우
						map[i][newIdx] = value;
					}
					newIdx++;
				}
			}
			break;
		case 3: // 우
			for(int i=0; i<N; i++) {
				newIdx = N-1;
				for(int j=N-1; j>=0; j--) {
					if(map[i][j] == 0) continue; // 0인 경우
					
					int value = map[i][j];
					map[i][j] = 0;
					if(j == 0) { // 마지막 한칸 남은 경우 ---> 비교하지말고 newIdx의 삽입
						map[i][newIdx] = value;
						continue;
					}
					
					targetIdx = j-1;
					while(targetIdx>0 && map[i][targetIdx] == 0) {
						targetIdx--;
					}
					
					if(value == map[i][targetIdx]) { // 두 블럭이 같은 경우 ---> 합침
						map[i][targetIdx] = 0;
						map[i][newIdx] = value * 2;
					} else { // 다른 경우
						map[i][newIdx] = value;
					}
					newIdx--;
				}
			}
			break;
		}
	}
}
