/**
 * Backjoon - 17136. 색종이 붙이기
 * 색종이붙이기_17136.java
 * @date 2020-12-21
 * @author hansolKim
 **/

package p17000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 색종이붙이기_17136 {
	
	static int[][] map;
	static int N = 10, answer;
	static int[] COUNT;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		COUNT = new int[6];
		COUNT[1] = 5;
		for(int i=2; i<=5; i++) {
			COUNT[i] = i*i*5 + COUNT[i-1];
		}
		
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 1) {
					cnt++;
				}
			}
		}
		
		answer = Integer.MAX_VALUE;
		
		tryPaper(0, 0, 5, 5, 0, cnt);
		
		answer = answer == Integer.MAX_VALUE ? -1 : answer;
		
		System.out.println(answer);
		br.close();
	}

	private static void tryPaper(int x, int y, int size, int paperCnt, int useCnt, int remain) {
	
		if(answer <= useCnt) { return; } // 현재 사용한 색종이 개수가 이전에 카운팅된 개수보다 많은 경우 -> 더 이상 진행해도 최솟값이 될 수 없다.
		
		if(remain == 0) { // 붙여야 하는 칸이 다 사라진 경우 -> answer에 값 저장
			answer = useCnt;
			return;
		}
		
		if(size == 0) { // 모든 색종이를 소진한 경우
			return;
		}
		
		if(paperCnt==0) { // 현재 사이즈의 종이가 없는 경우 -> 크기가 한 단계 아래인 색종이 사용
			tryPaper(0, 0, size-1, 5, useCnt, remain); 
			return;
		} 	
		if(COUNT[size-1] + (size*size)*paperCnt < remain) { return; } // 현재 남은 색종이보다 붙여야 할 칸이 더 많은 경우
				
		if(x==N-size+1) { // 탐색이 끝난 경우
			tryPaper(0, 0, size-1, 5, useCnt, remain);
			return;
		}
		
		if(y==N-size+1) { 
			tryPaper(x+1, 0, size, paperCnt, useCnt, remain); 
			return;
		}
		
		// 1. 현재 칸이 1이고 현재 크기의 색종이를 사용할 수 있는경우
		if(map[x][y] == 1 && isPut(x, y, size)) {
			// 사용하는 경우
			putPaper(x, y, size, 0);
			tryPaper(x, y+1, size, paperCnt-1, useCnt+1, remain-(size*size));
			// 사용하지 않는 경우
			putPaper(x, y, size, 1);
			tryPaper(x, y+1, size, paperCnt, useCnt, remain);
			
		} else { // 2. 현재 칸이 0인 경우 -> 패스
			tryPaper(x, y+1, size, paperCnt, useCnt, remain);
		}
		
	}

	private static void putPaper(int x, int y, int size, int type) {
		for(int i=x; i<x+size; i++) {
			for(int j=y; j<y+size; j++) {
				map[i][j] = type;
			}
		}		
	}

	private static boolean isPut(int x, int y, int size) {
		boolean result = true;
		for(int i=x; i<x+size; i++) {
			for(int j=y; j<y+size; j++) {
				if(map[i][j] == 0) {
					result = false;
					break;
				}
			}
		}
		
		return result;
	}
}
