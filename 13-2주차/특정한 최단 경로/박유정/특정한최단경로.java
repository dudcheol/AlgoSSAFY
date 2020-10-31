package study2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class 특정한최단경로 {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		int N=Integer.parseInt(st.nextToken());
		int E=Integer.parseInt(st.nextToken());
		
		int INF=1001;//
		
		int[][] arr=new int[N][N];
		
		for (int i = 0; i < N; i++) {
			Arrays.fill(arr[i],INF);
			arr[i][i]=0;
		}
		
		for (int i = 0; i < E; i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken())-1;
			int b=Integer.parseInt(st.nextToken())-1;
			int cost=Integer.parseInt(st.nextToken());
			
			arr[a][b]=arr[b][a]=cost;
			
		}
		st=new StringTokenizer(br.readLine());
		int a=Integer.parseInt(st.nextToken())-1;
		int b=Integer.parseInt(st.nextToken())-1;
		
		for (int i = 0; i < N; i++) {//경유지
			for (int j = 0; j < N; j++) {//출발지
				for (int k = 0; k < N; k++) {//도착지
					if(arr[j][k]>arr[j][i]+arr[i][k]) {
						arr[j][k]=arr[j][i]+arr[i][k];
					}
				}
			}
		}
		int answer=Math.min(arr[0][a]+arr[a][b]+arr[b][N-1],arr[0][b]+arr[b][a]+arr[a][N-1]); 
		if(answer>=INF)
			System.out.println(-1);
		else {
			System.out.println(answer);
			}
	}
}
