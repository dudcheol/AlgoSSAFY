package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1717_집합의표현 {
	static int n, m, parents[], rank[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		parents = new int[n + 1];
		rank = new int[n + 1];
		
		for (int i = 1; i <= n; i++) {
			parents[i] = i;
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int input = Integer.parseInt(st.nextToken());
			
			if(input == 0) {
				union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			} else {
				int rootA = find(Integer.parseInt(st.nextToken()));
				int rootB = find(Integer.parseInt(st.nextToken()));
				
				if(rootA == rootB)
					sb.append("YES\n");
				else
					sb.append("NO\n");
			}
		}
		System.out.println(sb);
	}

	private static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		if(rootA != rootB)
			link(rootA, rootB);
	}

	private static void link(int a, int b) {
		if(rank[a] < rank[b])
			parents[a] = b;
		else {
			parents[b] = a;
			if(rank[a] == rank[b])
				rank[a]++;
		}		
	}

	private static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
}
