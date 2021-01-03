package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B1922_ver2 {
	static int[] parent;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int N=Integer.parseInt(br.readLine());
		int M=Integer.parseInt(br.readLine());
		
		PriorityQueue<Edge> pq=new PriorityQueue<Edge>();
		parent=new int[N+1];
		int min=0;
		
		for(int i=0;i<N+1;i++) {
			parent[i]=i;
		}
	
		for(int i=0;i<M;i++) {
			StringTokenizer stz=new StringTokenizer(br.readLine());
			int start=Integer.parseInt(stz.nextToken());
			int end=Integer.parseInt(stz.nextToken());
			int cost=Integer.parseInt(stz.nextToken());
			
			pq.add(new Edge(start, end, cost));
		}
		
		
		while(!pq.isEmpty()){
			Edge edge=pq.poll();
			
			
			int aRoot=find(edge.start);
			int bRoot=find(edge.end);
			
			if(aRoot==bRoot) {
				continue;
			}
			union(aRoot,bRoot);
			min+=edge.cost;
		}
		System.out.println(min);
	}
	public static int find(int a) {
		if(a==parent[a]) {
			return a;
		}
		parent[a]=find(parent[a]);
		return parent[a];
	}
	
	public static void union(int a,int b) {
		int aRoot=find(a);
		int bRoot=find(b);
		
		if(aRoot!=bRoot) {
			parent[aRoot]=b;
		}else {
			return;
		}
	}
	
	
	static class Edge implements Comparable<Edge>{
		int start;
		int end;
		int cost;
		public Edge(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
		
		
		@Override
		public int compareTo(Edge o) {
			if(this.cost>o.cost) {
				return 1;
			}else {
				return -1;
			}
		}
		
		
	}

}
