import java.util.LinkedList;
import java.util.Queue;

public class 프렌즈4블록 {
	static int[] dx = { 0, 1, 1 };
	static int[] dy = { 1, 0, 1 };
	static char[][] map;
	static boolean[][] visit;
	static int m, n, count;
	static Queue<int[]> q;

	public int solution(int x, int y, String[] board) {
		map = new char[x][y];
		m = x;
		n = y;
		for (int i = 0; i < m; i++) {
			map[i] = board[i].toCharArray();
		}
		boolean flag = true;
		q = new LinkedList<int[]>();
		while (flag) {

			flag = false;
			visit = new boolean[x][y];

			for (int i = 0; i < m - 1; i++) {
				for (int j = 0; j < n - 1; j++) {
					if (visit[i][j] || map[i][j] < 'A' || map[i][j] > 'Z')
						continue;
					if (check(i, j, map[i][j])) {
						flag = true;
						q.add(new int[] { i, j });
						visit[i][j] = true;
						fill();
					}
				}
			}
			if (flag) {
				down();
			}
		}
		return count;
	}

	// 4개가 같은 것이 있는지 검사
	static boolean check(int i, int j, char c) {

		for (int a = 0; a < 3; a++) {
			int x = i + dx[a];
			int y = j + dy[a];

			if (x < 0 || y < 0 || x >= m || y >= n)// 배열 범위 벗어났으면 4개 못만듦
				return false;
			if (map[x][y] != c)// 같은 캐릭터아니면 패스
				return false;
		}

		// 캐릭터 지우기
		for (int a = 0; a < 3; a++) {
			int x = i + dx[a];
			int y = j + dy[a];
			if (visit[x][y])
				continue;
			visit[x][y] = true;

			q.add(new int[] { x, y });

			check(x, y, c);// 재귀로 주변에 4개 일치하는지 확인

		}
		return true;
	}

	static void fill() {
		while (!q.isEmpty()) {
			int x = q.peek()[0];
			int y = q.poll()[1];

			map[x][y] = '-';
		}
	}

	// 아래로 내리기
	static void down() {
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {

				if (map[i][j] == '-') {
					count++;// 지울때마다 카운트
					map[i][j] = 0; // 빈공간으로 만들기

					for (int h = i - 1; h >= 0; h--) {// 위에 있는 캐릭터 한칸씩 내리는 FOR문
						if (map[h][j] == 0)// 위에가 빈공간이면 패스
							break;
						map[h + 1][j] = map[h][j];// 한칸내려오기
						map[h][j] = 0;// 빈공간으로 채우기
					}
				}
			}
		}
	}
}
