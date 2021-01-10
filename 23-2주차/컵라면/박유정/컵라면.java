package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 컵라면 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[][] arr = new int[N][2];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {//데드라인 빠른순, 컵라면 많은순
				if (o1[0] == o2[0]) {
					return o2[1] - o1[1];
				}
				return o1[0] - o2[0];
			}
		});

		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		q.add(arr[0][1]);// 첫번째것 넣어줌
		int time = 1;// 현재시각

		for (int i = 1; i < N; i++) {
			if (arr[i][0] > time) {// 데드라인 마감안된것(현재시각 보다 큰것)
				q.add(arr[i][1]);
				time++;
			}

			else if (q.peek() < arr[i][1]) {// 데드라인이 마감되었고, 컵라면이 더 이득이면 옛날꺼 빼고 넣어줌
				q.poll();// 옛날꺼 빼줌
				q.add(arr[i][1]);//현재꺼로 대신
			}
		}
		int sum = 0;
		for (int n : q) {
			sum += n;
		}
		System.out.println(sum);
	}
}
