/**
 * Samsung SW Expert - 3349. 최솟값으로 이동하기
 * MoveMinDistance_3349.java
 * @date 2020-09-16
 * @author hansolKim
 **/

package ssafy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MoveMinDistance_3349 {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수 입력

		for (int test_case = 1; test_case <= T; test_case++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int W = Integer.parseInt(st.nextToken()); // W 입력
			int H = Integer.parseInt(st.nextToken()); // H 입력
			int N = Integer.parseInt(st.nextToken()); // N 입력
			
			ArrayList<int[]> list = new ArrayList<>();
			
			int answer = 0;
			
			for(int i=0; i<N; i++) { // 거쳐가야하는 좌표 값 입력 후 list에 삽입
				st = new StringTokenizer(br.readLine()); 
				list.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
			}
			
			int curX = list.get(0)[0]; // 현재 x좌표
			int curY = list.get(0)[1]; // 현재 y좌표
			
			for(int i=1; i<N; i++) {
				int toX = list.get(i)[0];
				int toY = list.get(i)[1];
				
				// 1. 목적지 좌표값이 (x,y)둘다 크거나 둘다 작은 경우 ---> 대각선 사용
				if((curX-toX)*(curY-toY) > 0) {
					// 대각선 사용
					// int diagonal = Math.min((curX-toX), (curY-toY)); // x, y중에 가까운 것의 거리만큼 대각선 사용
					//answer += Math.max((curX-toX), (curY-toY)) - diagonal;
					//answer += diagonal;
					answer += Math.max(Math.abs(curX-toX), Math.abs(curY-toY));
				} else { // 2. 나머지 경우는 ---> 대각선 사용 x 이므로 거리차 절대값 계산
					answer += Math.abs(curX-toX) + Math.abs(curY-toY);
				}
				
				curX = toX;
				curY = toY;
			}
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}

}
