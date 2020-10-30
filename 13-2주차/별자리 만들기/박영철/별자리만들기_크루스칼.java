package week13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 별자리만들기_크루스칼 {

	private static int N;
	private static int[] set;

	private static class Edge implements Comparable<Edge>{
		int from;
		int to;
		double cost;

		public Edge(int from, int to, double cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			if(this.cost > o.cost) {
				return 1;
			} else if(this.cost < o.cost) {
				return -1;
			} else return 0;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		double[][] stars = new double[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			stars[i][0] = Double.parseDouble(st.nextToken());
			stars[i][1] = Double.parseDouble(st.nextToken());
		}

		ArrayList<Edge> edges = new ArrayList<Edge>();
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				edges.add(new Edge(i, j,
						Math.sqrt(Math.pow(stars[i][0] - stars[j][0], 2) + Math.pow(stars[i][1] - stars[j][1], 2))));
			}
		}
		
		Collections.sort(edges);
		
		// make-set
		set = new int[N];
		for (int i = 0; i < N; i++) {
			set[i] = i;
		}
		
		int cnt = 0;
		double answer = 0;
		for (int i = 0; i < edges.size(); i++) {
			if(cnt == N-1) break;
			
			Edge cur = edges.get(i);
			int from = cur.from;
			int to = cur.to;
			double cost = cur.cost;
			
			if(!union(from, to))
				continue;
			
			answer += cost;
			cnt++;
		}
		
		System.out.println(Math.round(answer * 100) / 100.0);
	}

	private static boolean union(int from, int to) {
		int fromRoot = find(from);
		int toRoot = find(to);
		
		if(fromRoot==toRoot)
			return false;
		set[toRoot] = fromRoot;
		return true;
	}

	private static int find(int x) {
		if(set[x] == x)
			return x;
		return set[x] = find(set[x]);
	}

}
