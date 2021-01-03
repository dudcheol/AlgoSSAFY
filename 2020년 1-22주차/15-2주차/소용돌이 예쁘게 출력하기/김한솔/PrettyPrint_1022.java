/**
 * Backjoon - 1022. 예쁘게 출력하기
 * PrettyPrint_1022.java
 * @date 2020-11-14
 * @author hansolKim
 **/

package p1000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PrettyPrint_1022 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r1 = Integer.parseInt(st.nextToken());
		int c1 = Integer.parseInt(st.nextToken());
		int r2 = Integer.parseInt(st.nextToken());
		int c2 = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[r2-r1+1][c2-c1+1];
		int max = 0;
		for(int i=r1; i<=r2; i++) {
			for(int j=c1; j<=c2; j++) {
				int v=1;
				if(i+j<0 && i<=j) { v = i*i*4+(i-j)+1; } // 상 ---> 4i^2+(i-j)+1
				if(i+j<=0 && i>j) { v = (j*2)*(j*2) + (i-j) + 1;} // 좌 ---> (2j)^2+(i-j)+1
				if(i+j>0 && i>=j) { v = (2*i+1)*(2*i+1)+(j-i);} // 하 ---> (2i+1)^2+(j-i)
				if(i+j>=0 && i<j) { v = (2*j)*(2*j)-(2*j-1)-(i+j);} // 우 ---> (2j)^2-(2j-1)-(i+j)
				
				map[i-r1][j-c1] = v; // 값 넣기
				if(max<v) { max = v;}
			}	
		}
		
		int s = 0;
		while(max>0) {
			s++;
			max/=10;
		}
		
		StringBuilder sb = new StringBuilder();
		String d = "%"+s+"d";
		for(int i=0; i<r2-r1+1; i++) {
			for(int j=0; j<c2-c1+1; j++) {
				sb.append(String.format(d, map[i][j])).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
		br.close();
	}

}
