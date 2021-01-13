package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ2812_크게만들기 {
	static int N, M;
	static int[] ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		ans = new int[M];
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1] != o2[1])
					return o2[1] - o1[1];
				else
					return o1[0] - o2[0];
			}
		});
		
		String string = br.readLine();
		for (int i = 0; i < string.length(); i++) {
			pq.add(new int[] {i, Integer.parseInt(String.valueOf(string.charAt(i)))});
		}
		
		PriorityQueue<int[]> q = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N - M; i++) {
			q.add(pq.poll());
		}
		
		for (int i = 0; i < N - M; i++) {
			sb.append(q.poll()[1]);
		}
		System.out.println(sb);
	}
}