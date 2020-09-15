package study;

import java.io.*;
import java.util.*;

public class 홈방범서비스 {
	static int N,M,count;
	static int[][] map;
	static int[] dx= {0,0,1,-1};
	static int[] dy= {1,-1,0,0};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int t=Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		
		for (int test = 1; test <= t; test++) {
			
			st=new StringTokenizer(br.readLine());
			sb.append("#").append(test).append(" ");
			
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			
			map=new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st=new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			int max=0;
			int sum=0;		
			
			for (int k = 1; k <=N+1; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						
						count=0;
						
						dfs(i,j,k);
						
						if(max<count) {
							sum=k*k+(k-1)*(k-1);//운영 비용
							if(count*M-sum>=0) {	//손해보지않으면 MAX에 넣어줌
								max=count;
							}
						}
					}
				}
			
			}
			sb.append(max).append("\n");
		}
		
		System.out.println(sb);
	}
	private static void dfs(int i, int j, int k) {
		int result;
		for (int m = 0; m < N; m++) {
			for (int n = 0; n < N; n++) {
				result=Math.abs(m-i)+Math.abs(n-j);//거리가 계산
				if(result<=k-1) {//방범 범위 안에 있으면 k-1-> 자기자신일때 자기자신빼면 0이지만 k=1
					if(map[m][n]==1)//집이면
						count++;
				}
			}
		}
	}
}
