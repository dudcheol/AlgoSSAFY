package week11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 20:00
public class _5373_큐빙 {
	static int n;
	static String[] cmd;
	static char[][][] cube;
	static char[] color = { 'w', 'y', 'r', 'o', 'g', 'b' };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());

		for (int test_case = 0; test_case < TC; test_case++) {
			n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			cmd = new String[n];
			for (int i = 0; i < n; i++)
				cmd[i] = st.nextToken();

			// 윗 면은 흰색, 아랫 면은 노란색, 앞 면은 빨간색, 뒷 면은 오렌지색, 왼쪽 면은 초록색, 오른쪽 면은 파란색
			cube = new char[6][3][3];
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					Arrays.fill(cube[i][j], color[i]);
				}
			}

			for (int c = 0; c < n; c++) {
				simulate(change(cmd[c].charAt(0)), cmd[c].charAt(1));
			}

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					sb.append(cube[0][i][j]);
				}
				sb.append('\n');
			}
		}
		System.out.print(sb);
	}

	private static int change(char turn) {
		switch (turn) {
		case 'U':
			return 0;
		case 'D':
			return 1;
		case 'F':
			return 2;
		case 'B':
			return 3;
		case 'L':
			return 4;
		case 'R':
			return 5;
		}
		return -1;
	}

	// U: 윗 면, D: 아랫 면, F: 앞 면, B: 뒷 면, L: 왼쪽 면, R: 오른쪽 면이다.
	// 두 번째 문자는 돌린 방향이다. +인 경우에는 시계 방향 (그 면을 바라봤을 때가 기준), -인 경우에는 반시계 방향이다.
	private static void simulate(int turn, char dir) {

		int[] fix = new int[2];
		int[] dirs = null;

		switch (turn) {
		case 0:
		case 1:
			fix[0] = 0;
			fix[1] = 1;
			if(turn==1) {
				dir = dir == '-' ? '+' : '-';
			}
			if (dir == '-') { // 반시계
				dirs = new int[] { 2, 4, 3, 5 };
			} else { // 시계
				dirs = new int[] { 2, 5, 3, 4 };				
			}
			break;
		case 2:
		case 3:
			fix[0] = 2;
			fix[1] = 3;
			if(turn==3) {
				dir = dir == '-' ? '+' : '-';
			}
			if (dir == '-') { // 반시계
				dirs = new int[] { 0, 5, 1, 4 };
			} else { // 시계
				dirs = new int[] { 0, 4, 1, 5 };
			}
			break;
		case 4:
		case 5:
			fix[0] = 4;
			fix[1] = 5;
			if(turn==5) {
				dir = dir == '-' ? '+' : '-';
			}
			if (dir == '-') { // 반시계
				dirs = new int[] { 2, 1, 3, 0 };				
			} else { // 시계
				dirs = new int[] { 2, 0, 3, 1 };
			}
			break;
		}

		char[] tmp = null;
		switch (turn) {
		case 0:
			tmp = cube[dirs[0]][0].clone();
			for (int d = 0; d < 3; d++) {
				cube[dirs[d]][0] = cube[dirs[d + 1]][0].clone();
			}
			cube[dirs[3]][0] = tmp;
			break;
		case 1:
			tmp = cube[dirs[0]][2].clone();
			for (int d = 0; d < 3; d++) {
				cube[dirs[d]][2] = cube[dirs[d + 1]][2].clone();
			}
			cube[dirs[3]][2] = tmp;
			break;
		case 2:
			tmp = cube[dirs[0]][2].clone();
			for (int i = 0; i < 3; i++) {
				cube[dirs[0]][2][i] = cube[dirs[1]][i][0];
			}
			for (int i = 0; i < 3; i++) {
				cube[dirs[1]][i][0] = cube[dirs[2]][2][i];
			}
			for (int i = 0; i < 3; i++) {
				cube[dirs[2]][2][i] = cube[dirs[3]][i][0];
			}
			for (int i = 0; i < 3; i++) {
				cube[dirs[3]][i][0] = tmp[i];
			}
			break;
		case 3:
			tmp = cube[dirs[0]][0].clone();
			for (int i = 0; i < 3; i++) {
				cube[dirs[0]][0][i] = cube[dirs[1]][i][2];
			}
			for (int i = 0; i < 3; i++) {
				cube[dirs[1]][i][2] = cube[dirs[2]][0][i];
			}
			for (int i = 0; i < 3; i++) {
				cube[dirs[2]][0][i] = cube[dirs[3]][i][2];
			}
			for (int i = 0; i < 3; i++) {
				cube[dirs[3]][i][2] = tmp[i];
			}
			break;
		case 4:
			tmp = new char[3];
			for (int i = 0; i < 3; i++) {
				tmp[i] = cube[dirs[0]][i][0];
			}
			for (int i = 0; i < 3; i++) {
				cube[dirs[0]][i][0] = cube[dirs[1]][i][0];
			}
			for (int i = 0; i < 3; i++) {
				cube[dirs[1]][i][0] = cube[dirs[2]][i][0];
			}
			for (int i = 0; i < 3; i++) {
				cube[dirs[2]][i][0] = cube[dirs[3]][i][0];
			}
			for (int i = 0; i < 3; i++) {
				cube[dirs[3]][i][0] = tmp[i];
			}
			break;
		case 5:
			tmp = new char[3];
			for (int i = 0; i < 3; i++) {
				tmp[i] = cube[dirs[0]][i][2];
			}
			for (int i = 0; i < 3; i++) {
				cube[dirs[0]][i][2] = cube[dirs[1]][i][2];
			}
			for (int i = 0; i < 3; i++) {
				cube[dirs[1]][i][2] = cube[dirs[2]][i][2];
			}
			for (int i = 0; i < 3; i++) {
				cube[dirs[2]][i][2] = cube[dirs[3]][i][2];
			}
			for (int i = 0; i < 3; i++) {
				cube[dirs[3]][i][2] = tmp[i];
			}
			break;
		}
		
		// 아래 -> 행 반전
		char[] tmp2 = cube[1][0].clone();
		cube[1][0] = cube[1][2];
		cube[1][2] = tmp2;
		
		// 왼쪽 -> 열 반전
		for (int i = 0; i < 3; i++) {
			char tmp3 = cube[4][i][0];
			cube[4][i][0] = cube[4][i][2];
			cube[4][i][2] = tmp3;
		} 
		
		// 뒤 -> 열 반전
		for (int i = 0; i < 3; i++) {
			char tmp3 = cube[3][i][0];
			cube[3][i][0] = cube[3][i][2];
			cube[3][i][2] = tmp3;
		} 
		// 뒤 -> 행 반전
		char[] tmp4 = cube[3][0].clone();
		cube[3][0] = cube[3][2];
		cube[3][2] = tmp4;
		
		print();
	}

	static void print() {
		String[] s = {"위", "아래", "앞", "뒤", "왼", "오른"};
		for (int i = 0; i < 6; i++) {
			System.out.println(s[i]);
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					System.out.print(cube[i][j][k]);
				}
				System.out.println();
			}
			System.out.println("---------");
		}
		System.out.println();
		System.out.println();
	}
}
