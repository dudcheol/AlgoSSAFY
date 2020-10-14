package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 주사위귤리기 {
	static int N, M;
	static int[][] map;
	static int[] die;
	static int[][] d = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };// 동서북남

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());

		int order = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		die = new int[6];//위 앞 오 뒤 왼 아

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 북남일때는 방향바뀌지않음
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < order; i++) {
			int o = Integer.parseInt(st.nextToken());

			x +=  d[o - 1][0];
			y +=  d[o - 1][1];

			if (x < 0 || y < 0 || x >= N || y >= M) {
				x -=  d[o - 1][0];
				y -=  d[o - 1][1];
				continue;
			}
			roll(o);
			if(map[x][y]==0) {
				map[x][y]=die[5];
			}else {
				die[5]=map[x][y];
				map[x][y]=0;
			}
			System.out.println(die[0]);
		}
	}
	private static void roll(int o) {
		if (o==1) {//오른쪽0254
			int temp=die[0];
			die[0]=die[2];
			die[2]=die[5];
			die[5]=die[4];
			die[4]=temp;
		}else if(o==2) {//왼쪽0452
			int temp=die[0];
			die[0]=die[4];
			die[4]=die[5];
			die[5]=die[2];
			die[2]=temp;
		}else if(o==3) {//위0351
			int temp=die[0];
			die[0]=die[3];
			die[3]=die[5];
			die[5]=die[1];
			die[1]=temp;
		}else if(o==4) {//아래0153
			int temp=die[0];
			die[0]=die[1];
			die[1]=die[5];
			die[5]=die[3];
			die[3]=temp;
		}
	}
}
