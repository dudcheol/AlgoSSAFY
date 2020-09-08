import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 보호필름 {
	static int D, W, K, min = Integer.MAX_VALUE;
	static int[][] film;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int t = 1; t <= test; t++) {

			sb.append("#").append(t).append(" ");

			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			film = new int[D][W];

			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			min = Integer.MAX_VALUE;

			int result = check(film);
			if (result == -1) {
				sb.append(0).append("\n");
				continue;
			}

			back(0, 1, film);
			sb.append(min).append("\n");
		}
		System.out.println(sb);
	}

	public static void back(int start, int count, int[][] copy) {
		if (start == D)
			return;

		int[][] copy2;
		int[][] copy1;

		copy1 = arraycopy(copy);// 2차원배열에서 clone그냥 쓰면 x
		copy2 = arraycopy(copy);

		change(start, 1, copy2);
		change(start, 0, copy1);

		int res1 = check(copy1);
		int res2 = check(copy2);

		if (res1 == -1 || res2 == -1) {
			min = Math.min(min, count);
			return;
		} else {
			back(start + 1, count, copy);
			back(start + 1, count + 1, copy1);
			back(start + 1, count + 1, copy2);
		}

	}

	public static int[][] arraycopy(int[][] arr) {
		int[][] copy = new int[D][W];
		for (int i = 0; i < D; i++) {
			for (int j = 0; j < W; j++) {
				copy[i][j] = arr[i][j];
			}
		}
		return copy;
	}

	public static int check(int[][] copy) {
		for (int i = 0; i < W; i++) {
			int counta = 0;
			int countb = 0;
			boolean flag = false;
			for (int j = 0; j < D; j++) {
				if (copy[j][i] == 0) {
					countb = 0;
					counta++;
				} else {
					counta = 0;
					countb++;
				}
				if (counta >= K || countb >= K) {
					flag = true;
					break;
				}
			}
			if (!flag)
				return i;

		}
		return -1;
	}

	public static void change(int w, int cell, int[][] copy) {
		for (int i = 0; i < W; i++) {
			copy[w][i] = cell;
		}
	}

}
