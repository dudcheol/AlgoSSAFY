package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ13460_구슬탈출2 {
	static int N, M, dirs[][] = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} }; // 상하좌우
	static char[][] map;
	static int rx, ry, bx, by, result = Integer.MAX_VALUE;
	static boolean rIn, bIn;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j] == 'R') {
					rx = i;
					ry = j;
				}
				if(map[i][j] == 'B') {
					bx = i;
					by = j;
				}
			}
		}
		
		for (int i = 0; i < 4; i++) {
			check(rx, ry, bx, by, i, 1);
		}
		
		if(result == Integer.MAX_VALUE) 
			System.out.println(-1);
		else 
			System.out.println(result);
	}

	private static void check(int rx, int ry, int bx, int by, int dir, int cnt) {
		if(cnt > 10) return;
		
		rIn = false;
		bIn = false;
		
		int rnx = rx;
		int rny = ry;
		while(map[rnx][rny] != '#') {
			rnx += dirs[dir][0];
			rny += dirs[dir][1];
			
			if(map[rnx][rny] == 'O') {
				rIn = true;
				break;
			}
		}
		rnx -= dirs[dir][0];
		rny -= dirs[dir][1];
		
		int bnx = bx;
		int bny = by;
		while(map[bnx][bny] != '#') {
			bnx += dirs[dir][0];
			bny += dirs[dir][1];
			
			if(map[bnx][bny] == 'O') {
				bIn = true;
				break;
			}
		}
		bnx -= dirs[dir][0];
		bny -= dirs[dir][1];
		
		if(bIn) return;
		else {
			// 빨간 공만 빠졌으면
			if(rIn) {
				if(result > cnt)
					result = cnt;
				return;
			}
		}
		
		// 빨간 공, 파란 공 위치 같으면
		if(rnx == bnx && rny == bny) {
			// 이동 거리따지기
			int rmove = Math.abs(rnx - rx) + Math.abs(rny - ry); 
			int bmove = Math.abs(bnx - bx) + Math.abs(bny - by); 
			
			// 이동 거리 더 크면 더 뒤에 있는 공
			if(rmove > bmove) {
				rnx -= dirs[dir][0];
				rny -= dirs[dir][1];
			} else if(bmove > rmove) {
				bnx -= dirs[dir][0];
				bny -= dirs[dir][1];
			}
			
		}
		
		for (int i = 0; i < 4; i++) {
			// 현재 방향이 이전 방향과 같다면 체크할 필요 없음
			if(i == dir)
				continue;
			check(rnx, rny, bnx, bny, i, cnt + 1);
		}
	}

}
