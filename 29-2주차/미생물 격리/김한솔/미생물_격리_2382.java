/**
 * Samsung SW Expert - 2382. 미생물 격리
 * 미생물_격리_2382.java
 * @date 2031-03-14
 * @author hansolKim
 **/

package ssafy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 미생물_격리_2382 {

	static int N, M, K, answer;
	static int[][][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static Queue<int[]> list; // 미생물 리스트
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
			
		for(int tc=1; tc<=T; ++tc) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 맵의 크기
			M = Integer.parseInt(st.nextToken()); // M초후 결과
			K = Integer.parseInt(st.nextToken()); // 미생물 수
			
			map = new int[N][N][3]; // 미생물 총 값, 미생물 MAX값, 방향
			
			list = new LinkedList<>();
			
			for(int i=0; i<K; ++i) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken())-1;
				
				list.add(new int[] {x, y, cnt, dir});
//				map[x][y][0] = cnt;
//				map[x][y][1] = dir;
			}
			
			// 시뮬레이션 진행
			while(M-->0) {
				// 각각 다음 셀로 이동
				while(!list.isEmpty()) {
					int x = list.peek()[0];
					int y = list.peek()[1];
					int cnt = list.peek()[2];
					int dir = list.poll()[3];
					
					int nx = x + dx[dir];
					int ny = y + dy[dir];
					
					// 약품이 칠해진 곳으로 이동한 경우
					if(nx == 0 || nx == N-1 || ny == 0 || ny == N-1) {
						cnt /= 2;
						dir = dir%2==0 ? dir+1 : dir-1;
					}
					
					// 현재 미생물의 크기가 가장 큰 경우
					if(map[nx][ny][1] < cnt) {
						map[nx][ny][1] = cnt;
						map[nx][ny][2] = dir;
					}
					
					map[nx][ny][0] += cnt;
				}
				
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						if(map[i][j][0] != 0) {
							list.add(new int[] {i, j, map[i][j][0], map[i][j][2]});
							map[i][j][0] = 0;
							map[i][j][1] = 0;
						}
					}
				}
			}
			
			answer = 0;
			while(!list.isEmpty()) {
				answer += list.poll()[2];
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}