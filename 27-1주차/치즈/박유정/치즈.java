package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 치즈 {
	static int[][] d = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	static int h, w;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());

		int[][] map = new int[h][w];
		int count = 1;
		int prev = 0;

		for (int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < w; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int time=0;
		
		for (int i = 0; i < h; i++) {// 공기 구별 -1로 채움
			for (int j = 0; j < w; j++) {
				if (map[i][j] == 0 && (i == 0 || j == 0 || i == h - 1 || j == w - 1)) {
					air(map, i, j);
				}
			}
		}
		
		while (true) {//치즈 다 녹을떄까지
			count=0;
						
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (map[i][j] == 1) {//치즈 수
						count++;
					}
				}
			}
			
			if (count == 0) {	//다 녹았으면
				System.out.println(time);
				System.out.println(prev);
				break;
			}
						
			int[][] mapcopy=new int[h][];
			for (int i = 0; i < map.length; i++) {
				mapcopy[i]=map[i].clone();
			}
			
			for (int i = 0; i < h; i++) {// 상하좌우 주변에 공기 있으면 녹는다.
				for (int j = 0; j < w; j++) {
					if (check(map,i,j)) {
						mapcopy[i][j]=-1;
						air(mapcopy,i,j);//주변에 0이 있는지 확인 0->-1
					}
				}
			}
			
			for (int i = 0; i < map.length; i++) {
				map[i]=mapcopy[i];
			}
			
			prev=count;
			time++;
		}
	}



	private static void air(int[][] map, int x, int y) {//공기부분을  -1로바꿔줌
		for (int i = 0; i < 4; i++) {
			int dx = x + d[i][0];
			int dy = y + d[i][1];

			if (dx < 0 || dy < 0 || dx >= h || dy >= w || map[dx][dy] != 0)
				continue;

			map[dx][dy] = -1;
			air(map, dx, dy);
		}
	}
	private static boolean check(int[][] map,  int x, int y) {//상하좌우에 공기 있는지 확인
		for (int i = 0; i < 4; i++) {
			int dx = x + d[i][0];
			int dy = y + d[i][1];

			if (dx < 0 || dy < 0 || dx >= h || dy >= w )
				continue;

			if(map[dx][dy]==-1)
				return true;
		}
		return false;
	}

}
