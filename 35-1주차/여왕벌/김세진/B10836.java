import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B10836 {

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz=new StringTokenizer(br.readLine());
		
		int N=Integer.parseInt(stz.nextToken());
		int M=Integer.parseInt(stz.nextToken());
		
		
		int[][] map=new int[N][N];
		
		//init

		for(int i=0;i<N;i++) {
			Arrays.fill(map[i], 1);
		}
		
		
		//로직을 어떻게 짤것인가?
		
		//M을 입력받고 순서대로 진행하는 방식대로 하자
		
		for(int i=0;i<M;i++) {
			stz=new StringTokenizer(br.readLine());
			
			//1의개수 2의개수 3의 개수
			
			int zero=Integer.parseInt(stz.nextToken());
			int one=Integer.parseInt(stz.nextToken());
			int two=Integer.parseInt(stz.nextToken());
			
			//day 마다 바꾸는거
			
			//왼쪽
			for(int j=N-1;j>-1;j--) {
				if(zero>0) {
					map[j][0]+=0;
					zero--;
					continue;
				}
				
				if(one>0) {
					map[j][0]+=1;
					one--;
					continue;
				}
				if(two>0) {
					map[j][0]+=2;
					two--;
					continue;
				}
				
			}
			//상단
			for(int j=1;j<N;j++) {
				if(zero>0) {
					map[0][j]+=0;
					zero--;
					continue;
				}
				
				if(one>0) {
					map[0][j]+=1;
					one--;
					continue;
				}
				if(two>0) {
					map[0][j]+=2;
					two--;
					continue;
				}
			}	
		}
		
		for(int j=1;j<N;j++) {
			for(int k=1;k<N;k++) {
				
				//비교 
				int max=Math.max(map[j-1][k-1], Math.max(map[j][k-1], map[j-1][k]));
				map[j][k]=max;
				
			}
		}
		
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
//				System.out.print(map[i][j]+" ");
				sb.append(map[i][j]+" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
