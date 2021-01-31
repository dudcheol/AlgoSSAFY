/**
 * Backjoon - 2660. 회장뽑기
 * 회장뽑기_2660.java
 * @date 2021-01-31
 * @author hansolKim
 **/

package p2000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 회장뽑기_2660 {

	static int N, minPoint, cnt;
	static int[] points;
	static boolean[] visited;
	static ArrayList<Integer>[] graph;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		graph = new ArrayList[N+1];
		for(int i=1; i<=N; i++) { graph[i] = new ArrayList<>();}
		
		StringTokenizer st;
		while(true) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			// 입력 종료
			if(x == -1) { break;}
			graph[x].add(y);
			graph[y].add(x);			
		}
		
		points = new int[N+1];
		minPoint = Integer.MAX_VALUE;
		
		for(int i=1; i<=N; i++) {
			cnt = 0;
			visited = new boolean[N+1];
			bfs(i);
		}
		
		ArrayList<Integer> answerList = new ArrayList<>();
		
		for(int i=1; i<=N; i++) {
			if(points[i] == minPoint) {
				answerList.add(i);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(minPoint).append(" ").append(answerList.size()).append("\n");
		
		for(int num : answerList) { sb.append(num).append(" "); }
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static void bfs(int x) {
		Queue<int[]> q = new LinkedList<>();		
		q.add(new int[] {x, 0});
		visited[x] = true;
		cnt = 1;
		
		while(!q.isEmpty()) {
			int cur = q.peek()[0];
			int time = q.poll()[1];
			
			if(time>= minPoint) { return;} // 회장후보가 될 수 없는 경우
			
			for(int i=0; i<graph[cur].size(); i++) {
				int friend = graph[cur].get(i);
				if(visited[friend]) continue;  // 이미 방문한 곳이면 패스
				
				visited[friend] = true;
				if(++cnt == N) { 
					minPoint = time+1;
					points[x] = minPoint;
					return;
				}
				q.add(new int[] {friend, time+1});
			}
		}
		
	}

}
