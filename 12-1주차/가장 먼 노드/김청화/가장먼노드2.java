package programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// 프로그래머스 가장 먼 노드 https://programmers.co.kr/learn/courses/30/lessons/49189
public class 가장먼노드2 {
	static ArrayList<ArrayList<Integer>> adjlist = new ArrayList<>();
	static int[] nodes;
	static boolean[] visited;
	static int max;
	
    public static int solution(int n, int[][] edge) {
        int answer = 0;
        
        // nodes 배열: 각 해당 노드로 가는 데 필요한 간선 개수
        nodes = new int[n + 1];
        visited = new boolean[n + 1];
        
        // 연결리스트 초기화
        for (int i = 0; i < edge.length; i++) {
			adjlist.add(new ArrayList<Integer>());
		}

        for (int i = 0; i < edge.length; i++) {
        	int u = edge[i][0];
        	int v = edge[i][1];
        	
        	adjlist.get(u).add(v);
        	adjlist.get(v).add(u);
        }
        
        Queue<Integer> q = new LinkedList<>();
        q.add(1); // 시작점 넣기
        visited[1] = true;
        
        while(!q.isEmpty()) {
        	int cur = q.poll();
        	
        	for (int i = 0; i < adjlist.get(cur).size(); i++) {
				if(!visited[adjlist.get(cur).get(i)]) {
					nodes[adjlist.get(cur).get(i)] = nodes[cur] + 1;
					visited[adjlist.get(cur).get(i)] = true;
					q.add(adjlist.get(cur).get(i));
				}
			}
        	
        }
        
        for (int i = 1; i <= n; i++) {
			if(max < nodes[i])
				max = nodes[i];
		}
        
        for (int i = 1; i <= n; i++) {
			if(max == nodes[i])
				answer++;
		}
        return answer;
    }
    
	public static void main(String[] args) {
		int n = 6;
		int[][] vertex = { {3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2} };
	
		System.out.println(solution(n, vertex));
	}
}
