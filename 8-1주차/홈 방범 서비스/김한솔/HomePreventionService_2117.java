/**
 * Samsung SW Expert - 2117. 홈 방범 서비스
 * HomePreventionService_2117.java
 * @date 2020-09-10
 * @author hansolKim
 * */

package ssafy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class HomePreventionService_2117 {

	private static int N, M, max; // 도시의 크기, 집에서 지불하는 비용
	private static int[][] map;
	private static StringBuilder sb;
	private static int[] dx = {-1,1,0,0};
	private static int[] dy = {0,0,1,-1};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#").append(test_case).append(" ");

			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			max = 0;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					bfs(i, j);
				}
			}
			sb.append(max).append("\n");
		}

		System.out.println(sb);

	}

	private static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<int[]>();
		boolean[][] visited = new boolean[N][N];
		
		visited[x][y] = true;
		q.offer(new int[] {x, y});
		
		int cnt = map[x][y]; /// 집 개수 누적
		while(!q.isEmpty()) {
			for(int K=1; K<2*N; K++) {
				int price = M*cnt-((K*K) + (K-1)*(K-1));
				if(price >=0) max = Math.max(max, cnt);
				int size = q.size();
				for(int s=0; s<size; s++) {
					int[] xy = q.poll();
					for(int i=0; i<4; i++) {
						int nx = xy[0]+dx[i];
						int ny = xy[1]+dy[i];
						
						if(nx<0 || nx>=N || ny<0 || ny>=N || visited[nx][ny]) continue;
						
						visited[nx][ny] = true;
						q.offer(new int[] {nx, ny});
						if(map[nx][ny] == 1) cnt++;
					}
				}
			}
		}
	}
}
