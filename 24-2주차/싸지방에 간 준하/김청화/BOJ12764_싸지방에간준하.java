package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ12764_싸지방에간준하 {
	static int N;
	static int[] computer = new int[1000001];
	static int[][] list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		list = new int[N][2];
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			list[i][0] = Integer.parseInt(st.nextToken());
			list[i][1] = Integer.parseInt(st.nextToken());
		}		
		
		Arrays.sort(list, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		
		PriorityQueue<int[]> end = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		}); // 끝 시간 정렬
		PriorityQueue<Integer> labels = new PriorityQueue<>(); // 라벨 정렬

		int idx = 0; // 총 의자 개수
		for (int i = 0; i < N; i++) {
			
			while(!end.isEmpty()) {
				if(end.peek()[0] <= list[i][0]) {
					labels.add(end.poll()[1]);
				} else 
					break;
			}
			
			if(labels.isEmpty()) {
				end.add(new int[] {list[i][1], idx});
				computer[idx++]++;
			
			} else {
				int label = labels.poll(); // 앉을 수 있는 의자 중에서 가장 작은 번호
				end.add(new int[] {list[i][1], label});
				computer[label]++;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < idx; i++) {
			sb.append(computer[i]).append(" ");
		}
		
		System.out.println(sb);
	}
}