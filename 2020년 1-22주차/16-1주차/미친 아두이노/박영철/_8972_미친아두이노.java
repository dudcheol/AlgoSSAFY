package week16;

import java.util.*;
import java.io.*;

// 11:30~
public class _8972_미친아두이노 {

	private static final int JONGSU = 'I';
	private static final int ROBOT = 'R';
	private static int R;
	private static int C;
	private static int[][] map;
	private static char[] cmd;
	private static int[] dr = { 0, 1, 1, 1, 0, 0, 0, -1, -1, -1 };
	private static int[] dc = { 0, -1, 0, 1, -1, 0, 1, -1, 0, 1 };
	private static int ir;
	private static int ic;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		Queue<int[]> q = new LinkedList<>();
		Queue<int[]> robots = new LinkedList<>();
		for (int i = 0; i < R; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if (line[j] == 'I') {
					ir = i;
					ic = j;
					q.offer(new int[] { i, j, JONGSU });
				} else if (line[j] == 'R') {
					map[i][j]++;
					robots.add(new int[] { i, j, ROBOT });
				}
			}
		}
		q.addAll(robots);
		cmd = br.readLine().toCharArray();

		boolean game = true;
		int order = 0;
		int move = 0;
		loop:while (!q.isEmpty()) {
			if(order == cmd.length) break;
			int size = q.size();
			while (size-- != 0) {
				int[] p = q.poll();
				int r = p[0];
				int c = p[1];
				int who = p[2];

				if (who == JONGSU) {
					int nr = r + dr[cmd[order]-'0'];
					int nc = c + dc[cmd[order]-'0'];
					move++;
					// 보드를 벗어나는 입력은 주어지지 않는다.
					if (map[nr][nc] != 0) {
						game = false;
						break loop;
					}
					ir = nr;
					ic = nc;
					q.offer(new int[] { nr, nc, JONGSU });
				} else {
					int min = Integer.MAX_VALUE;
					int mr = 0;
					int mc = 0;
					for (int d = 1; d <= 9; d++) {
						if (d == 5)
							continue;
						int nr = r + dr[d];
						int nc = c + dc[d];
						if (isOut(nr, nc))
							continue;
						int cal = Math.abs(ir - nr) + Math.abs(ic - nc);
						if (min > cal) {
							mr = nr;
							mc = nc;
							min = cal;
						}
					}
					if (mr==ir && mc==ic) {
						game = false;
						break loop;
					}
					map[r][c]--;
					map[mr][mc]++;
				}
			}
			
			// 2개 또는 그 이상의 미친 아두이노가 같은 칸에 있는 경우에는 큰 폭발이 일어나고, 그 칸에 있는 아두이노는 모두 파괴된다.
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if(map[i][j] > 1) {
						map[i][j] = 0;
					} else if(map[i][j] == 1) {
						q.offer(new int[] { i, j, ROBOT });
					}
				}
			}
			
			order++;
		}

		StringBuilder sb = new StringBuilder();
		if(game) {
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if(i==ir&&j==ic) sb.append('I');
					else sb.append(map[i][j]==0?'.':'R');
				}
				sb.append('\n');
			}
		} else {
			sb.append("kraj ").append(move); // 문제를 잘읽자!! "K"raj가 아니라 "k"raj이다!!!!
		}
		
		System.out.println(sb);
	}

	private static boolean isOut(int nr, int nc) {
		return nr < 0 || nc < 0 || nr >= R || nc >= C;
	}

}
