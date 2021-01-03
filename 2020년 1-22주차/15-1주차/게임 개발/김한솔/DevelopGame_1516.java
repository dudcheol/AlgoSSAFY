/**
 * Backjoon - 1516. 게임 개발
 * DevelopGame_1516.java
 * @date 2020-11-10
 * @author hansolKim
 **/

package p1000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class DevelopGame_1516 {

	static int N;
	static int[] inDegree, time, result;
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		inDegree = new int[N+1];
		time = new int[N+1];
		result = new int[N+1];
		map = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int cntToken = st.countTokens();
			
			time[i] = Integer.parseInt(st.nextToken()); // 건물 짓는데 걸리는 시간
			for(int j=0; j<cntToken-2; j++) {
				int input = Integer.parseInt(st.nextToken());
				map[input][i] = 1; // 연결여부
				inDegree[i]++; // i번째 진입차수 증가
			}
		}
		
		topology();
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++) {
			sb.append(result[i]).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void topology() {
		Queue<Integer> q = new LinkedList<>();
		
		// 진입차수가 0개인 것 모두 큐에 삽입
		for(int i=1; i<=N; i++) {
			if(inDegree[i] == 0) {
				q.add(i);
				result[i] = time[i];
			}
		}
		
		while(!q.isEmpty()) {
			int cur = q.poll(); // 현재 지워질 정점번호
			
			for(int i=1; i<=N; i++) {
				if(map[cur][i] == 1) { // 현재 정점에서 진입하는 정점인 경우
					result[i] = Math.max(result[i], result[cur]+time[i]); // 필요한 건물을 모두짓는데 가장오래걸리는 시간
					if(--inDegree[i] == 0) { // 차수 1감소한 결과 진입차수가 0이되는 경우
						q.add(i);
					}
				}
			}
			
		}
	}

}
