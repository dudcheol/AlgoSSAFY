package vacation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class 배열돌리기4 {
	static int N,M,K;
	static int answer=Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		 N=Integer.parseInt(st.nextToken())+1;
		 M=Integer.parseInt(st.nextToken())+1;
		 K=Integer.parseInt(st.nextToken());
		
		int[][] arr=new int[N][M];
		for (int i = 1; i < N; i++) {
			st=new StringTokenizer(br.readLine());
			for (int j = 1; j < M; j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		int[][] cal=new int[K][3];
		
		for (int i = 0; i < K; i++) {
			st=new StringTokenizer(br.readLine());
			cal[i][0]=Integer.parseInt(st.nextToken());
			cal[i][1]=Integer.parseInt(st.nextToken());
			cal[i][2]=Integer.parseInt(st.nextToken());
		}
		int[] per=new int[K];
		boolean[] check=new boolean[K];
		
		per(arr,check,per,cal,0);
		
		System.out.println(answer);
		
	}

	private static void per(int[][] arr, boolean[] check, int[] per, int[][] cal, int cnt) {
		if(cnt==K) {
			int[][] arr2=new int[N][M];			
			for (int i = 0; i < N; i++) {
				arr2[i]=arr[i].clone();
			}
			for (int i = 0; i < K; i++) {
				rotation(arr2,cal[per[i]][0],cal[per[i]][1],cal[per[i]][2]);
			}
			
			for (int i = 1; i < N; i++) {//최솟값 구하기
				int sum=0;
				for (int j = 1; j <M; j++) {
					sum+=arr2[i][j];
				}
				answer=answer>sum?sum:answer;
			};
			return;
		}
		for (int i = 0; i < K; i++) {
			if(!check[i]) {
				check[i]=true;
				per[cnt]=i;
				per(arr,check,per,cal,cnt+1);
				check[i]=false;
			}
		}
		
	}
	private static void rotation(int[][] arr, int r, int c, int s) {
		int[][] arr2=new int[N][M];
		
		for (int i = 0; i < N; i++) {
			arr2[i]=arr[i].clone();
		}
		for (int i = 1; i <= s; i++) {
			for (int j = 0; j < i*2; j++) {
				arr[r-i][c-i+j+1]=arr2[r-i][c-i+j];//윗줄
				arr[r-i+j+1][c+i]=arr2[r-i+j][c+i];//오른쪽줄
				arr[r+i][c+i-j-1]=arr2[r+i][c+i-j];//아랫줄
				arr[r+i-j-1][c-i]=arr2[r+i-j][c-i];//왼쪽줄
			}
		}
	}
}
