//[., ., ., ., ., .]
//[., ., ., ., ., .]
//[., ., ., ., ., .]
//[., ., ., ., ., .]
//[., ., ., ., ., .]
//[., ., ., ., ., .]
//[., ., ., ., ., .]
//[., ., ., ., ., .]
//[., ., ., ., ., .]
//[., ., ., ., ., .]
//[., B, ., ., ., .]
//[., B, B, ., ., .]
//이경우 시간초과
package study2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
public class PUYOPUYO {
	static char[][] map = new char[12][6];
	static int[][] d = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 12; i++) {
			map[i] = br.readLine().toCharArray();
		}
		int stage = 0;
		while (true) {
			boolean flag = false;
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if (map[i][j] != '.') {
						boolean[][] visit = new boolean[12][6];
						visit[i][j] = true;
						count = 1;
						if(bfs(visit, i, j, map[i][j])) {//안해주면 시간초과...터졌는지 체크
							flag=true;
						}
					}
				}
			}
			if (!flag)
				break;
			stage++;
			down();//다 터트렸으면 내려주기
		}
		System.out.println(stage);
	}


	private static boolean bfs(boolean[][] visit, int i, int j, char a) {
		Queue<int[]> q = new LinkedList<int[]>();
		Queue<int[]> dot = new LinkedList<int[]>();
		map[i][j]='.';
		q.add(new int[] { i, j });
		dot.add(new int[] { i, j });//좌표저장

		while (!q.isEmpty()) {
			int[] arr = q.poll();
			int m = arr[0];
			int n = arr[1];

			for (int k = 0; k < 4; k++) {
				int x = m + d[k][0];
				int y = n + d[k][1];

				if (x < 0 || y < 0 || x >= 12 || y >= 6 || visit[x][y] || map[x][y] != a)
					continue;
				visit[x][y] = true;
				count++;
				map[x][y] = '.';
				q.add(new int[] { x, y });
				dot.add(new int[] { x, y });
			}
		}
		if (count >= 4)
			return true;
		
		while (!dot.isEmpty()) {//안터지면 다시 되돌리기
			int[] arr = dot.poll();
			int m = arr[0];
			int n = arr[1];
			map[m][n] = a;

		}
		return false;

	}

	private static void down() {//내려주기
		int y = 0;
		for (int j = 0; j < 6; j++) {
			boolean flag = false;
			for (int i = 11; i >= 0; i--) {
				if (!flag && map[i][j] == '.') {//처음빈공간 좌표저장
					flag = true;
					y = i;
				} else if (flag && map[i][j] != '.') {//밑에 빈공간이 있는데 위에 뿌요가 있다
					map[y][j] = map[i][j];
					map[i][j] = '.';
					y--;
				}
			}
		}
	}
}
