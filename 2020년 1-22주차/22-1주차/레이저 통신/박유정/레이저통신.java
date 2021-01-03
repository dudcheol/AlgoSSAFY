package vacation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 레이저통신 {
	static int w, h;
	static int sx, sy, ex, ey;
	static int[][] d = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());

		char[][] map = new char[h][w];
		int[][] c=new int[h][w];//거울 갯수 저장할 배열
		
		for (int i = 0; i < c.length; i++) {
			Arrays.fill(c[i], 100000);//1000으로 했더니 틀림
		}
		for (int i = 0; i < h; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		sx = sy = ex = ey = -1;
		label: for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (map[i][j] == 'C') {
					if (sx == -1) {// 시작 좌표저장
						sx = i;
						sy = j;
					} else {// 끝나는 좌표 저장
						ex = i;
						ey = j;
						break label;
					}
				}
			}
		}
		Queue<int[]> q=new LinkedList<int[]>();
		q.add(new int[] {sx,sy,0,0});//시작 좌표, count, 방향
		c[sx][sy]=0;
		
		while(!q.isEmpty()) {
			int[] arr=q.poll();
			int x=arr[0];
			int y=arr[1];
			int count=arr[2];
			int dir=arr[3];
			
			if(x==ex&&y==ey) {//도착지점
				answer=count;
			}
			if(count>=answer)//가지치기
				continue;

			for (int i = 0; i < 4; i++) {
				int dx=x+d[i][0];
				int dy=y+d[i][1];
				
				if(dx<0||dy<0||dx>=h||dy>=w||map[dx][dy]=='*')
					continue;
				//거울의 갯수가 배열에 저장된 값보다 작거나 같을때
				if((x==sx&&y==sy)||(dir==i&&c[dx][dy]>=count)) {//시작점이거나 전과 방향이 같으면(일직선으로감 거울필요 x)
					q.add(new int[] {dx,dy,count,i});
					c[dx][dy]=count;
					
				}else if(dir!=i&&c[dx][dy]>=count+1){//90도회전함
					q.add(new int[] {dx,dy,count+1,i});
					c[dx][dy]=count;
				}
			}
		}
		System.out.println(answer);
	}
}
