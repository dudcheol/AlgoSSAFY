/**
 * Backjoon - 17281. 야구 게임
 * 집합의표현_1717.java
 * @date 2020-12-26
 * @author hansolKim
 **/

package p1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 집합의표현_1717 {

	static int N, M;
	static int[] parents;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		
		parents = new int[N+1];
		for(int i=0; i<=N; i++) {
			parents[i] = i;
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(command == 0) { // 집합을 합침
				union(a, b);
			} else { // 집합 포함여부
				if(isUnion(a, b)) {
					sb.append("YES");
				} else {
					sb.append("NO");
				}
				sb.append("\n");
			}
						
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static boolean isUnion(int x, int y) {
		int xRoot = getParent(x);
		int yRoot = getParent(y);
		
		if(xRoot == yRoot) return true;
		return false;
	}

	private static void union(int x, int y) {
		int xRoot = getParent(x);
		int yRoot = getParent(y);
		
		if(xRoot != yRoot) {
			parents[xRoot] = yRoot;
		}
	}

	private static int getParent(int x) {
		if(x == parents[x]) return x;
		return parents[x] = getParent(parents[x]);
	}
}