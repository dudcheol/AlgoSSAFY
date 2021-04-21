package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _10836_여왕벌 {

	private static int M,N,map[][],cmd[];
	private static int[] dr = {-1,0}; //상우
	private static int[] dc = {0,1}; //상우
	private static int[] dy = {0,-1,-1}; //좌 좌상 상
	private static int[] dx = {-1,-1,0}; //좌 좌상 상
	

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M=Integer.parseInt(st.nextToken());
		N=Integer.parseInt(st.nextToken());
		map=new int[M][M];
		for (int i = 0; i < M; i++) {
			Arrays.fill(map[i], 1);
		}
		
		int cmdLen = M*2-1;
		cmd = new int[3];
		for (int i = 0; i < N; i++) {
			
			// 좌,상 성장시키기
			st=new StringTokenizer(br.readLine());
			int zero = Integer.parseInt(st.nextToken());
			int one = Integer.parseInt(st.nextToken());
			int two = Integer.parseInt(st.nextToken());
			
			for (int j = 1; j <= M; j++) {
				if(zero>0) {
					map[M-j][0]+=0;
					zero--;
					continue;
				}
				if(one>0) {
					map[M-j][0]+=1;
					one--;
					continue;
				}
				if(two>0) {
					map[M-j][0]+=2;
					two--;
					continue;
				}
			}
			
			for (int j = 1; j < M; j++) {
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
			
//			for (int j = 0; j < 3; j++) {
//				cmd[j] = Integer.parseInt(st.nextToken());
//			}
//			
//			int nr=M-1;
//			int nc=0;
//			int add=0;
//			int idx=0;
//			for (int j = 0; j < M; j++) { // 상
//				while(idx<=3 && cmd[idx]==0) {
//					idx++;
//					add++;
//				}
//				map[nr][nc]+=add;
//				nr+=dr[0];
//				nc+=dc[0];
//				cmd[idx]--;
//			}
//			nr-=dr[0];
//			nc-=dc[0];
//			for (int j = M; j < cmdLen; j++) { // 우
//				while(idx<=3 && cmd[idx]==0) {
//					idx++;
//					add++;
//				}
//				nr+=dr[1];
//				nc+=dc[1];
//				map[nr][nc]+=add;
//				cmd[idx]--;
//			}
		}
		
		// 나머지 칸 채우기
		for (int i = 1; i < M; i++) {
			for (int j = 1; j < M; j++) {
				map[i][j] = map[i-1][j];
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(map[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}

//	private static int findMaxDiff(int i, int j, int[][] map) {
//		int max=0;
//		for (int d = 0; d < 3; d++) {
//			int ny=i+dy[d];
//			int nx=j+dx[d];
//			max=Math.max(max, map[ny][nx]);
//		}
//		return max;
//	}

}
