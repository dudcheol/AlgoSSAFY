package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1781_컵라면 {
	static int N, cnt;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];				
			}
		});
		
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			pq.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
		}
		
		PriorityQueue<Integer> ramen = new PriorityQueue<>();
		int date = 1;
		while(!pq.isEmpty()) {
			
			int[] cur = pq.poll();
			
			if(cur[0] <= date && !ramen.isEmpty() && ramen.peek() < cur[1]) {
				cnt -= ramen.poll();
				cnt += cur[1];
				ramen.add(cur[1]);
			} else {
				cnt += cur[1];
				ramen.add(cur[1]);
			}
			
			date = cur[0];
		}
		
		System.out.println(cnt);
	}
}