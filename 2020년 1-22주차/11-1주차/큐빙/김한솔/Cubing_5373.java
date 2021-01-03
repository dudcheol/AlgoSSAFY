/**
 * Backjoon - 5373. 큐빙
 * Cubing_5373.java
 * @date 2020-10-13
 * @author hansolKim
 **/

package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Cubing_5373 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine()); // 테스트케이스 수

		char[][] cube = new char[12][9];

		for (int i = 0; i < 13; i++) {
			for (int j = 3; j < 6; j++) {
				if (i < 3) {
					cube[i][j] = 'o';
					continue;
				} // 오렌지
				if (i < 6) {
					cube[i][j] = 'w';
					continue;
				} // 화이트
				if (i < 9) {
					cube[i][j] = 'r';
					continue;
				} // 레드
				if (i < 12) {
					cube[i][j] = 'y';
					continue;
				} // 옐로우
			}
		}

		// 왼쪽 면 초록색
		for (int i = 3; i < 6; i++) {
			for (int j = 0; j < 3; j++) {
				cube[i][j] = 'g';
			}
		}

		// 왼쪽 면 초록색
		for (int i = 3; i < 6; i++) {
			for (int j = 6; j < 9; j++) {
				cube[i][j] = 'b';
			}
		}

		StringBuilder sb = new StringBuilder();

		for (int testcase = 1; testcase <= T; testcase++) {
			int n = Integer.parseInt(br.readLine()); // 큐브를 돌린 횟수

			// 큐브 내용 복사
			char[][] tempCube = new char[12][9];
			for (int i = 0; i < 12; i++) {
				tempCube[i] = cube[i].clone();
			}

			StringTokenizer st = new StringTokenizer(br.readLine());
			String[] command = new String[n];

			// 명령어 배열에 명령어 저장
			for (int i = 0; i < n; i++) {
				command[i] = st.nextToken();
			}

			for (int i = 0; i < n; i++) {
				move(tempCube, command[i].charAt(0), command[i].charAt(1));
			}

			// 맨 윗면 저장
			for (int i = 3; i < 6; i++) {
				for (int j = 3; j < 6; j++) {
					sb.append(tempCube[i][j]);
				}
				sb.append("\n");
			}
		}

		System.out.println(sb);
	}

	private static void move(char[][] cube, char command, char direction) {
		switch (command) {
		case 'L':
			if (direction == '-') {
				timeReverseRotate(cube, 3, 0);
				
				// 3열 3칸씩 위로
				char temp[] = {cube[0][3], cube[1][3], cube[2][3]};
				for(int i=0; i<7; i+=3) {
					for(int j=0; j<3; j++) {
						cube[i+j][3] = cube[i+j+3][3];
					}
				}
				for(int i=0; i<3; i++) {
					cube[9+i][3] = temp[i];
				}
			} else {
				timeRotate(cube, 3, 0);
				
				// 3열 3칸씩 아래로
				char temp[] = {cube[9][3], cube[10][3], cube[11][3]};
				for(int i=6; i>=0; i-=3) {
					for(int j=0; j<3; j++) {
						cube[i+j+3][3] = cube[i+j][3];
					}
				}
				for(int i=0; i<3; i++) {
					cube[i][3] = temp[i];
				}
			}
			break;
		case 'R':
			if (direction == '-') {
				timeReverseRotate(cube, 3, 6);
				// 3열 3칸씩 아래로
				char temp[] = {cube[9][5], cube[10][5], cube[11][5]};
				for(int i=6; i>=0; i-=3) {
					for(int j=0; j<3; j++) {
						cube[i+j+3][5] = cube[i+j][5];
					}
				}
				for(int i=0; i<3; i++) {
					cube[i][5] = temp[i];
				}
			} else {
				timeRotate(cube, 3, 6);
				// 3열 3칸씩 위로
				char temp[] = {cube[0][5], cube[1][5], cube[2][5]};
				for(int i=0; i<7; i+=3) {
					for(int j=0; j<3; j++) {
						cube[i+j][5] = cube[i+j+3][5];
					}
				}
				for(int i=0; i<3; i++) {
					cube[9+i][5] = temp[i];
				}
			}
			break;
		case 'B':
			if (direction == '-') {
				timeReverseRotate(cube, 0, 3);
				char temp[] = {cube[3][6], cube[3][7], cube[3][8]};
				for(int i=3; i>=0; i--) {
					for(int j=0; j<3; j++) {
						cube[3][i+j+3] = cube[3][i+j]; 
					}
				}				
				for(int i=0; i<3; i++) {
					cube[3][i] = cube[11][5-i];
				}
				for(int i=0; i<3; i++) {
					cube[11][5-i] = temp[i];
				}
			} else {
				timeRotate(cube, 0, 3);
				char temp[] = {cube[3][0], cube[3][1], cube[3][2]};
				for(int i=0; i<4; i++) {
					for(int j=0; j<3; j++) {
						cube[3][i+j] = cube[3][i+j+3]; 
					}
				}				
				for(int i=0; i<3; i++) {
					cube[3][6+i] = cube[11][5-i];
				}
				for(int i=0; i<3; i++) {
					cube[11][5-i] = temp[i];
				}
			}
			break;
		case 'F':
			if (direction == '-') {
				timeReverseRotate(cube, 6, 3);
				char temp[] = {cube[5][0], cube[5][1], cube[5][2]};
				for(int i=0; i<4; i++) {
					for(int j=0; j<3; j++) {
						cube[5][i+j] = cube[5][i+j+3]; 
					}
				}				
				for(int i=0; i<3; i++) {
					cube[5][6+i] = cube[9][5-i];
				}
				for(int i=0; i<3; i++) {
					cube[9][5-i] = temp[i];
				}
			} else {
				timeRotate(cube, 6, 3);
				char temp[] = {cube[5][6], cube[5][7], cube[5][8]};
				for(int i=3; i>=0; i--) {
					for(int j=0; j<3; j++) {
						cube[5][i+j+3] = cube[5][i+j]; 
					}
				}				
				for(int i=0; i<3; i++) {
					cube[5][i] = cube[9][5-i];
				}
				for(int i=0; i<3; i++) {
					cube[9][5-i] = temp[i];
				}
			}
			break;
		case 'U':
			if (direction == '-') {
				timeReverseRotate(cube, 3, 3);
				char temp[] = {cube[2][3], cube[2][4], cube[2][5]};
				for(int i=0; i<3; i++) { cube[2][i+3] = cube[3+i][6]; }
				for(int i=0; i<3; i++) { cube[3+i][6] = cube[6][5-i]; }
				for(int i=0; i<3; i++) { cube[6][5-i] = cube[5-i][2]; }
				for(int i=0; i<3; i++) { cube[5-i][2] = temp[i]; }
			} else {
				timeRotate(cube, 3, 3);
				char temp[] = {cube[2][3], cube[2][4], cube[2][5]};
				for(int i=0; i<3; i++) { cube[2][i+3] = cube[5-i][2]; }
				for(int i=0; i<3; i++) { cube[5-i][2] = cube[6][5-i]; }
				for(int i=0; i<3; i++) { cube[6][5-i] = cube[3+i][6]; }
				for(int i=0; i<3; i++) { cube[3+i][6] = temp[i]; }
			}
			break;
		case 'D':
			if (direction == '-') {
				timeReverseRotate(cube, 9, 3);
				char temp[] = {cube[0][3], cube[0][4], cube[0][5]};
				for(int i=0; i<3; i++) { cube[0][i+3] = cube[5-i][0]; }
				for(int i=0; i<3; i++) { cube[5-i][0] = cube[8][5-i]; }
				for(int i=0; i<3; i++) { cube[8][5-i] = cube[3+i][8]; }
				for(int i=0; i<3; i++) { cube[3+i][8] = temp[i]; }
			} else {
				timeRotate(cube, 9, 3);
				char temp[] = {cube[0][3], cube[0][4], cube[0][5]};
				for(int i=0; i<3; i++) { cube[0][i+3] = cube[3+i][8]; }
				for(int i=0; i<3; i++) { cube[3+i][8] = cube[8][5-i]; }
				for(int i=0; i<3; i++) { cube[8][5-i] = cube[5-i][0]; }
				for(int i=0; i<3; i++) { cube[5-i][0] = temp[i]; }
			}
			break;
		}
	}

	public static void timeReverseRotate(char[][] cube, int x, int y) {
		char[] temp = {cube[x][y], cube[x][y+1], cube[x][y+2]};
		for(int i=0; i<3; i++) {cube[x][y+i] = cube[x+i][y+2];}
		for(int i=0; i<3; i++) {cube[x+i][y+2] = cube[x+2][y+2-i];}
		for(int i=0; i<2; i++) {cube[x+2][y+2-i] = cube[x+2-i][y];}
		for(int i=0; i<3; i++) {cube[x+2-i][y] = temp[i]; }
	}
	
	public static void timeRotate(char[][] cube, int x, int y) {
		char[] temp = {cube[x][y], cube[x][y+1], cube[x][y+2]};
		for(int i=0; i<3; i++) {cube[x][y+2-i] = cube[x+i][y];}
		for(int i=0; i<3; i++) {cube[x+i][y] = cube[x+2][y+i];}
		for(int i=0; i<2; i++) {cube[x+2][y+i] = cube[x+2-i][y+2];}
		for(int i=0; i<3; i++) { cube[x+i][y+2] = temp[i]; }
	}
}
