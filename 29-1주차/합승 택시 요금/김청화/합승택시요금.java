package programmers;

import java.util.Arrays;
import java.util.PriorityQueue;

public class 합승택시요금 {
	
	static int[][] matrix;
	static int[] distance;
	static boolean[] visited;
	
	public static void main(String[] args) {
		int n = 6;
		int s = 4;
		int a = 6;
		int b = 2;
		int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41},
				{5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
		
		System.out.println(Solution(n, s, a, b, fares));
	}
	
    public static int Solution(int n, int s, int a, int b, int[][] fares) {
    	
        init(n, fares);
        
        int cost = dijkstra(n, s, a) + dijkstra(n, s, b); // 합승하지 않고 따로 타고 갔을 때 가격 계산
        for (int i = 1; i <= n; i++) {
			
        	if(s != i)
        		cost = Math.min(cost, dijkstra(n, s, i) + dijkstra(n, i, a) + dijkstra(n, i, b));
        }
        
        return cost;
    }
    
	private static void init(int n, int[][] fares) {
		matrix = new int[n + 1][n + 1];
		
		for (int[] fare: fares) {
			matrix[fare[0]][fare[1]] = fare[2];
			matrix[fare[1]][fare[0]] = fare[2];
		}
	}

	public static int dijkstra(int n, int start, int end) {
		final int INFINITY = Integer.MAX_VALUE;
		
		distance = new int[n + 1];
		visited = new boolean[n + 1];
		
		PriorityQueue<Vertex> pQueue = new PriorityQueue<Vertex>();
		Arrays.fill(distance, INFINITY);
		distance[start] = 0;
		pQueue.offer(new Vertex(start, distance[start]));
		
		Vertex current = null;
		
		while(!pQueue.isEmpty()) {

			current = pQueue.poll();
			if(visited[current.no]) continue;
			
			visited[current.no] = true;
			if(current.no == end) break;
			
			for (int j = 1; j <= n; j++) { 
				if(!visited[j] && matrix[current.no][j] != 0 && distance[j] > current.totalDistance + matrix[current.no][j]) {
					distance[j] = current.totalDistance + matrix[current.no][j];
					pQueue.offer(new Vertex(j, distance[j]));
				}
			}
		}

		return distance[end];
	}
	
	static class Vertex implements Comparable<Vertex> {
		int no, totalDistance;
		
		public Vertex(int no, int totalDistance) {
			super();
			this.no = no;
			this.totalDistance = totalDistance;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.totalDistance - o.totalDistance; 
		}
	}
}
