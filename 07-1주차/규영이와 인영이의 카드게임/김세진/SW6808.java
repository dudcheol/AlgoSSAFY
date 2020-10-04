package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW6808 {

	static int[] gu;
	static int[] in;
	static boolean[] all;
	static int win;
	static int loose;
	static boolean[] visited;
	static int[] output;
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int T=Integer.parseInt(br.readLine());
		
		
		
		
		for(int TC=1;TC<=T;TC++) {
			
			
			gu=new int[9];
			in=new int[9];
			all=new boolean[19];
			visited=new boolean[9];
			output=new int[9];
			
			StringTokenizer stz=new StringTokenizer(br.readLine());
			
			
			for(int i=0;i<9;i++) {
				gu[i]=Integer.parseInt(stz.nextToken());
				all[gu[i]]=true;
				
			}
			int guindex=0;
			int inindex=0;
			win=0;
			loose=0;
			for(int i=1;i<=18;i++) {
				if(all[i]) {
	
				}else {
					in[inindex++]=i;
				}
			}
			
			recursive(0);
			System.out.println("#"+TC+" "+win+" "+loose);
		}
	}
	
	static int recursive(int index) {
		
		if(index==9) {
			int resultGu=0;
			int resultIn=0;
			for(int i=0;i<9;i++) {
				if(gu[i]>output[i]) {
					resultGu+=gu[i]+output[i];
				}else if(gu[i]<output[i]) {
					resultIn+=gu[i]+output[i];
				}
			}
			
			if(resultGu>resultIn) {
				win++;
			}else {
				loose++;
			}
		}
		
		//9장의 카드중 9장을 뽑는 것이다.
		
		//9장을 뽑음
		for(int i=0;i<in.length;i++) {
			
			//전에 뽑았으면 뽑지 않음
			if(visited[i]) {
				continue;
			}
			visited[i]=true;
			output[index]=in[i];
			recursive(index+1);
			visited[i]=false;
		}
		
		
		
		
		
		return 0;
	}

}
