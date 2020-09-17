/**
 * Backjoon - 19238. 스타트 택시
 * StartTaxi_19238.java
 * @date 2020-09-18
 * @time 00:18~
 * @author hansolKim
 **/

package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class StartTaxi_19238 {

	private static int N, M, fuel; // N: 맵의 크기, M: 손님의 수
	private static int taxiX, taxiY;
	private static int[][] map, person; // 지도
	private static boolean[] finished;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][N+1];
		finished = new boolean[M];
		
		// map 상태 입력
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		taxiX = Integer.parseInt(st.nextToken());
		taxiY = Integer.parseInt(st.nextToken());
		
		person = new int[M][5]; // 0: fromX, 1: fromY, 2: toX, 3: toY, 4: 승객과 목표지점간의 거리
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			person[i][0] = Integer.parseInt(st.nextToken());
			person[i][1] = Integer.parseInt(st.nextToken());
			person[i][2] = Integer.parseInt(st.nextToken());
			person[i][3] = Integer.parseInt(st.nextToken());
			// 승객에서부터 목표까지 거리계산
			person[i][4] = dist(person[i][0], person[i][1], person[i][2], person[i][3]);
			if(person[i][4] == -1) {
				System.out.println(-1);
				return;
			}
		}
		
		boolean noAnswer = false;
		
		main:for(int i=0; i<M; i++) {
			if(finished[i]) continue;
			 
			int targetX = person[i][0]; // 승객의 행
			int targetY = person[i][1]; // 승객의 열
			int targetIdx = i;
			
			int taxiToPerson = dist(taxiX, taxiY, person[i][0], person[i][1]);
			if(taxiToPerson == -1) {
				noAnswer = true;
				break;
			}
			for(int j=0; j<M; j++) {
				if(i == j || finished[j]) continue;				
				
				int tempDist = dist(taxiX, taxiY, person[j][0], person[j][1]);
				if(tempDist == -1) {
					noAnswer = true;
					break main;
				}
				// 현재 택시거리가 더 짧은 경우
				if(tempDist < taxiToPerson) {
					taxiToPerson = tempDist;
					targetX = person[j][0];
					targetY = person[j][1];
					targetIdx = j;
				} else if(tempDist == taxiToPerson) { // 택시거리가 같은경우
					// 행 번호 기준
					if(person[j][0] < targetX) {
						targetX = person[j][0];
						targetY = person[j][1];
						targetIdx = j;
						continue;
						// 택시 거리도 같고 행도 같을 때 열이 작은 경우
					} else if(person[j][0] == targetX && person[j][1] < targetY) {
						targetX = person[j][0];
						targetY = person[j][1];
						targetIdx = j;
					}
				}
			}
			
			// 이 지점에서 승객의 지점이 정해짐
			// 현재 택시에서 승객까지 가는 거리 + 승객이 목표지점까지 가는 거리보다 연료가 적으면 -1 리턴
			if(taxiToPerson + person[targetIdx][4] > fuel) {
				noAnswer = true;
				break;
			}
			
			// 승객 번호 방문처리
			System.out.println(targetIdx);
			finished[targetIdx] = true;

			// fuel 계산
			fuel += (person[targetIdx][4]-taxiToPerson);
			
			// taxi 현재위치 갱신
			taxiX = person[targetIdx][2];
			taxiY = person[targetIdx][3];
			i = -1; // 하.... 이것때문에...
		}
 		
		if(noAnswer) System.out.println(-1);
		else System.out.println(fuel);
		br.close();
	}

	/* 한 좌표에서 목표지점 좌표까지의 최소 거리 계산 메서드(bfs 사용) */
	private static int dist(int fromX, int fromY, int toX, int toY) {
		
		if(fromX == toX && fromY == toY) {
			return 0;
		}
		
		boolean[][] visited = new boolean[N+1][N+1];
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,1,-1};
		
		Queue<int[]> q = new LinkedList<>();
		
		q.add(new int[] {fromX, fromY, 0});
		visited[fromX][fromY] = true;
		
		while(!q.isEmpty()) {
			int[] xy = q.poll();
			
			for(int i=0; i<4; i++) {
				int nx = xy[0] + dx[i];
				int ny = xy[1] + dy[i];
				
				// 영역 밖 및 방문처리 된 지점 예외처리
				if(nx<=0 || nx>N || ny<=0 || ny>N || visited[nx][ny] || map[nx][ny] == 1) continue;

				// 현재 찾는 지점인 경우
				if(nx == toX && ny == toY) {
					return xy[2] + 1;
				}
				
				// 앞으로 간 지점 방문처리 및 큐에 삽입
				visited[nx][ny] = true;
				q.add(new int[] {nx, ny, xy[2]+1}); // `xy[2]+1` 거리 1추가	
			}
		}
		
		return -1; // 갈 수 없는 곳인 경우
		
	}

}
