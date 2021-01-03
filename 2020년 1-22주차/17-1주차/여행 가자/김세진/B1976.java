package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1976 {

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int N=Integer.parseInt(br.readLine());
		int M=Integer.parseInt(br.readLine());
		
		int[][] map=new int[N][N];
		for(int i=0;i<N;i++) {
			StringTokenizer stz=new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j]=Integer.parseInt(stz.nextToken());
			}
		}
		
		int[] arr=new int[M];
		StringTokenizer stz=new StringTokenizer(br.readLine());
		for(int i=0;i<M;i++) {
			arr[i]=Integer.parseInt(stz.nextToken());
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j]==0 && i!=j) {
					map[i][j]=10000;
				}
			}
		}
		
		boolean flag=false;
		int index=arr[0]-1;
		
		//가운데
		for(int k=0;k<N;k++) {
			//스타트
			for(int i=0;i<N;i++) {
				//엔드
				for(int j=0;j<N;j++) {
					
					if(map[i][j]>map[i][k]+map[k][j]) {
						map[i][j]=map[i][k]+map[k][j];
					}
				}
			}
		}
		
		for(int i=1;i<M;i++) {
			if(map[index][arr[i]-1]>=10000) {
				flag=true;
				break;
			}else {
				index=arr[i]-1;
			}
		}
		
		if(flag) {
			System.out.println("NO");
		}else {
			System.out.println("YES");
		}
		
	}

}
