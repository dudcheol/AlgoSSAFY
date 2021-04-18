package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 11:30~12:38
public class _2174_로봇_시뮬레이션 {

	private static int A, B, N, M;
	private static int[] dy = { -1, 1, 0, 0 };
	private static int[] dx = { 0, 0, -1, 1 };
	private static Robot[][] map;

	private static class Robot {
		int no; // 로봇번호
		int dir; // 로봇 방향

		public Robot(int no, int dir) {
			this.no = no;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new Robot[B + 1][A + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			char sdir = st.nextToken().charAt(0);
			int dir = convertDir(sdir);
			map[y][x] = new Robot(i + 1, dir);
		}

		// 명령
		for (int cmd = 0; cmd < M; cmd++) {
			st = new StringTokenizer(br.readLine());
			int target = Integer.parseInt(st.nextToken());
			char tcmd = st.nextToken().charAt(0);
			int cnt = Integer.parseInt(st.nextToken());
			
			// target 찾기
			Robot r = null;
			int rx=-1;
			int ry=-1;
			loop:for (int i = 1; i <= B; i++) {
				for (int j = 1; j <= A; j++) {
					if(map[i][j]==null) continue;
					if(map[i][j].no==target) {
//						r=new Robot(map[i][j].no, map[i][j].dir);
						r=map[i][j];
						rx=j;
						ry=i;
						break loop;
					}
				}
			}
			
			// 찾은 로봇 명령수행하기
			for (int k = 0; k < cnt; k++) {
				switch (tcmd) {
				case 'L':
					r.dir = rotateR(r.dir); // 상하반전됐으므로 L,R 도 바뀌어야함
					break;
				case 'R':
					r.dir = rotateL(r.dir); // 상하반전됐으므로 L,R 도 바뀌어야함
					break;
				case 'F': // 이동
					map[ry][rx] = null; //원래자리 비움
					ry += dy[r.dir];
					rx += dx[r.dir];
					
					if(ry<=0||rx<=0||ry>B||rx>A) {
						System.out.println("Robot "+ r.no +" crashes into the wall");
						return;
					}
					
					if(map[ry][rx]!=null) {
						System.out.println("Robot "+r.no+" crashes into robot "+map[ry][rx].no);
						return;
					}
					
					map[ry][rx] = r; //이동될자리에 넣음
					break;
				}
			}
		}
		System.out.println("OK");
	}

	private static int rotateL(int dir) {
		switch (dir) {
		case 0:
			return 2;
		case 1:
			return 3;
		case 2:
			return 1;
		case 3:
			return 0;
		}
		return -1;
	}
	
	private static int rotateR(int dir) {
		switch (dir) {
		case 0:
			return 3;
		case 1:
			return 2;
		case 2:
			return 0;
		case 3:
			return 1;
		}
		return -1;
	}

	private static int convertDir(char sdir) {
		switch (sdir) {
		case 'N': // 맵 방향이 좌하부터 시작하므로
			return 1;
		case 'S':
			return 0;
		case 'W':
			return 2;
		case 'E':
			return 3;
		}
		return -1;
	}

}
