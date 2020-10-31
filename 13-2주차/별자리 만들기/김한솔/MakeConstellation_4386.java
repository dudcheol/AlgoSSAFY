/**
 * Backjoon - 4386. 별자리 만들기
 * MakeConstellation_4386.java
 * @date 2020-10-31
 * @author hansolKim
 **/

package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge>{
	int from;
	int to;
	double dist;
	
	public Edge(int from, int to, double dist) {
		this.from = from;
		this.to = to;
		this.dist = dist;
	}

	@Override
	public int compareTo(Edge o) {
		return Double.compare(this.dist, o.dist);
	}
	
}

public class MakeConstellation_4386 {
	
	static int[] parents;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		double[][] stars = new double[n][2];
		parents = new int[n]; 
		
		// make
		for(int i=0; i<n; i++) {
			parents[i] = i;
		}
		
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			
			stars[i][0] = x;
			stars[i][1] = y;
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		// 모든 간선정보 저장
		for(int i=0; i<n; i++) {
			double curX = stars[i][0];
			double curY = stars[i][1];
			
			for(int j=0; j<n; j++) {
				if(i == j) continue;
				double targetX = stars[j][0];
				double targetY = stars[j][1];
				
				double curDist = getDist(curX, curY, targetX, targetY);
				pq.offer(new Edge(i, j, curDist));
			}
		}
		
		double answer = 0;
		int cnt = 0;
		while(!pq.isEmpty()) {
			
			Edge e = pq.poll();
			
			if(union(e.from, e.to)) { 
				answer += e.dist; 
				cnt++; 
			}
			
			if(cnt == n-1) { break;}
		}
		
		System.out.printf("%.2f", answer);
		
	}

	static double getDist(double x, double y, double tX, double tY) {
		return Math.sqrt(Math.pow(Math.abs(x-tX), 2.0) + Math.pow(Math.abs(y-tY), 2.0));
	}
	
	static int getParents(int a) {
		if(parents[a] == a) return a;
		return parents[a] = getParents(parents[a]); 
	}
	
	static boolean union(int a, int b) {
		int aRoot = getParents(a);
		int bRoot = getParents(b);
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}
}
