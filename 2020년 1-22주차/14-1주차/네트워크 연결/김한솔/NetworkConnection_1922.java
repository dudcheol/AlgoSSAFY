/**
 * Backjoon - 1922. 네트워크 연결
 * NetworkConnection_1922.java
 * @date 2020-11-03
 * @author hansolKim
 **/

package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge1 implements Comparable<Edge1>{
	int from;
	int to;
	int weight;

	public Edge1(int from, int to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge1 o) {
		return Integer.compare(this.weight, o.weight);
	}
}

public class NetworkConnection_1922 {

	static int N, M;
	static int[] parents;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); // 컴퓨터의 갯수
		M = Integer.parseInt(br.readLine()); // 연결할 수 있는 선의 수
		
		PriorityQueue<Edge1> pq = new PriorityQueue<>();
		parents = new int[N+1];
		
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			pq.add(new Edge1(from, to, weight));
		}
		
		// parents배열 초기화
		make();
		
		int answer = 0;
		int cnt = 0;
		
		while(!pq.isEmpty()) {
			
			// 가장 짧은 간선 가져옴
			Edge1 curEdge = pq.poll();
			
			// cycle을 만들지 않으면 해당 간선 선택
			if(union(curEdge.from, curEdge.to)) {
				answer += curEdge.weight;
				cnt++;
			}
			
			if(cnt == N-1) { break; }
		}
		
		System.out.println(answer);
		br.close();
	}

	private static void make() {
		for(int i=1; i<=N; i++) {
			parents[i] = i;
		}
	}
	
	private static int getParent(int x) {
		if(x == parents[x]) return x;
		return parents[x] = getParent(parents[x]);
	}
	
	private static boolean union(int x, int y) {
		int xRoot = getParent(x);
		int yRoot = getParent(y);
		if(xRoot == yRoot) return false;
		parents[yRoot] = xRoot;
		return true;
	}

}
