/**
 * Backjoon - 1781. 컵라면
 * 컵라면_1781.java
 * @date 2021-01-09
 * @author hansolKim
 **/

package p1000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 컵라면_1781 {
	
	static int[] parents;
	static boolean[] used;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		used = new boolean[N+1];
		parents = new int[N+1];
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {				
				return Integer.compare(o2[1], o1[1]); // 컴라면 기준 내림차순
			}
		});
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			pq.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())} );
		}

		for(int i=1; i<=N; i++) {
			parents[i] = i;
		}
		
		int answer = 0;
		
		while(!pq.isEmpty()) { 
			int deadLine = pq.peek()[0]; // 데드라인
			int ramen = pq.poll()[1]; // 컵라면

			parents[deadLine] = updateParent(deadLine);
			
			if(parents[deadLine] == 0 || used[parents[deadLine]]) { continue; }
			used[parents[deadLine]] = true;
			answer+=ramen; 
		}
		
		System.out.println(answer);
		br.close();
	}

	private static int updateParent(int num) {
		if(num == 0) { return num;}
		if(!used[num]) { return num;}
		if(parents[num] == 0) { return 0; }
		return parents[num] = updateParent(num-1);
	}
}