package algorithm.swea.D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _6808_규영이와인영이의카드게임 {
	static int[] gyuCards;
	static int[] inCards;
	static boolean[] used;
	static boolean[] visited;
	static int[] selected;
	static int gyuWin;
	static int inWin;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		gyuCards = new int[9];
		inCards = new int[9];
		selected = new int[9];

		for (int test_case = 1; test_case <= TC; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			used = new boolean[19];
			visited = new boolean[9];
			gyuWin = 0;
			inWin = 0;
			
			// 규영이 카드
			for (int i = 0; i < 9; i++) {
				gyuCards[i] = Integer.parseInt(st.nextToken());
				used[gyuCards[i]] = true;
			}

			// 인영이 카드
			int idx = 0;
			for (int i = 1; i <= 18; i++) {
				if (!used[i])
					inCards[idx++] = i;
			}

			perm(0);
			sb.append('#').append(test_case).append(' ').append(gyuWin).append(' ').append(inWin).append('\n');
		}

		System.out.print(sb);

	}

	private static void perm(int k) {
		if (k == 9) {
			simulate();
			return;
		}

		for (int i = 0; i < 9; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			selected[k] = i;
			perm(k + 1);
			visited[i] = false;
		}

	}

	private static void simulate() {
		int gyuSum = 0;
		int inSum = 0;

		for (int i = 0; i < 9; i++) {
			int gyu = gyuCards[i];
			int in = inCards[selected[i]];

			int sum = gyu + in;
			if (gyu > in) {
				gyuSum += sum;
			} else {
				inSum += sum;
			}
		}

		if (gyuSum > inSum) {
			gyuWin++;
		} else if (gyuSum < inSum) {
			inWin++;
		}
	}

}
