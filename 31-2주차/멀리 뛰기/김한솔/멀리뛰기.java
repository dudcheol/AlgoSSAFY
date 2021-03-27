/**
 * programmers - 멀리 뛰기
 * 멀리뛰기.java
 * @date 2021-03-27
 * @author hansolKim
 **/

package programmers;

public class 멀리뛰기 {

	public long solution(int n) {
		if (n == 1)
			return 1;
		if (n == 2)
			return 2;

		long[] arr = new long[n + 1];
		arr[1] = 1;
		arr[2] = 2;
		int mod = 1234567;

		for (int i = 3; i <= n; ++i) {
			arr[i] = (arr[i - 1] % mod + arr[i - 2] % mod) % mod;
		}

		return arr[n];
	}

}
