/**
 * Backjoon - 1504. 특정한 최단 경로
 * SpecialRoute_1504.java
 * @date 2020-11-01
 * @author hansolKim
 **/

package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SpecialRoute_1504 {

	static final int INF = 100000000;
	static int N, E, v1, v2;
	static ArrayList<int[]>[] list;
	static boolean[] visited;
	static int[] dist;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N+1];
		for(int i=1; i<=N; i++) { list[i] = new ArrayList<>(); }
		
		// 간선정보 입력
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());

			list[from].add(new int[] {to, dist});
			list[to].add(new int[] {from, dist});
		}
		
		// 무조건 거쳐야 하는 두 개의 서로다른 정점
		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());
		
		int path1 = 0;
		int path2 = 0;
		
		// 1번정점, v1, v2, N번정점을 리스트에 넣기
		int[] vlist = {1, v1, v2, N};
		int[] vlist2 = {1, v2, v1, N};
		
		for(int i=0; i<3; i++) {
			int d = dijkstra(vlist[i], vlist[i+1]);
			if(d != INF) {path1+=d;}
			else {
				System.out.println(-1);
				return;
			}
		}
		
		for(int i=0; i<3; i++) {
			int d = dijkstra(vlist2[i], vlist2[i+1]);
			if(d != INF) {path2+=d;}
			else {
				System.out.println(-1);
				return;
			}
		}
		
		int answer = Math.min(path1, path2);
		
		System.out.println(answer);
	}

	private static int dijkstra(int start, int dest) {
		
		visited = new boolean[N+1];
		dist = new int[N+1];
		for(int i=1; i<=N; i++) { dist[i] = INF;}
		dist[start] = 0;
		
		for(int i=0; i<N-1; i++) { // N개의 정점 
			
			int curV = 0; // 선택된 정점
			int curDist = INF+1; 
			
			// 방문하지 않은 정점중에 dist가 가장짧은 정점 선택
			for(int j=1; j<=N; j++) {
				if(!visited[j] && dist[j] < curDist) {
					curV = j;
					curDist = dist[j];
				}
			}
			
			// 선택된 정점이 목적지인 경우
			if(curV == dest) {return curDist;}
			
			// 선택된 정점 방문처리
			visited[curV] = true;
			
			// 현재 정점을 기준으로 거리 갱신
			for(int j=0; j<list[curV].size(); j++) {
				int targetV = list[curV].get(j)[0];
				int targetDist = list[curV].get(j)[1];
				
				if(curDist+targetDist<dist[targetV]) {
					dist[targetV] = curDist+targetDist; 
				}
			}
			
		}
		
		return dist[dest];
	}

}
 