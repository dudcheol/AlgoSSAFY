package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ12764_싸지방에간준하 {
	static int N;
	static int[] computer = new int[1000001];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {// 시작시간 정렬
				return o1[0] - o2[0];
			}
		});
		
		PriorityQueue<int[]> end = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		}); // 끝 시간 정렬

		PriorityQueue<int[]> labels = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		}); // 라벨 정렬
		
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			pq.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
		}
		

		int idx = 0;
		for (int i = 0; i < N; i++) {
			int cur[] = pq.poll();
			
			// end가 안 비어있거나, end에서 가장 작은 시간 < cur의 시작시간 이면
			if(!end.isEmpty() && (labels.size() > 0 || end.peek()[0] <= cur[0])) {
				
				while(!end.isEmpty() && end.peek()[0] <= cur[0]) {
					labels.add(end.poll());
				}
				int label = labels.poll()[1];
				end.add(new int[] {cur[1], label});
				computer[label]++;
			
			} else {
				end.add(new int[] {cur[1], idx});
				computer[idx++]++;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(idx).append("\n");
		for (int i = 0; i < computer.length; i++) {
			if(computer[i] != 0)
				sb.append(computer[i]).append(" ");
			else break;
		}
		
		System.out.println(sb);
	}
}