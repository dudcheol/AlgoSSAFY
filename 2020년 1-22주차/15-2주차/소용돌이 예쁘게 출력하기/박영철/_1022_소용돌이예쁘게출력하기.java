package week15;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1022_소용돌이예쁘게출력하기 {
	
	static int r1,c1,r2,c2,answer[][];
	static int[] dy = {0,-1,0,1}; // 우상좌하
	static int[] dx = {1,0,-1,0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		r1 = Integer.parseInt(st.nextToken());
		c1 = Integer.parseInt(st.nextToken());
		r2 = Integer.parseInt(st.nextToken());
		c2 = Integer.parseInt(st.nextToken());
		
		// 배열 미리 생성
		int R = r2-r1+1;
		int C = c2-c1+1;
		answer = new int[R][C];
		int FULL = R*C;
		
		// 처음에 0,0으로 가기위한 초기화 작업 
		int r=0,c=-1; // 0,-1부터 시작
		int d = 0; // 0:우, 1:상, 2:좌, 3:하
		int dcnt = 0; // 방향이동 횟수
		int mcnt = -1; // 이동한 칸 수
		int limit = 1; // 현재 방향으로 이동가능한 칸 수
		int num = 1;
		int fullcnt = 0;
		
		while(true) {
			if(mcnt == limit) {
				mcnt = 0;
				d = (d+1)%4; // 이동방향 변경
				if(++dcnt==2) { // 2번 이동했으면 limit이 1 증가함
					limit++;
					dcnt=0;
				}
			}
			r += dy[d];
			c += dx[d];
			
			if(r1<=r&&r<=r2 && c1<=c&&c<=c2) {
				answer[r-r1][c-c1] = num;
				if(++fullcnt == FULL) break;
			}
			mcnt++;
			num++;
		}
		
		String format = "%"+Integer.toString(num).length()+"d";
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sb.append(String.format(format, answer[i][j])).append(' ');
			}
			sb.append('\n');
		}
		
		System.out.print(sb);
	}

}
