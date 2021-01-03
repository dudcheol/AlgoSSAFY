package vacation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 야구공 {
	static int inning;
	static int[][] p;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		inning = Integer.parseInt(br.readLine());
		p = new int[inning][9];

		for (int i = 0; i < inning; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				// 이닝별로 선수별 결과
				p[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		boolean[] check = new boolean[9];
		int[] players = new int[9];
		players[3] = 0;//4번타자는 1번으로 정해져있음
		check[0] = true;
		per(check, players, 0);
		System.out.println(answer);
	}

	private static void per(boolean[] check, int[] players, int count) {
		if (count == 9) {
			play(players);
			return;
		} else if (count == 3) {//4번타자는 넘어감
			per(check, players, count + 1);
		} else {

			for (int i = 1; i < 9; i++) {
				if (!check[i]) {
					check[i] = true;
					players[count] = i;
					per(check, players, count + 1);
					check[i] = false;
				}
			}
		}
	}
	//경기 시작
	private static void play(int[] players) {
		int score = 0;
		int index = 0;//선수들 순서
		
		for (int i = 0; i < inning; i++) {
			int out = 0;
			int[] ru = new int[3];//비워주는거 주의
			while (out < 3) {
				if (p[i][players[index]] == 0) {//아웃 일경우
					out++;
				} else {//아닐경우 선수들 움직임
					score = move(p[i][players[index]], score, ru);
				}
				index = (index + 1) % 9;//다음타자
			}
		}
		if (answer < score) {
			answer = score;
		}
	}

	//선수들 움직임
	private static int move(int num, int score, int[] ru) {
		for (int i = 2; i >= 0; i--) {//뒤에서 부터
			if (ru[i] == 1) {
				if (i + num >= 3) {// 홈에 도착
					score++;
				} else {
					ru[i + num] = 1;
				}
				ru[i] = 0;
			}
		}
		//현재타자 위치
		if (num == 4)
			score++;
		else
			ru[num - 1] = 1;
		
		return score;
	}

}
