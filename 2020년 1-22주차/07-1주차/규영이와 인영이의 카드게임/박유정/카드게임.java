import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 카드게임 {
	static int[] kyu, in;
	static boolean[] isSelected;
	static int[] permu;
	static int WIN, FAIL, sum = 18 * 19 / 2;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int t = 1; t <= test; t++) {
			sb.append("#").append(t).append(" ");

			kyu = new int[9];// 고정
			in = new int[9];
			permu = new int[9];
			isSelected = new boolean[9];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 9; i++) {
				kyu[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(kyu);
			int indexk = 0;
			int indexi = 0;

			for (int i = 1; i <= 18; i++) {
				if (indexk < 9 && kyu[indexk] == i) {
					indexk++;
					continue;
				}
				in[indexi++] = i;
			}
			WIN = 0;
			FAIL = 0;

			per(0, 0);

			sb.append(WIN).append(" ").append(FAIL).append("\n");
		}
		System.out.println(sb);
	}

	public static void per(int index, int sumk) {
		if (index == 9) {
			if (sum - sumk > sumk)
				FAIL++;
			else if (sum - sumk < sumk)
				WIN++;
			return;
		}
		for (int i = 0; i < 9; i++) {
			if (isSelected[i])
				continue;
			isSelected[i] = true;
			permu[index] = in[i];
			
			if (permu[index] < kyu[index])
				sumk += permu[index] + kyu[index];
			
			per(index + 1, sumk);			
			isSelected[i] = false;
			
			if (permu[index] < kyu[index])
				sumk -= permu[index] + kyu[index];

		}
	}
}
