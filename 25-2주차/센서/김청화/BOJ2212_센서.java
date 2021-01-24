package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ2212_센서 {
	static int N, K, sensor[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		sensor = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			sensor[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(sensor);
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		for (int i = N - 1; i > 0; i--) {
			pq.add(sensor[i] - sensor[i - 1]);
		}
		
		for (int i = 0; i < K - 1; i++) {
			pq.poll();
		}
		
		int sum = 0;
		while(!pq.isEmpty()) {
			sum += pq.poll();
		}
		System.out.println(sum);
	}
}