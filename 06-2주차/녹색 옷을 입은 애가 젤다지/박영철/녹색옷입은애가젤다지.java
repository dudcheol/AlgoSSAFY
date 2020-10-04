package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 녹색옷입은애가젤다지 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String input = "";
		int[] dy = { 0, 1, 0, -1 }; // 우하좌상
		int[] dx = { 1, 0, -1, 0 }; // 우하좌상
		int test_case = 1;

		while ((input = br.readLine()) != null) {
			int N = Integer.parseInt(input);
			if (N == 0)
				break;

			int[][] map = new int[N][N];
			PriorityQueue<int[]> pq = new PriorityQueue<int[]>((o1, o2) -> {
				return Integer.compare(o1[2], o2[2]);
			});

			int[][] dist = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					dist[i][j] = Integer.MAX_VALUE;
				}
			}

			pq.offer(new int[] { 0, 0, map[0][0] });
			dist[0][0] = map[0][0]; // 첫번째 위치 초기화
			while (!pq.isEmpty()) {
				int[] cur = pq.poll();
				int y = cur[0];
				int x = cur[1];
				int cost = cur[2];

				if (dist[y][x] < cost)  // 0,0부터 y,x까지의 최단거리가 기록되어있는 dist[y][x]에 기록된 비용보다
					continue;			 // 0,0부터 현재 선택된 경로까지의 비용이 더 크다면,
										 // 그것은 최소비용이 아니게 되므로 그 경로를 건너 뜀

				for (int d = 0; d < 4; d++) {
					int ny = y + dy[d];
					int nx = x + dx[d];
					if (ny < 0 || nx < 0 || ny >= N || nx >= N) // 범위를 벗어나면 건너 뜀
						continue;

					if (dist[ny][nx] > dist[y][x] + map[ny][nx]) { // 현재 위치에서 ny, nx로 이동하는 것이 기존의 비용보다 더 저렴하다면
						// 두가지 경우로 볼 수 있음
						// 1. dist[ny][nx]가 아직 한번도 방문하지 않았던 상태
						//	- 이 상태라면, 여기에는 Integer.MAX_VALUE가 들어가 있을 것이므로 무조건 if문 안으로 들어옴
						// 2. dist[ny][nx]에 값이 들어있는 상태
						//  - 이 상태라면, 현재 경로에서 상,하,좌,우로 가는 방법으로 이동했을 때 비용이 저장되고 있는 것이므로
						//  - 가장 최소비용이 드는 곳으로 업데이트할 수 있게 됨
						dist[ny][nx] = dist[y][x] + map[ny][nx]; // 현재 위치에서 ny, nx로 이동하는 비용으로 업데이트
						pq.offer(new int[] { ny, nx, dist[ny][nx] }); // 업데이트한 정보를 큐에 넣어준다
					}
				}
			}

			sb.append("Problem ").append(test_case++).append(':').append(' ').append(dist[N-1][N-1]).append('\n');

		}

		System.out.print(sb);
	}

}
