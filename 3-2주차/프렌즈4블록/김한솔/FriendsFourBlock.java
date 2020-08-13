/**
 * programmers - 2018 카카오 블라인드 채용. 프렌즈 4블록
 * FriendsFourBlock.java
 * @date 2020-08-14
 * @author hansolKim
 **/

package programmers;

public class FriendsFourBlock {

	private static int[] dx = { 0, 1, 1 }; // 우, 하, 우하
	private static int[] dy = { 1, 0, 1 };
	private static int[][] map, tempMap;
	private static int M, N;
	private static boolean isDeleted = false; // 제거된 블록이 있는 지 확인

	public int solution(int m, int n, String[] board) {
		int answer = 0;

		map = new int[m][n];
		tempMap = new int[m][n];
		M = m;
		N = n; // 전역변수로 사용

		/* map에다 블록데이터 삽입 */
		for (int i = 0; i < m; i++) {
			String blocks = board[i];
			for (int j = 0; j < n; j++) {
				map[i][j] = blocks.charAt(j);
				tempMap[i][j] = blocks.charAt(j);
			}
		}

		while (true) {
			
			isDeleted = false;					

			/* 같은 블록 찾기 */
			for (int i = 0; i < M-1; i++) {
				for (int j = 0; j < N-1; j++) {
					if (map[i][j] != -1) // 없어지지 않은 블록인 경우에만 탐색
						dfs(i, j); // 같은블록 탐색
				}
			}
			if (!isDeleted) {
				break;
			} // 제거된 블록이 없는 경우 탈출

			/* 2*2 같은 블록 제거하기 */
			// 하나의 열 마다 -1(제거할 블록) 제거 -------> 열의 아래서부터 탐색하여 제거된 블록과 유효한 블록과 교체
			for (int i = 0; i < N; i++) { // 모든 열을 돌면서
				for (int j = M - 1; j >= 0; j--) { // 맨 아래서 부터 탐색
					if (tempMap[j][i] == -1) { // 제거할 블록인 경우
						for (int k = j - 1; k >= 0; k--) { // 현재 블록보다 위의 블록부터 탐색
							if (tempMap[k][i] != -1) { // 유효한 블록을 찾은 경우
								tempMap[j][i] = tempMap[k][i];
								tempMap[k][i] = -1;
								break;
							}
						}
					}
				}
				
			}
			
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = tempMap[i][j];
				}
			}
		}

		/* 제거된 블록의 갯수 계산 */
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == -1) {
					answer++;
				}
			}
		}
		
		return answer;
	}

	private void dfs(int x, int y) {
		
		int block = map[x][y]; // 현재 블록  값 
				
		for (int i = 0; i < 3; i++) {
			int rx = x + dx[i];
			int ry = y + dy[i];

			if (!isInMap(rx, ry)) { // 맵을 벗어난 경우
				return;
			}
			
			if(map[rx][ry] != block) {return;} // block이 다른 경우
		}

		isDeleted = true; // 삭제할 블록이 있음을 표기

		tempMap[x][y] = -1; // 제거 블록 표기

		for (int i = 0; i < 3; i++) {
			int rx = x + dx[i];
			int ry = y + dy[i];

			tempMap[rx][ry] = -1; // 임시테이블에 제거 블록 표기
		}
	}

	private boolean isInMap(int x, int y) {
		return (x >= 0 && x < M && y >= 0 && y < N);
	}
}
