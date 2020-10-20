package graph;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class 가장먼노드 {

	public static void main(String[] args) throws IOException {

		System.out.println(solution(6, new int[][] { { 3, 6 }, { 4, 3 }, { 3, 2 }, { 1, 3 }, { 1, 2 }, { 2, 4 }, { 5, 2 } }));
	}

	static public int solution(int n, int[][] edge) {
		int answer = 0;

		boolean[][] graph = new boolean[n + 1][n + 1];
		boolean[] visited = new boolean[n + 1];

		// graph 배열의 선이있으면 true로 변경

		for (int i = 0; i < edge.length; i++) {
			graph[edge[i][0]][edge[i][1]] = true;

        	graph[edge[i][1]][edge[i][0]] = true;
		}

		visited[0] = true;
		visited[1] = true;
		/*
		 * int count = 1; int[] value = new int[n + 1];
		 */
		Queue<Integer> qu = new LinkedList<Integer>();
		Queue<Integer> pack = new LinkedList<Integer>();

		qu.offer(1);
		while (true) {
		
			answer=qu.size();
			while (!qu.isEmpty()) {
				int node = qu.poll();
				for (int i = 1; i < n + 1; i++) {
					if (visited[i] || !graph[node][i]) {
						continue;
					}
					pack.offer(i);
					visited[i]=true;
				}
			}
			
			if(pack.isEmpty()) {
				break;
			}
			
			while(!pack.isEmpty()) {
				qu.offer(pack.poll());
			}
		}
		return answer;
	}
}
