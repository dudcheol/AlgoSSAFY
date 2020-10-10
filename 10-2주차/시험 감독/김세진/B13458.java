package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B13458 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
	
		StringTokenizer stz = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(stz.nextToken());
		}

		stz = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(stz.nextToken());
		int C = Integer.parseInt(stz.nextToken());

		

		// 우선 각 시험장마다 감독관 1명이 있어야 한다.
		long count = N;


		for (int i = 0; i < N; i++) {
			arr[i]-=B;
			if(arr[i]<0) {
				arr[i]=0;
			}
			
			count+=arr[i]/C;
			
			if((arr[i])%C>0) {
				count+=1;
			}
			
				
			
		}
		
		System.out.println(count);
	}

}
