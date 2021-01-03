package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B14499 {
	static int[][] map;
	static int N,M,K;
	static int[] dx= {0,0,-1,1};
	static int[] dy= {1,-1,0,0};
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz=new StringTokenizer(br.readLine());
		N=Integer.parseInt(stz.nextToken());
		M=Integer.parseInt(stz.nextToken());
		
		int startX=Integer.parseInt(stz.nextToken());
		int startY=Integer.parseInt(stz.nextToken());
		K=Integer.parseInt(stz.nextToken());
		
		map=new int[N][M];
		
		for(int i=0;i<N;i++) {
			stz=new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j]=Integer.parseInt(stz.nextToken());
			}
		}
		stz=new StringTokenizer(br.readLine());
		
		//top 1,forward 2,right 3,left 4,back 5,bottom 6; 
		int[] dice=new int[7];
		
		int temp=0;
		//solve
		for(int i=0;i<K;i++) {
			int order=Integer.parseInt(stz.nextToken());	
			
			int rdx=startX+dx[order-1];
			int rdy=startY+dy[order-1];
			
			if(rdx>=N || rdy>=M || rdx<0 || rdy<0) {
				continue;
			}
			
			switch (order) {
			case 1:
				//동쪽으로 이동시
				temp=dice[6];
				dice[6]=dice[3];
				dice[3]=dice[1];
				dice[1]=dice[4];
				dice[4]=temp;
				break;
			case 2:
				//서쪽으로 이동시
				temp=dice[6];
				dice[6]=dice[4];
				dice[4]=dice[1];
				dice[1]=dice[3];
				dice[3]=temp;
				break;
			case 3:
				//북쪽으로 이동시
				temp=dice[6];
				dice[6]=dice[2];
				dice[2]=dice[1];
				dice[1]=dice[5];
				dice[5]=temp;
				break;
			case 4:
				//남쪽으로 이동시
				temp=dice[6];
				dice[6]=dice[5];
				dice[5]=dice[1];
				dice[1]=dice[2];
				dice[2]=temp;
				
				break;
			}
			
			if(map[rdx][rdy]==0) {
				map[rdx][rdy]=dice[6];
			}else {
				dice[6]=map[rdx][rdy];
				map[rdx][rdy]=0;
			}
			System.out.println(dice[1]);
			
			startX=rdx;
			startY=rdy;
		}
	}

}
