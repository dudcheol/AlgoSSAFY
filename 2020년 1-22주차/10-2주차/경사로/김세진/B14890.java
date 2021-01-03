package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14890 {

	static int N,L;
	static int[][] map;
	static boolean[] visit;
	static int count;
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer stz=new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(stz.nextToken());
		L=Integer.parseInt(stz.nextToken());
		
		map=new int[N][N];
		
		
		for(int i=0;i<N;i++) {
			stz=new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j]=Integer.parseInt(stz.nextToken());
			}
		}
		
		for(int i=0;i<N;i++) {
			if(solve(i, 0, true)) {
				count++;
			}
			if(solve(0, i, false)) {
				count++;
			}
			
		}
		
		System.out.println(count);
		
	}
	
	//true면 세로줄  false면 가로줄
	public static boolean solve(int x,int y,boolean flag) {
		//라인을 떼서 확인할 수 있는 배열 선언
		int[] arr=new int[N];
		visit=new boolean[N];
		
		// 세로줄 - 가로줄 구분
		for(int i=0;i<N;i++) {
			if(flag) {
				arr[i]=map[x][y+i];
			}else {
				arr[i]=map[x+i][y];
			}
		}
		
		
		for(int i=0;i<N-1;i++) {
			
			//높이가 같음
			if(arr[i]==arr[i+1]) {
				continue;
			}
			
			//차이가 1이상
			if(Math.abs(arr[i]-arr[i+1])>1) {
				return false;
			}
			
			//내려가는 길일 경우
			if((arr[i])-1 == arr[i+1]) {
				//경사로 시작점 i+1부터 i+L(경사로길이) 까지 
				for(int j=i+1;j<=i+L;j++) {
					
					//맵을 벗어나거나 , 높이 차이가 있거나 , 먼저 경사로를 깔아놓은 경우
					if(j>=N || arr[i+1] != arr[j] || visit[j]) {
						return false;
					}
					visit[j]=true;
				}
			}
			
			//올라가는 길일 경우
			else if((arr[i]+1) ==arr[i+1]) {
				//경사로 시작점 i에서 경사로 끝점까지 
				for(int j=i;j>i-L;j--) {
					//맵을 벗어나거나 , 높이차이가 있거나, 먼저 경사로를 깔아놓은 경우
					if(j<0|| arr[i] != arr[j] || visit[j]) {
						return false;
					}
					visit[j]=true;
				}
			}
		}
		return true;
	}

	
}
