package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ4195_친구네트워크 {
	static int T, F;
	static int[] rank, parents, cnt;	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder string = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			F = Integer.parseInt(br.readLine());
			
			int no = 0;
			Map<String, Integer> map = new HashMap<>();
			rank = new int[2*F];
			parents = new int[2*F];
			cnt = new int[2*F];
			for (int i = 0; i < 2*F; i++) {
				parents[i] = i; // 초기화
				cnt[i] = 1;
			}
			
			for (int f = 0; f < F; f++) {
				st = new StringTokenizer(br.readLine());
				
				String name1 = st.nextToken();
				String name2 = st.nextToken();
				int no1 = 0, no2 = 0;
				
				if(!map.containsKey(name1)) {
					map.put(name1, no++);
				}
				if(!map.containsKey(name2)) {
					map.put(name2, no++);
				}
				no1 = map.get(name1);
				no2 = map.get(name2);

				union(no1, no2);
				
				string.append(cnt[find(no2)]).append("\n");
			}
		}
		System.out.println(string);
	}
	
	static void union(int a, int b){
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot != bRoot) {
			link(aRoot, bRoot);
		}
	}
	
	static int find(int a){
		if(a == parents[a]) return a;
		else return parents[a] = find(parents[a]);
		
	}
	
	static void link(int a, int b){
		if(rank[a] > rank[b]) {
			parents[b] = a;
			cnt[a] += cnt[b];
		} else {
			parents[a] = b;
			cnt[b] += cnt[a];
			if(rank[a] == rank[b]) {
				rank[b]++;
			}
		}
	}
}
