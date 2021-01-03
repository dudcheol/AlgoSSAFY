package week15;

import java.util.*;
import java.io.*;

public class _1516_게임개발 {

	static int N, indegree[], result[];
	static V[] v;

	static class V {
		int cost;
		ArrayList<Integer> next;

		public V() {
			this.cost = 0;
			this.next = new ArrayList<>();
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		v = new V[N + 1];
		indegree = new int[N + 1];
		result = new int[N + 1];
		
		for (int i = 1; i <= N; i++) {
			v[i] = new V(); // 각 정점 미리 생성
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			// 정점i를 짓는데 드는 비용 저장
			v[i].cost = Integer.parseInt(st.nextToken());
			
			int input = -1;
			while ((input = Integer.parseInt(st.nextToken())) != -1) {
				v[input].next.add(i); // i를 짓기위해 input을 먼저 지어야 함 (가장 먼저 지어지는 정점은 진입차수가 0이어야 함)
				indegree[i]++; // i를 짓기 위해 먼저 지어야 하는 정점이 추가되었으므로 진입차수를 증가시킴
			}
		}

		// 위상정렬을 위한 큐 생성
		Queue<Integer> q = new LinkedList<>();

		// 자기에게 들어오는 애가 0개인 애들 큐에 담기(indegree가 0인 정점 큐에 삽입)
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				q.offer(i);
				result[i] = v[i].cost;
			}
		}

		while (!q.isEmpty()) {
			int size = q.size();

			while (size-- != 0) {
				int from = q.poll();
				
				for (Integer to : v[from].next) {
					if(--indegree[to] == 0) { // 간선제거
						q.offer(to);
//						v[to].cost += v[from].cost; // ** 질문하기!!! 이게 왜 안되고
					}
					result[to] = Math.max(result[to], result[from]+v[to].cost); // ** 왜 이렇게 해야하지?
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			sb.append(result[i]).append('\n');
		}
		
		System.out.print(sb);
	}

}

/*
 * **반례
5
10 -1
10 1 4 5 -1
4 1 -1
4 3 1 -1
3 3 -1
*/
