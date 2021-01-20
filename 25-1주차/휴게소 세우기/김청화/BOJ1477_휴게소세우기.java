package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1477_휴게소세우기 {
	static int N, M, L, rest[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		rest = new int[N + 2];
		rest[0] = 0;
		rest[N + 1] = L;
		for (int i = 1; i <= N; i++) {
			rest[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(rest);
		
		int start = 0, end = L;
		while(start <= end) {
			
			int mid = (start + end) / 2;
			
			int cnt = 0;
			for (int i = 1; i < rest.length; i++) {
				cnt += (rest[i] - rest[i - 1] - 1) / mid; // 휴게소 겹쳐서 세워지는 거 고려하기 위해 1뺌
			}
			
			if(cnt > M) { // M번 보다 더 많이 나눴다는 것 = mid값이 너무 작았다
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		
		System.out.println(start);
	}
}
