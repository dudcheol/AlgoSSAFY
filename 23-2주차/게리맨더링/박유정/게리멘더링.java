package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 게리멘더링 {
	static int max = Integer.MAX_VALUE;
	static int N, sum, answer = max;
	static int[] per;
	static boolean[] selected;
	static int[] input;
	static ArrayList<Integer>[] al;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		per = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			per[i] = Integer.parseInt(st.nextToken());
			sum += per[i];
		}

		al = new ArrayList[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			al[i] = new ArrayList<Integer>();
			int k = Integer.parseInt(st.nextToken());
			for (int j = 0; j < k; j++) {
				al[i].add(Integer.parseInt(st.nextToken()) - 1);

			}
		}

		selected = new boolean[N];
		input = new int[N];

		sub(0, 0);

		if (answer == max)
			System.out.println(-1);
		else {
			System.out.println(answer);
		}

	}

	private static void sub(int cnt,  int tcnt) {
		if (cnt == N) {

			if (tcnt == 0 || tcnt == N)// 각 선거구는 적어도 하나의 구역을 포함해야 한다.
				return;

			if (bfs(tcnt, true) && bfs(N - tcnt, false)) {// 모두 연결되어 있다.
				int s = 0;

				for (int i = 0; i < N; i++) {
					if (selected[i]) {// 인구 세기
						s += per[i];
					}
				}
				answer = answer > (int) Math.abs(sum - 2 * s) ? (int) Math.abs(sum - 2 * s) : answer;// 최소
			}
			return;
		}
		selected[cnt] = true;
		sub(cnt + 1,  tcnt + 1);
		selected[cnt] = false;
		sub(cnt + 1,  tcnt);
	}

	private static boolean bfs(int M, boolean flag) {
		Queue<Integer> q = new LinkedList<Integer>();
		boolean visit[] = new boolean[N];

		for (int i = 0; i < N; i++) {
			if (selected[i] == flag) {// 첫번쨰 노드 넣어주고 bfs
				q.add(i);
				visit[i] = true;
				break;
			}
		}
		int cnt = 0;

		while (!q.isEmpty()) {
			int n = q.poll();
			cnt++;

			if (cnt == M) // 부분집합이 모두 이어져있으면
				return true;

			for (int i = 0; i < al[n].size(); i++) {
				int m = al[n].get(i);
				if (visit[m] || selected[m] != flag)
					continue;
				visit[m] = true;
				q.add(m);
			}
		}
		return false;
	}
}
