package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1516_게임개발 {
	static int N;
	static ArrayList<ArrayList<Integer>> adjlist = new ArrayList<>();
	static int[] indegree, times;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder string = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		
		indegree = new int[N + 1];
		times = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			adjlist.add(new ArrayList<Integer>());
		}
		StringTokenizer st = null; 
		for (int v = 1; v <= N; v++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			times[v] = time;
			while(true) {
				int u = Integer.parseInt(st.nextToken());
				if(u == -1) break;
				
				adjlist.get(u).add(v);
				indegree[v]++;
			}
		}
		
		// 위상 정렬
		Queue<Integer> q = new LinkedList<>();
		
		for (int i = 1; i <= N; i++) { // 진입차수가 없는 애들은 q에다 넣음
			if(indegree[i] == 0) {
				q.add(i);
			}
		}
		
		int[] result = new int[N + 1];
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			int size = adjlist.get(cur).size();
			for (int i = 0; i < size; i++) {
				int next = adjlist.get(cur).get(i);
				indegree[next]--;
				
				// next 건물을 짓기 전까지 걸린 시간 (ex. A건물 10, B건물 20시간 C건물 짓기 위해서 A, B 건물 지어져 있어야 함 -> C건물은 20시간 뒤부터 지을 수 있음)
				result[next] = Math.max(result[next], result[cur] + times[cur]);
			
				if(indegree[next] == 0)
					q.add(next);
			}
		}
		
		for (int i = 1; i <= N; i++) {
			string.append(result[i] + times[i]).append("\n");
		}
		
		System.out.println(string);
	}

}
