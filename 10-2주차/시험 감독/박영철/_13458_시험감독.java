package week10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _13458_시험감독 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] map = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine(), " ");
		int B = Integer.parseInt(st.nextToken()); // 총감독관 감시 가능 인원
		int C = Integer.parseInt(st.nextToken()); // 부감독관 감시 가능 인원

		// 각 시험장별로 확인
		long min = 0; // << 이거 때문에 허무하게 틀릴때가 너무 많다.. 조심하자!!
		for (int i = 0; i < N; i++) {
			// 총감독관만으로 가능
			if (map[i] <= B) {
				min += 1;
				continue;
			}
			// 총감독관 + 부감독관으로 가능
			int rest = map[i] - B;
			int res = rest / C;
			if (rest % C != 0)
				res += 1;
			
			min += (res + 1);
		}
		
		System.out.println(min);
	}

}
