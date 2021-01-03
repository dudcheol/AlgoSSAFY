package week13;

import java.util.*;
import java.io.*;

public class 특정한최단경로 {
	
	private static final int INF = Integer.MAX_VALUE;
	private static int N, E;
	private static int[] D;
	private static int V1;
	private static int V2;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		D = new int[N+1];
		
		ArrayList<int[]>[] l = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			l[i] = new ArrayList<int[]>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			l[from].add(new int[] {to, cost});
			l[to].add(new int[] {from, cost});
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		V1 = Integer.parseInt(st.nextToken()); // 반드시 거쳐야 하는 정점
		V2 = Integer.parseInt(st.nextToken());
		
		// 1 -> V1 -> V2 -> N
		Queue<int[]> q = new LinkedList<>();
		int[] cmd = {1, V1, V2, N};
		int v1v2 = 0;
		for (int c = 0; c < 3; c++) {
			Arrays.fill(D, INF);
			D[cmd[c]] = 0;
			q.offer(new int[] {cmd[c], 0});
			while(!q.isEmpty()) {
				int[] p = q.poll();

				int from = p[0];
				int len = p[1];
				
				for (int i = 0; i < l[from].size(); i++) {
					int[] next = l[from].get(i);
					
					int to = next[0];
					int cost = next[1];
					
					if(D[to] > D[from] + cost) {
						D[to] = D[from] + cost;
						q.offer(new int[] {to, len+1});
					}
				}
			}
			if(D[cmd[c+1]] == INF) {
				v1v2 = INF;
				break;
			}
			v1v2 += D[cmd[c+1]];
		}
		
		
		// 1 -> V2 -> V1 -> N
		q = new LinkedList<>();
		cmd = new int[]{1, V2, V1, N};
		int v2v1 = 0;
		for (int c = 0; c < 3; c++) {
			Arrays.fill(D, INF);
			D[cmd[c]] = 0;
			q.offer(new int[] {cmd[c], 0});
			while(!q.isEmpty()) {
				int[] p = q.poll();

				int from = p[0];
				int len = p[1];
				
				for (int i = 0; i < l[from].size(); i++) {
					int[] next = l[from].get(i);
					
					int to = next[0];
					int cost = next[1];
					
					if(D[to] > D[from] + cost) {
						D[to] = D[from] + cost;
						q.offer(new int[] {to, len+1});
					}
				}
			}
			if(D[cmd[c+1]] == INF) {
				v2v1 = INF;
				break;
			}
			v2v1 += D[cmd[c+1]];
		}
		
		// 두 개 중 작은 값 출력
		int answer = Math.min(v1v2, v2v1);
		System.out.println(answer == INF ? -1 : answer);
	}

}
