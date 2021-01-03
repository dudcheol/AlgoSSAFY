/**
 * programmers - 가장 먼 노드
 * FurthestNode.java
 * @date 2020-10-20
 * @author hansolKim
 **/

package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class FurthestNode {

	public static void main(String[] args) {
		FurthestNode furthestNode = new FurthestNode();
		int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
		furthestNode.solution(6, edge);
	}
	
	static int[] visited;
	static HashMap<Integer, ArrayList<Integer>> graph;
	
	public int solution(int n, int[][] edge) {
		int answer = 0;
		
		visited = new int[n+1];
		graph = new HashMap<>();
		
		for(int i=0; i<edge.length; i++) {
			ArrayList<Integer> list = new ArrayList<>();
			if(graph.containsKey(edge[i][0])) {
				list = graph.get(edge[i][0]);
			}
			list.add(edge[i][1]);
			graph.put(edge[i][0], list);
			
			ArrayList<Integer> list2 = new ArrayList<>();
			if(graph.containsKey(edge[i][1])) {
				list2 = graph.get(edge[i][1]);
			}
			list2.add(edge[i][0]);
			graph.put(edge[i][1], list2);
		}
		
		bfs(1);
		
		Arrays.sort(visited);
		int dist = visited[visited.length-1];
		answer = 1;
		for(int i=visited.length-2; i>=0; i--) {
			if(dist == visited[i]) { 
				answer++;
				continue;
			}
			break;
		}
		return answer;
	}

	private void bfs(int start) {
		visited[start] = 1;
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		
		while(!q.isEmpty()) {
			int node = q.poll();
			
			ArrayList<Integer> list = graph.get(node);

			for(int nextNode : list) {
				// 방문했는지 확인
				if(visited[nextNode] != 0) continue;
				
				// 방문을 안했다면
				visited[nextNode] = visited[node]+1;
				q.add(nextNode);
			}
		}
	}

}
