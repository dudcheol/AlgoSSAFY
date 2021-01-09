/**
 * Backjoon - 17471. 게리맨더링
 * 게리맨더링_17471.java
 * @date 2021-01-09
 * @author hansolKim
 **/

package p17000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class 게리맨더링_17471 {

	static int answer, N;
	static LinkedList<Integer>[] graph;
	static boolean[] selected;
	static int[] persons;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		
		persons = new int[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			persons[i] = Integer.parseInt(st.nextToken());
		}
		
		graph = new LinkedList[N+1];
		for(int i=1; i<=N; i++) {
			graph[i] = new LinkedList<>();
		}
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			for(int j=0; j<cnt; j++) {
				int node = Integer.parseInt(st.nextToken());
				graph[i].add(node);
				graph[node].add(i);
			}
		}
		
		selected = new boolean[N+1];
		answer = Integer.MAX_VALUE;
		subSet(1);

		answer = answer == Integer.MAX_VALUE ? -1 : answer;
		System.out.println(answer);
		br.close();
	}

	private static void subSet(int cnt) {
		if(cnt == N+1) {
			int aCnt = 0; // true
			int bCnt = 0; // false
			Set<Integer> aSet = new HashSet<>();
			Set<Integer> bSet = new HashSet<>();
			
			for(int i=1; i<=N; i++) {
				if(selected[i]) { // 참인 경우 -> a선거구
					aCnt += persons[i];
					aSet.add(i);
				} else { // 거짓인 경우 -> b선거구
					bCnt += persons[i];
					bSet.add(i);
				}
			}
			
			// 두 지역의 차이가 이전의 차이보다 크면 패스
			if(answer <= Math.abs(aCnt-bCnt)) {
				return;
			}

			// 선거구의 지역이 없는 경우
			if(aSet.size()==0 || bSet.size()==0) {
				return;
			}
			
			Queue<Integer> q = new LinkedList<>();
			int aStart = aSet.iterator().next();
			q.add(aStart);
			aSet.remove(aStart);
			while(!q.isEmpty()) {
				int cur = q.poll();
				for(int nextNode : graph[cur]) {
					// 연결된 노드가 현재 셋에 있고 true인 경우 -> set에서 제거 후 q에 삽입
					if(selected[nextNode] && aSet.contains(nextNode)) {
						aSet.remove(nextNode);
						q.add(nextNode);
					}
				}
			}
			
			if(!aSet.isEmpty()) { 
				return;
			}
			q.clear();
			
			int bStart = bSet.iterator().next();
			q.add(bStart);
			bSet.remove(bStart);
			while(!q.isEmpty()) {
				int cur = q.poll();
				for(int nextNode : graph[cur]) {
					// 연결된 노드가 현재 셋에 있고 false인 경우 -> set에서 제거 후 q에 삽입
					if(!selected[nextNode] && bSet.contains(nextNode)) {
						bSet.remove(nextNode);
						q.add(nextNode);
					}
				}
			}
			
			if(!bSet.isEmpty()) { 
				return;
			}
			
			answer = Math.abs(aCnt-bCnt); 
			return;
		}
		
		selected[cnt] = true;
		subSet(cnt+1);
		selected[cnt] = false;
		subSet(cnt+1);
	}

}
