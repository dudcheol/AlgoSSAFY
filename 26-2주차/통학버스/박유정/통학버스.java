package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 통학버스 {
	static int N, K, S, answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 단지수
		K = Integer.parseInt(st.nextToken());// 정원
		S = Integer.parseInt(st.nextToken());// 학교위치

		PriorityQueue<int[]> left = new PriorityQueue<int[]>((o1, o2) -> o1[0] - o2[0]);// 학교에서 왼쪾 학교에서 먼순으로 정렬
		PriorityQueue<int[]> right = new PriorityQueue<int[]>((o1, o2) -> o2[0] - o1[0]);// 학교에서 오른쪽

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int per = Integer.parseInt(st.nextToken());

			if (x < S)
				left.add(new int[] { x, per });
			else
				right.add(new int[] { x, per });
		}

		bus(left, 0);
		bus(right, 0);
		System.out.println(answer);
	}

	private static void bus(PriorityQueue<int[]> q, int cnt) {
		int prev = S;
		int bus = 0;

		while (!q.isEmpty()) {
			prev = S;
			bus=0;

			while(!q.isEmpty()) {

				int[] arr = q.poll();

				if (bus + arr[1] <= K) {//버스에 모두 태울수 있음
					bus += arr[1];
					answer += Math.abs(prev - arr[0]);
					prev = arr[0];
					if (bus == K)
						break;
				}
				else {//정원초과하면 일부만 태움
					q.add(new int[] {arr[0],bus+arr[1]-K});
					answer += Math.abs(prev - arr[0]);
					prev = arr[0];
					bus=K;
					break;
				}
			
			}
			answer += Math.abs(S - prev);//학교에 데따줌
		}
	}
}
