package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 13458 시험 감독 https://www.acmicpc.net/problem/13458
public class BOJ13458_시험감독 {
	static int N, B, C; // 반 수
	static long[] A;
	static long result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st= new StringTokenizer(br.readLine());
		A = new long[N];
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken()); 
		
		for (int i = 0; i < N; i++) {
			A[i] -= B;
			result += 1;
			if(A[i] / C > 0)
				result += A[i] / C;
			if(A[i] % C > 0)
				result += 1;
		}
		System.out.println(result);
	}
}
