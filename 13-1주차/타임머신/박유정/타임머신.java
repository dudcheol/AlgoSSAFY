package study2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;



public class 타임머신 {
	static int[][] arr;
	static int inf=Integer.MAX_VALUE;
	static long longinf=Long.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int road = Integer.parseInt(st.nextToken());
		
		arr = new int[n+1][n+1];
		
		for (int i = 0; i < n+1; i++) {
			Arrays.fill(arr[i],inf);
		}
		
		for (int i = 0; i < road; i++) {
			st = new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			arr[a][b] = Math.min(c,arr[a][b]);// 머야이거..................
		}
		long[] distance=new long[n+1];
		Arrays.fill(distance,longinf);
		distance[1]=0;
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j < distance.length; j++) {//경유지
				if(distance[j]==longinf)
					continue;
				for (int k = 1; k < distance.length; k++) {//도착지
					if(arr[j][k]==inf)
						continue;
					if(distance[k]>distance[j]+arr[j][k]) {//j를 경유해서 k로~~
						distance[k]=distance[j]+arr[j][k];
						if(i==n) {
							System.out.println(-1);
							return;
						}
					}
				}
			}
		}

		for (int i = 2; i < distance.length; i++) {
			if(distance[i]==longinf)
				System.out.println(-1);
			else
				System.out.println(distance[i]);
		}
	}
}
