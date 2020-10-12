/**
 * Backjoon - 14499. 주사위 굴리기
 * DiceRoll_14499.java
 * @date 2020-10-12
 * @author hansolKim
 **/

package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DiceRoll_14499 {

	static int[][] dice, map;
	static int N, M, K, x, y;
	static int[] command;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		/* 각 배열 생성 */
		map = new int[N][M];
		command = new int[K];
		dice = new int[4][3];

		// 지도 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 명령 입력
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			command[i] = Integer.parseInt(st.nextToken()) - 1;
		}

		StringBuilder sb = new StringBuilder();

		// 동, 서, 북, 남
		int[] dx = { 0, 0, -1, 1 };
		int[] dy = { 1, -1, 0, 0 };

		for (int i = 0; i < command.length; i++) {
			int com = command[i]; // 현재 명령어

			int nx = x + dx[com];
			int ny = y + dy[com];

			// 영역 밖으로 벗어나려는 경우 명령 무시
			if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
				continue; 
			}

			// 주사위 이동
			move(com);
			
			// 이동한 칸에 쓰여 있는 수가 0이면 ---> 주사위 바닥면에 쓰여 있는 수가 칸에 복사
			if(map[nx][ny] == 0) {
				map[nx][ny] = dice[3][1];
			} else { // 0이 아닌 경우 ---> 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사 후 칸에 쓰여 있는 수는 0이 된다.
				dice[3][1] = map[nx][ny];
				map[nx][ny] = 0;
			}
			
			// 상단에 쓰여 있는 값 저장
			sb.append(dice[1][1]).append("\n");
			x = nx;
			y = ny;
		}
		
		System.out.println(sb);
	}

	private static void move(int com) {
		int temp;
		switch (com) {
		case 0: // 동
			temp = dice[1][0]; 
			dice[1][0] = dice[1][1];
			dice[1][1] = dice[1][2];
			dice[1][2] = dice[3][1];
			dice[3][1] = temp;
			break;
		case 1: // 서
			temp = dice[1][2]; 
			dice[1][2] = dice[1][1];
			dice[1][1] = dice[1][0];
			dice[1][0] = dice[3][1];
			dice[3][1] = temp;
			break;
		case 2: // 북
			// 1번째 열 한칸씩 올리기			
			temp = dice[0][1];
			for(int i=0; i<3; i++) {
				dice[i][1] = dice[i+1][1]; 
			}
			dice[3][1] = temp;
			break;
		case 3: // 남
			// 1번째 열 한칸씩 내리기
			temp = dice[3][1];
			for(int i=3; i>0; i--) {
				dice[i][1] = dice[i-1][1]; 
			}
			dice[0][1] = temp;
			break;
		default:
			break;
		}
	}

}
