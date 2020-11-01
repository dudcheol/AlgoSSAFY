package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 백준 4386 별자리 만들기 https://www.acmicpc.net/problem/4386
public class BOJ4386_별자리만들기 {
	static int n;
	static double[][] points;
	static int[] parents, rank;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		points = new double[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			points[i][0] = Double.parseDouble(st.nextToken());
			points[i][1] = Double.parseDouble(st.nextToken());
		}
		
		PriorityQueue<list> pq = new PriorityQueue<list>();
		double weight = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(i != j) {
					weight = Double.parseDouble(String.format("%.2f", Math.sqrt(Math.pow(points[i][0] - points[j][0], 2) + Math.pow(points[i][1] - points[j][1], 2))));
					pq.add(new list(i, j, weight));
				}
			}
		}
		
		parents = new int[n + 1];
		rank = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			parents[i] = i;
		} // union-find: 일단 부모를 자기 자신으로
		
		double result = 0;
		int size = pq.size();
		for (int i = 0; i < size; i++) {
			list node = pq.poll();
			int start = node.u;
			int end = node.v;
			int a = find(start);
			int b = find(end);
			if(a == b) continue;
			
			union(start, end);
			result += node.w;
		}
		
		System.out.println(result);
		
	}
	
    static int find(int a) { 
        if(a == parents[a]) return a; 
        else return parents[a] = find(parents[a]); 
    }
    
    static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
         
        if(bRoot != aRoot)
            link(aRoot, bRoot);
    }
    
    public static void link(int a, int b) {
        if(rank[a] > rank[b]) {
            parents[b] = a;
        } else { // rank[a] <= rank[b]
            parents[a] = b;
            if(rank[a] == rank[b]) { // 깊이가 같은 경우 랭크값을 증가시켜준다.
                rank[b]++;
            }
        }
    }
    
	public static class list implements Comparable<list>{
		int u;
		int v;
		double w;
		
		public list(int u, int v, double w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
		
		@Override
		public int compareTo(list list2) {
			return list2.w >= this.w ? -1 : 1;
		}
	}

}
