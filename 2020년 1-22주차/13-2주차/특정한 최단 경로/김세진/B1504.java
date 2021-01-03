package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1504 {
	static long[] Node;
	static int[][] map;
	static int INF, N, E;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stz = new StringTokenizer(br.readLine());

		N = Integer.parseInt(stz.nextToken());
		E = Integer.parseInt(stz.nextToken());

		Node = new long[N + 1];
		map = new int[N+1][N+1];
		INF = Integer.MAX_VALUE;
		visited = new boolean[N + 1];

		for (int i = 0; i < E; i++) {
			stz = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(stz.nextToken());
			int end = Integer.parseInt(stz.nextToken());
			int cost = Integer.parseInt(stz.nextToken());

			map[start][end] = cost;
			map[end][start] = cost;
		}

		stz = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(stz.nextToken());
		int v2 = Integer.parseInt(stz.nextToken());

		long result1 = 0, result2 = 0;

		result1 += dijkstra(1, v1);
		result1 += dijkstra(v1, v2);
		result1 += dijkstra(v2, N);

		result2 += dijkstra(1, v2);
		result2 += dijkstra(v2, v1);
		result2 += dijkstra(v1, N);

		if (result1 >= INF || result2 >= INF || result1<0 || result2<0) {
			System.out.println(-1);
		} else {
			System.out.println(Math.min(result1, result2));
		}

	}

	static long dijkstra(int start, int end) {
		for (int i = 0; i <= N; i++) {
			Node[i] = INF;
		}
		visited=new boolean[N+1];
		Node[start] = 0;
		int current=0;
		for (int i = 1; i <= N; i++) {
			long min=INF;
			for (int j = 1; j <= N; j++) {
				if(!visited[j] && min>Node[j]) {
					min=Node[j];
					current=j;
				}
			}
			visited[current]=true;
			if(current==end) {
				return Node[end];
			}
			
			
			for(int j=0;j<=N;j++) {
				if(!visited[j] && map[current][j]!=0 && Node[j]>min+map[current][j] ) {
					Node[j]=min+map[current][j];
				}
			}
		}
		
		return -1;
	}

}
