package programmers;
//조합으로 풀어보려 했으나 실패!!!
//dp시러요
import java.util.Arrays;

public class 멀리뛰기 {
	long[] fact = new long[2001];

	public long solution(int n) {
		long answer = 0;
		fact[1] = 1;
		fact[0] = 1;
		answer++;// 모두 1인경우

		for (int i = 1; i <= n / 2; i++) {
			answer += fact(n - i) / (fact(i) * fact(n-2*i));//중복조합
			answer%=1234567;
		}
		System.out.println(answer);

		return answer % 1234567;
	}

	private long fact(int n) {
		if (fact[n] != 0)
			return fact[n];
		return fact[n] = n * fact(n - 1);
	}

	public static void main(String[] args) {
		멀리뛰기 s = new 멀리뛰기();
		s.solution(3);

	}

}
