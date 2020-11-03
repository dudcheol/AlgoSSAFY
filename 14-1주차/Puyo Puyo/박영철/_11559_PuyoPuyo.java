package week14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class _11559_PuyoPuyo {

	static char[][] map;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	private static boolean[][] visited;
	private static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new char[12][];
		for (int i = 0; i < 12; i++) map[i] = br.readLine().toCharArray();
		
		// 뿌요 찾기
		boolean change = true;
		while (change) {
			change = false;
			for (int i = 0; i < 12; i++) 
				for (int j = 0; j < 6; j++) 
					if (map[i][j] != '.' && bfs(i, j, map[i][j])) 
						change = true;
			
			// 터질 수 있는 뿌요가 여러 그룹이 있다면 동시에 터져야 하고 여러 그룹이 터지더라도 한번의 연쇄가 추가된다.
			if(change) answer++;
			fillblank();
		}

		System.out.println(answer);
	}


	private static boolean bfs(int y, int x, char c) {

		Queue<int[]> q = new LinkedList<>();
		visited = new boolean[12][6];
		q.offer(new int[] { y, x });
		int cnt = 1;
		
		while (!q.isEmpty()) {
			int[] p = q.poll();
			int py = p[0];
			int px = p[1];

			visited[py][px] = true;
			for (int d = 0; d < 4; d++) {
				int ny = py + dy[d];
				int nx = px + dx[d];
				if (ny < 0 || nx < 0 || ny >= 12 || nx >= 6 || visited[ny][nx] || c != map[ny][nx])
					continue;
				q.offer(new int[] { ny, nx });
				cnt++;
			}
		}

		if (cnt < 4) return false;
//		answer++; << 동시에 터질 때마다 answer의 갯수를 증가시키므로 오답

		// 방문된 곳 비우기 처리
		for (int i = 0; i < 12; i++) 
			for (int j = 0; j < 6; j++) 
				if (visited[i][j]) map[i][j] = '.';

		return true;
	}

	private static void fillblank() {
		// 빈 칸 메꾸기
		for (int i = 11; i >= 0; i--) {
			loop: for (int j = 0; j < 6; j++) {
				if (map[i][j] == '.') {
					int ny = i;
					while (true) {
						ny += dy[0];
						if (ny < 0)
							continue loop;
						if (map[ny][j] != '.')
							break;
					}
					map[i][j] = map[ny][j];
					map[ny][j] = '.';
				}
			}
		}
	}
}
