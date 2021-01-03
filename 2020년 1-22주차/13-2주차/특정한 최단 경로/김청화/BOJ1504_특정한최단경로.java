package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1504_특정한최단경로 {
	static int N, E, v1, v2, INF = 200000000;
	static int[][] matrix;
	static int[] distance;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		matrix = new int[N + 1][N + 1];
		distance = new int[N + 1];
		visited = new boolean[N + 1];	
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			matrix[u][v] = w;
			matrix[v][u] = w;
		}
		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());
		
		int result = 0;
		// 1 -> v1 -> v2 -> N
		result += dijkstra(1, v1);
		result += dijkstra(v1, v2);
		result += dijkstra(v2, N);
		
		int result2 = 0;
		// 1 -> v2 -> v1 -> N
		result2 += dijkstra(1, v2);
		result2 += dijkstra(v2, v1);
		result2 += dijkstra(v1, N);
		
		if(result >= INF && result2 >= INF)
			System.out.println(-1);
		else
			System.out.println(Math.min(result, result2));
	}
	
	private static int dijkstra(int start, int end) {			
		Arrays.fill(distance, INF);
		Arrays.fill(visited, false);
		distance[start] = 0; // 시작점은 0
		
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		pq.add(new Vertex(start, distance[start]));
		
		Vertex current = null;
		while(!pq.isEmpty()) {
			current = pq.poll();
			if(visited[current.no]) continue;
			
			visited[current.no] = true;
			if(current.no == end) break;
			
			for (int i = 1; i <= N; i++) {
				if(!visited[i] && matrix[current.no][i] != 0 && 
						distance[i] > current.totalDistance + matrix[current.no][i]) {
					distance[i] = current.totalDistance + matrix[current.no][i];
					pq.add(new Vertex(i, distance[i]));
				}
			}
		}
		
		return distance[end];
	}

	static class Vertex implements Comparable<Vertex> {
		int no;
		int totalDistance;
		
		Vertex(int no, int totalDistance){
			this.no = no;
			this.totalDistance = totalDistance;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.totalDistance - o.totalDistance;
		}
	}

}
