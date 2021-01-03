/**
 * Backjoon - 1976. 여행 가자
 * GoTravel_1976.java
 * @date 2020-11-12
 * @author hansolKim
 **/

package p1000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class GoTravel_1976 {

	static int N, M;
	static ArrayList<Integer> path;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		path = new ArrayList<>();
		map = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 1; i <= N; i++) {
			map[i][i] = 1;
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			path.add(Integer.parseInt(st.nextToken()));
		}

		for (int k = 1; k <= N; k++) { // 경유지
			for (int i = 1; i <= N; i++) { // 출발지
				for (int j = 1; j <= N; j++) { // 도착지
					if(map[i][k] + map[k][j] == 2) { // 출발지로 부터 경유지를 이용해서 도착지를 간다면 1로 표기
						map[i][j] = 1;
					}
				}
			}
		}
		
		boolean isPath = true;
		
		for(int i=0; i<path.size()-1; i++) {
			if(map[path.get(i)][path.get(i+1)] == 0) {
				isPath = false;
				break;
			}
		}

		if (isPath) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}
}