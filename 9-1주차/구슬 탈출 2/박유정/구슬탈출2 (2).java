package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 구슬탈출3 {
	static int[] dx = { 0, 0, 1, -1 };// 오, 왼, 아,위
	static int[] dy = { 1, -1, 0, 0 };
	static int n, m;
	static int flag = -1;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		char[][] map = new char[n][m];

		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}

		dfs(map,0,-1);
		if(answer==Integer.MAX_VALUE)
			answer=-1;
		System.out.println(answer);

	}

	public static void dfs(char[][] map, int count,int pdir) {
		if (count ==10)
			return;

		label:for (int dir = 0; dir < 4; dir++) {
			if(dir==pdir)//전에 했던 방향은 반복x
				continue;
			char[][] mapc = new char[n][m];

			for (int j = 0; j < map.length; j++) {
				mapc[j] = map[j].clone();
			}
			boolean check=false;
			if (dir == 0) {// 오
			//	print(mapc);
				for (int i = 1; i < n - 1; i++) {
					for (int j = m - 2; j > 0; j--) {//오른쪽 끝부터 탐색

						if (mapc[i][j] == 'R' || mapc[i][j] == 'B') {//구슬옮기기
							char col = mapc[i][j];
							for (int k = j + 1; k < m - 1; k++) {//한칸식 오른쪽으로 이동
								if (mapc[i][k] == '#'||(mapc[i][k]!='.'&&mapc[i][k]!=col))//끝에 구슬이있거나 벽으로 막힘
									break;
								else if (mapc[i][k] == 'O') {//구멍
									if (col == 'R') {//빨간구슬
										mapc[i][k-1] = '.';//없앰
										check=true;
										break;
									} else {//파란구슬이 빠지면 끝내고 방향 바꿈
										continue label;
									}
								}
								
								mapc[i][k - 1] = '.';//원래자리는 빈칸으로 만듦
								mapc[i][k] = col;//오른쪽으로 이동
							}
						}
					}
				}
			} else if (dir == 1) {// 왼
			//	print(mapc);
				for (int i = 1; i < n - 1; i++) {
					for (int j = 1; j < m - 1; j++) {

						if (mapc[i][j] == 'R' || mapc[i][j] == 'B') {
							char col = mapc[i][j];
							for (int k = j - 1; k > 0; k--) {
								//print(mapc);
								if (mapc[i][k] == '#'||(mapc[i][k]!='.'&&mapc[i][k]!=col))
									break;
								else if (mapc[i][k] == 'O') {
									if (col == 'R') {
										mapc[i][k+1] = '.';
										check=true;
										break;
									} else {
										continue label;
									}
								}
								
								mapc[i][k + 1] = '.';
								mapc[i][k] = col;
							}
						}
					}
				}
			} else if (dir == 2) {// 아
			//	print(mapc);
				for (int j = 1; j < m - 1; j++) {
					for (int i = n - 2; i > 0; i--) {
						
						if (mapc[i][j] == 'R' || mapc[i][j] == 'B') {
							char col = mapc[i][j];
							for (int k = i + 1; k < n - 1; k++) {
								if (mapc[i][k] == '#'||(mapc[i][k]!='.'&&mapc[i][k]!=col))
									break;
								else if (mapc[k][j] == 'O') {
									if (col == 'R') {
										mapc[k-1][j] = '.';
										check=true;
										break;
									} else {
										continue label;
									}
								}
								mapc[k-1][j] = '.';
								mapc[k][j] = col;
							}
						}
					}
				}
			} else if (dir == 3) {// 위
			//	print(mapc);
				for (int j = 1; j < m - 1; j++) {
						for (int i = 1; i < n - 1; i++) {

						if (mapc[i][j] == 'R' || mapc[i][j] == 'B') {
							char col = mapc[i][j];
							
							for (int k = i - 1; k >0; k--) {
								if (mapc[i][k] == '#'||(mapc[i][k]!='.'&&mapc[i][k]!=col))
									break;
								else if (mapc[k][j] == 'O') {
									if (col == 'R') {
										mapc[k+1][j] = '.';
										check=true;
										break;
									} else {
										continue label;
									}
								}
								
								mapc[k+1][j] = '.';
								mapc[k][j] = col;
							}
						}
					}
				}
			}
			//print(mapc);
			//System.out.println(answer);
			if(check) {
				answer = Math.min(answer, count + 1);
				return;
			}
			dfs(mapc, count + 1,dir);
		}
	}

	private static void print(char[][] mapc) {
		// TODO Auto-generated method stub
		for (int i = 0; i <n; i++) {
			System.out.println(Arrays.toString(mapc[i]));
		}
		System.out.println();
	}
}
