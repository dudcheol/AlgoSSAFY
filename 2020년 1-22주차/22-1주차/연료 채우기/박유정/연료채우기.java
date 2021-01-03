package vacation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 연료채우기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int[][] oil = new int[N][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			oil[i][0] = Integer.parseInt(st.nextToken());
			oil[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(oil,new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0]-o2[0];
			}});
		st = new StringTokenizer(br.readLine());
		int town = Integer.parseInt(st.nextToken());
		int cur = Integer.parseInt(st.nextToken());

		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
		int index = 0;
		int answer = 0;

		while (true) {
			for (;index < N &&  cur >= oil[index][0]; index++) { //현재 충전한 기름으로 갈수있는 거리 안에있는 주유소
				pq.add(oil[index][1]);
			}
			if (cur >= town) {//마을 도착
				System.out.println(answer);
				break;
			} else if (pq.isEmpty()) {//주유소없으면 
				System.out.println(-1);
				break;
			} else { //주유소충전하기....갈수있는 거리중에서 가장 많이 충전할수 있는 주유소
				cur += pq.poll();
				answer++;
			}
		}
	}
}
