package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// PQ
public class BOJ1715_카드정렬하기 {
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			int card = Integer.parseInt(br.readLine());
			pq.add(card);
		}
		
		int result = 0;
		while(!pq.isEmpty()) {
			if(pq.size() == 1) {
				System.out.println(result);
				return;
			}
			int a = pq.poll();
			int b = pq.poll();
			pq.add(a + b);
			result += (a + b);
		}
	}

}
