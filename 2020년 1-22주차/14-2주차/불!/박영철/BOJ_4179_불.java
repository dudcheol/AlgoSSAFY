package week14;

import java.util.*;
import java.io.*;

public class BOJ_4179_불 {
	
	private static final int JIHOON = 1;
	private static final int FIRE = 2;
	private static int R,C;
	private static char[][] map;
	private static int[] dy = {-1,1,0,0};
	private static int[] dx = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][];
		
		int[] jpos = new int[2];
//		int[] fpos = new int[2]; 불이 입력으로 하나만 주어진다는 보장은 없다
		ArrayList<int[]> fires = new ArrayList<>();
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if(map[i][j] == 'J') {
					jpos[0] = i;
					jpos[1] = j;
				} else if(map[i][j] == 'F') {
					fires.add(new int[] {i,j});
				}
			}
		}
		
		int answer = 0;
		System.out.println((answer = bfs(jpos, fires)) == -1 ? "IMPOSSIBLE" : answer + 1);
	}

	private static int bfs(int[] jpos, ArrayList<int[]> fires) {
		Queue<int[]> q = new LinkedList<>();
		
		for (int[] fire : fires) // 불을 먼저 퍼뜨려야 한다. test case 맨 아래에 있음
			q.offer(new int[] {fire[0], fire[1], FIRE, 0});
		q.offer(new int[] {jpos[0], jpos[1], JIHOON, 0});
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			int y = p[0];
			int x = p[1];
			int who = p[2];
			int time = p[3];
			
			if(who == JIHOON && (y==0 || y==R-1 || x==0 || x==C-1)) return time;
			
			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				if(ny<0||nx<0||ny>=R||nx>=C) continue;
				if(who == JIHOON) {
					if(map[ny][nx] == '#' || map[ny][nx] == 'F' || map[ny][nx] == 'J') continue;
					map[ny][nx] = 'J';
					q.offer(new int[] {ny, nx, JIHOON, time+1});
				} else {
					if(map[ny][nx] == '#' || map[ny][nx] == 'F') continue;
					map[ny][nx] = 'F';
					q.offer(new int[] {ny, nx, FIRE, time+1});
				}
			}
		}
		
		return -1;
	}

}

/*
3 3
###
#J#
#.F

answer : IMPOSSIBLE
*/