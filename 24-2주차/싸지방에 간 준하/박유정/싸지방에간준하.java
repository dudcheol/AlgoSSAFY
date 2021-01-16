package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 싸지방에간준하 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[][] com = new int[N][2];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			com[i][0] = Integer.parseInt(st.nextToken());
			com[i][1] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(com, new Comparator<int[]>() {// 시작시간으로 정렬
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});

		PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {//현재 사용중( 끝나는 시간으로 정렬[끝나는시간, 컴퓨터 이용번호])
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});

		PriorityQueue<Integer> num = new PriorityQueue<>();// 비어있는 좌석(비어있는 좌석중에 가장 번호가 낮은걸로 앉아야함!!!!!!!!!!)
		int index = 0;

		int[] per = new int[N];// 컴퓨터 이용수
		for (int i = 0; i < N; i++) {
			while (!q.isEmpty() && q.peek()[0] <= com[i][0]) {// 이용시간 끝났으면 좌석 비우기
				num.add(q.poll()[1]);
			}
			if (!num.isEmpty()) {//좌석이 비어있으면
				int n = num.poll();
				q.add(new int[] { com[i][1], n });
				per[n]++;
			} else {// 비어있는 좌석이 없으면 새로운 좌석 추가
				q.add(new int[] { com[i][1], index });
				per[index++]++;
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append(index).append("\n");
		for (int i = 0; i < index; i++) {
			sb.append(per[i]).append(" ");
		}
		System.out.println(sb);
	}
}
