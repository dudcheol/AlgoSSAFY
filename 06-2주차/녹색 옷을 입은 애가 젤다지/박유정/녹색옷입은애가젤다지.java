import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 녹색옷입은애가젤다지 {
	static int N;
	static int[][] map,value;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int problem = 1;

		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;

			map = new int[N][N];
			value = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				Arrays.fill(value[i],Integer.MAX_VALUE);
			}
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			PriorityQueue<Pairs> q = new PriorityQueue<Pairs>();
			q.add(new Pairs(0, 0, map[0][0]));
			value[0][0]=map[0][0];
			
			int answer = 0;

			while (!q.isEmpty()) {
				Pairs p = q.poll();
				int x = p.x;
				int y = p.y;
				int cnt = p.cnt;
				
				
				if (x == N - 1 && y == N - 1) {
					answer = cnt;
					break;
				}
				
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];

					if (nx < 0 || ny < 0 || nx >= N || ny >= N )
						continue;
					
					if(cnt+map[nx][ny]<value[nx][ny]) {
						q.add(new Pairs(nx, ny, cnt + map[nx][ny]));
						value[nx][ny]=cnt+map[nx][ny];
					}
				}
			}

			System.out.println("Problem " + problem++ + ": " + answer);
		}
	}
}

class Pairs implements Comparable<Pairs> {
	int x;
	int y;
	int cnt;

	public Pairs(int x, int y, int cnt) {
		super();
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}

	@Override
	public int compareTo(Pairs o) {
		return this.cnt - o.cnt;
	}
}
