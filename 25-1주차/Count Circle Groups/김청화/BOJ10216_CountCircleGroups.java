package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10216_CountCircleGroups {
	static int T, N, distance[][], parents[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			distance = new int[N][3];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				distance[i][0] = Integer.parseInt(st.nextToken());
				distance[i][1] = Integer.parseInt(st.nextToken());
				distance[i][2] = Integer.parseInt(st.nextToken());
			}
			
			init();
			int cnt = N;
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					int x_diff = Math.abs(distance[i][0] - distance[j][0]);
					int y_diff = Math.abs(distance[i][1] - distance[j][1]);
					int r = Math.abs(distance[i][2] + distance[j][2]); 
					if(x_diff * x_diff + y_diff * y_diff <= r * r) {
						if(union(i, j))
							cnt--;
					}
				}
			}
			System.out.println(cnt);
		}
	}

	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot != bRoot) {
			parents[bRoot] = aRoot;
			return true;
		}
		return false;
	}

	private static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]); // find(a)로 하는 것 주의!
	}

	private static void init() {
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}
}