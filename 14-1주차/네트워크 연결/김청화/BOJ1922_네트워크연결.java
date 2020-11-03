package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ1922_네트워크연결 {
	static int N, M;
	static ArrayList<Vertex> list = new ArrayList<>();
	static int[] parents, rank;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		parents = new int[N + 1];
		rank = new int[N + 1];
        for (int k = 1; k <= N; k++) {
            parents[k] = k;
        }
        
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			list.add(new Vertex(u, v, w));
		}
		
		Collections.sort(list);
		
		// 출력될 가중치의 합
		int result = 0;
		int cnt = 0;
		// 간선의 수만큼 loop 수행
		for(int i = 0; i < list.size(); i++) {
			// 정점과 정점이 연결될 수 있는지 확인
			if(union(list.get(i).u, list.get(i).v)) {
				result += list.get(i).w; // 가중치 덧셈
				cnt++;
			}
			// cnt가 정점 - 1개가 된다는 것은 모든 정점을 다 연결했다는 의미이므로 종료
			if(cnt == N - 1) break;
		}
		
		System.out.println(result);
	}
	
    static int find(int a) { 
        if(a == parents[a]) return a; 
        else return parents[a] = find(parents[a]); 
    }
 
     
    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
         
        if(bRoot != aRoot) {
            link(aRoot, bRoot);
            return true;
        }
        return false;
    }
     
    public static void link(int a, int b) {
        if(rank[a] > rank[b]) {
            parents[b] = a;
        } else { // rank[a] <= rank[b]
            parents[a] = b;
            if(rank[a] == rank[b]) { // 깊이가 같은 경우 랭크값을 증가시켜준다.
                rank[b]++;
            }
        }
    }
	
	static class Vertex implements Comparable<Vertex> {
		int u;
		int v;
		int w;
		
		public Vertex(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
		
		@Override
		public int compareTo(Vertex o) {
			return this.w - o.w;
		}
	}

}
