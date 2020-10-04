/**
 * Backjoon - 19236. 청소년 상어
 * JuvenileShark_19236.java
 * @date 2020-10-04
 * @author hansolKim
 **/

package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Fish{
	int x;
	int y;
	int dir;
	
	public Fish(int x, int y, int dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
}

public class JuvenileShark_19236 {

	static int[] dx = {-1,-1,0,1,1,1,0,-1};
	static int[] dy = {0,-1,-1,-1,0,1,1,1};
	static Fish[] fishList;
	static Fish shark;
	static int answer = 0;
	static int[][] map;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		fishList = new Fish[17];
		map = new int[4][4];
		
		for(int i=0; i<4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<4; j++) {
				int id = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());		
				fishList[id] = new Fish(i, j, dir-1);
				map[i][j] = id; 
			}
		}
		
		int startFishIdx = map[0][0]; // 시작점 물고기 번호 
		
		shark = new Fish(0, 0, fishList[startFishIdx].dir);
		fishList[startFishIdx] = null;
		map[0][0] = -1; // 상어 표기
		answer = startFishIdx;
		
		// 전체 시뮬레이션 진행
		dfs(fishList, shark, map, answer);
		
		System.out.println(answer);
	}

	private static void dfs(Fish[] fishList, Fish shark, int[][] map, int ans) {
		int[][] tempMap = new int[4][4];
		for(int i=0; i<4; i++) {
			tempMap[i] = map[i].clone();
		}
		
		Fish[] tempFishList = new Fish[17];
		for(int i=1; i<=16; i++) {
			if(fishList[i] != null)
				tempFishList[i] = new Fish(fishList[i].x, fishList[i].y, fishList[i].dir);	
		}
		
		// 물고기 움직임 구현
		moveFish(tempMap, tempFishList);
		
		// 상어의 움직임 구현
		for(int i=1; i<=4; i++) {
			int nx = shark.x + i*dx[shark.dir%8];
			int ny = shark.y + i*dy[shark.dir%8];
			
			// 영역 밖으로 벗어난 경우 더 이상 진행해도 벗어나므로 --> break
			if(nx<0 || nx>=4 || ny<0 || ny>=4) break;
			
			int targetFishIdx = tempMap[nx][ny];
			if(targetFishIdx > 0) {
				
				// 물고기를 먹는 경우
				tempMap[shark.x][shark.y] = 0;
				Fish tempShark = new Fish(nx, ny, tempFishList[targetFishIdx].dir);
				tempFishList[targetFishIdx] = null; // 잡혀 먹혔으므로 해당 물고기 객체 null표기
				tempMap[nx][ny] = -1; // 상어 표기
				
				dfs(tempFishList, tempShark, tempMap, ans+targetFishIdx);
				
				// 물고기를 먹지 않는 경우 ---> 롤백 되돌리기
				tempMap[nx][ny] = targetFishIdx;
				tempFishList[targetFishIdx] = new Fish(nx, ny, tempShark.dir);
				tempMap[shark.x][shark.y] = -1;
			}
		}
		
		if(answer < ans) {
			answer = ans;
		}
	}

	
	// 물고기의 움직임을 구현하는 메서드
	private static void moveFish(int[][] tempMap, Fish[] tempFishList) {
		for(int i=1; i<=16; i++) {
			Fish fish = tempFishList[i];
			if(fish == null) continue;
			
			int nDir = fish.dir;
			int nx = fish.x + dx[nDir%8];
			int ny = fish.y + dy[nDir%8];
			
			// 상어가 있거나 영역 밖인 경우 반시계방향으로 방향전환
			while(nx<0 || nx>=4 || ny<0 || ny>=4 || tempMap[nx][ny] == -1) {
				nDir++;
				nx = fish.x + dx[nDir%8];
				ny = fish.y + dy[nDir%8];
			}
			
			// 임시 map배열과 fishList에 물고기 위치 교환
			int targetId = tempMap[nx][ny];
			tempMap[fish.x][fish.y] = targetId;
			tempMap[nx][ny] = i;
			
			tempFishList[i] = new Fish(nx, ny, nDir);
			if(tempFishList[targetId] != null) {
				tempFishList[targetId] = new Fish(fish.x, fish.y, tempFishList[targetId].dir);
			}
			
		}
	}

}
