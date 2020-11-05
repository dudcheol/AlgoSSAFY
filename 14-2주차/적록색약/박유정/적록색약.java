package study2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 적록색약 {
	static int[][] d = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	static int n;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		char[][] arr1 = new char[n][n];
		char[][] arr2 = new char[n][n];

		for (int i = 0; i < n; i++) {
			arr1[i] = br.readLine().toCharArray();
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(arr1[i][j]=='G')
					arr2[i][j]='R';
				else 
					arr2[i][j]=arr1[i][j];
			}
		}
		
		boolean[][] visit1=new boolean[n][n];
		boolean[][] visit2=new boolean[n][n];
		int normal=0; int notnor=0;
		
		for (int i = 0; i < n; i++) {//정상인
			for (int j = 0; j < n; j++) {
				if(!visit1[i][j]) {
					normal++;
					dfs(visit1,arr1,i,j,arr1[i][j]);
				}
				if(!visit2[i][j]) {
					notnor++;
					dfs(visit2,arr2,i,j,arr2[i][j]);
				}
			}
		}
		System.out.println(normal+" "+notnor);
	}

	private static void dfs(boolean[][] visit, char[][] arr, int x, int y, char color) {
		for (int i = 0; i < 4; i++) {
			int dx= x+d[i][0];
			int dy= y+d[i][1];
			
			if(dx<0||dy<0||dx>=n||dy>=n||visit[dx][dy]||arr[dx][dy]!=color)
				continue;
			
			visit[dx][dy]=true;
			dfs(visit,arr,dx,dy,color);
		}
	}

}
