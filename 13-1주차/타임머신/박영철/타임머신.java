package week13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 타임머신 {

	private static int N;
	private static int M;
	private static final int INF = Integer.MAX_VALUE;
	private static int[] Dist;
	private static ArrayList<Edge> edges;

	private static class Edge {
		int from;
		int to;
		int cost;

		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		Dist = new int[N + 1]; // 1번 정점에서 출발하여 2, 3, ... , N번 정점까지의 최단거리를 기록할 배열
		Arrays.fill(Dist, INF); // "최단거리"를 기록할 것이므로 초기값으로 INF

		edges = new ArrayList<>(); // 벨만-포드 알고리즘은 "간선"중심으로 풀어나갈 수 있는 알고리즘이다!
		for (int i = 0; i < M; i++) { // 간선 입력받기
			st = new StringTokenizer(br.readLine(), " ");
			edges.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken())));
		}

		Dist[1] = 0; // 1번 정점에서 출발해서 1번 정점으로 가는 비용 = 0 이므로 초기화해준 뒤 시작한다.
		for (int i = 0; i < N - 1; i++) { // 알고리즘상 (정점의 수 - 1)회의 시도 안에 최단거리를 구할 수 있어야 한다. (왜????????????)
			for (Edge edge : edges) {
				int from = edge.from;
				int to = edge.to;
				int cost = edge.cost;

				if (Dist[from] != INF && Dist[to] > Dist[from] + cost) {
					Dist[to] = Dist[from] + cost;
				}
			}
		}

		// 음의 순환이 발생하지 않은 그래프라면, 벨만-포드 알고리즘을 적용했을 때 (정점의 수 - 1)번의 작업만으로
		// 출발 정점에서 각 정점으로까지의 최단거리가 구해진다.
		// 만약 음의 순환이 존재하는 그래프라면, (정점의 수 - 1)번보다 많은 횟수를 시도했을 때
		// 각 정점으로까지의 최단거리가 계속해서 변경될 것이다.
		// 따라서 마지막 시도(정점의수보다 많은 시도)에서 flag를 두어 음의 순환 여부를 판단할 수 있다.
		boolean isInf = false; // 현재 주어진 그래프가 음의 순환을 포함하고 있는 그래프인지 판단하기 위한 flag
		for (Edge edge : edges) {
			int from = edge.from;
			int to = edge.to;
			int cost = edge.cost;

			if (Dist[from] != INF && Dist[to] > Dist[from] + cost) {
				isInf = true;
				break;
			}
		}

		if (isInf) {
			System.out.println(-1);
		} else {
			StringBuilder sb = new StringBuilder();
			for (int i = 2; i <= N; i++) {
				sb.append(Dist[i] == INF ? -1 : Dist[i]).append('\n');
			}
			System.out.print(sb);
		}

	}

}
