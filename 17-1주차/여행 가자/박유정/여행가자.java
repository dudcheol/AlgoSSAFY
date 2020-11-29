package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 여행가자 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		final int inf=1000000;
		int map[][] = new int[n][n];
		int plan[] = new int[m];
		StringTokenizer st;
		
		for (int i = 0; i < n; i++) {
			 st= new StringTokenizer(br.readLine());
			 Arrays.fill(map[i],inf);
			for (int j = 0; j < n; j++) {
				int k = Integer.parseInt(st.nextToken());
				if(k==0)
					continue;
				map[i][j]=k;
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			plan[i]= Integer.parseInt(st.nextToken())-1;
		}
		for (int i = 0; i < n; i++) {//경유지
			for (int j = 0; j < n; j++) {//출발지
				//if(map[j][i]==inf)
				//	continue;
				for (int k = 0; k < n; k++) {//도착지
					if(map[j][k]>map[j][i]+map[i][k])
						map[j][k]=map[j][i]+map[i][k];
				}
			}
		}
		String answer="YES";
		for (int i = 0; i < plan.length-1; i++) {
			if(map[plan[i]][plan[i+1]]==inf) {
				answer="NO";
				break;
			}
		}
		System.out.println(answer);
	}
}
