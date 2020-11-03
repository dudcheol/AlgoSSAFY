package week14;

import java.util.*;
import java.io.*;

public class _1922_네트워크연결 {

	private static int N;
	private static int M;
	private static int[] parent;
	private static int answer;
	private static class Edge implements Comparable<Edge>{
		int from;
		int to;
		int cost;
		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 노드 수
		M = Integer.parseInt(br.readLine()); // 간선 수
		Edge[] edges = new Edge[M];
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			edges[i] = new Edge(
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken())
					);
		}
		
		Arrays.sort(edges);
		
		makeSet();
		
		int cnt = 0;
		for (int i = 0; i < M; i++) {
			if(cnt == N-1) break;
			Edge e = edges[i];
			if(!union(e.from, e.to)) continue;
			answer += e.cost;
			cnt++;
		}
		
		System.out.println(answer);
	}

	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot==bRoot) return false;
		parent[bRoot] = aRoot;
		return true;
	}

	private static int find(int x) {
		if(parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}

	private static void makeSet() {
		parent = new int[N+1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
	}

}

// 이 경우에 1-3, 2-3, 3-4, 4-5, 4-6을 연결하면 주어진 output이 나오게 된다.
// 4 + 2 + 6 + 3 + 8 = 23
