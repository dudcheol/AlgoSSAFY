package week12;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class 가장먼노드 {

	public int solution(int n, int[][] edge) {
		// 그래프 만들기
		ArrayList<Integer>[] edges = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			edges[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < edge.length; i++) {
			edges[edge[i][0]].add(edge[i][1]);
			edges[edge[i][1]].add(edge[i][0]);
		}

		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { 1, 0 });
		boolean[] visited = new boolean[n+1];
		visited[1] = true;

		int max = Integer.MIN_VALUE;
		int cnt = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			
			for (int i = 0; i < size; i++) {
				int[] polled = q.poll();
				int node = polled[0];
				int dist = polled[1];
				
				if(max < dist) {
					max = dist;
					cnt = 1;
				} else if(max == dist) {
					cnt++;
				}
				
				for (int j = 0; j < edges[node].size(); j++) {
					int next = edges[node].get(j);

					if (visited[next])
						continue;

					visited[next] = true;
					q.offer(new int[] { next, dist + 1 });
				}
			}
		}

		return cnt;
	}

	public static void main(String[] args) {
		가장먼노드 c = new 가장먼노드();

		int n = 6;
		int[][] edge = { { 3, 6 }, { 4, 3 }, { 3, 2 }, { 1, 3 }, { 1, 2 }, { 2, 4 }, { 5, 2 } };

		System.out.println(c.solution(n, edge));
	}

}
