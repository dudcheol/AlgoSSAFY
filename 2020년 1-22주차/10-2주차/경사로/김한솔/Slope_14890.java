/**
 * Backjoon - 14890. 경사로
 * Slope_14890.java
 * @date 2020-10-11
 * @author hansolKim
 **/

package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Slope_14890 {

	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int answer = 0;
		
		int[][] map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int road;
		boolean[][][] isSlope = new boolean[N][N][N];
		
		// 행에서 지나갈 수 있는 길의 개수 검사
		for(int i=0; i<N; i++) {
			road = 1;
			int curHeight = map[i][0]; // 해당 줄의 첫 시작 높이
			for(int j=1; j<N; j++) {
				if(curHeight == map[i][j]) {
					road++;
					continue;
				}
				
				if(curHeight == map[i][j]+1) { // 내리막 경사로
					// L의 길이만큼  map[i][j]높이와 같은 지 확인
					int cnt = 1; // 연속적으로 연결된 갯수
					int tempJ = j;
					isSlope[i][i][tempJ] = true;
					while(tempJ+1<N && map[i][tempJ] == map[i][tempJ+1] && cnt < L && !isSlope[i][i][tempJ+1]) {
						cnt++; tempJ++;
						isSlope[i][i][tempJ] = true;
					}
					
					if(cnt == L) { // 경사로 연결에 성공한 경우
						road += cnt;
						j = tempJ;
						curHeight = map[i][j];
					} else { // 경사로 연결에 실패한 경우
						break;
					}
					
				} else if(curHeight == map[i][j]-1) { // 오르막 경사로
					
					int cnt = 1; // 연속적으로 연결된 갯수
					int tempJ = j-1;
					if(isSlope[i][i][tempJ]) { break;}
					isSlope[i][i][tempJ] = true;
					while(tempJ-1>=0 && map[i][tempJ] == map[i][tempJ-1] && cnt < L && !isSlope[i][i][tempJ-1]) {
						cnt++; tempJ--;
						isSlope[i][i][tempJ] = true;
					}
					
					if(cnt == L) { // 경사로 연결에 성공한 경우
						road++;
						curHeight = map[i][j];
					} else { // 경사로 연결에 실패한 경우
						break;
					}
					
				} else { // 높이가 1이상 차이나는 경우
					break;
				}
			}
			
			if(road == N) { 
				answer++; 
			}
		}
		
		isSlope = new boolean[N][N][N];
		// 열에서 지나갈 수 있는 길의 개수 검사
		for(int i=0; i<N; i++) {
			road = 1;
			int curHeight = map[0][i]; // 해당 줄의 첫 시작 높이
			for(int j=1; j<N; j++) {
				if(curHeight == map[j][i]) {
					road++;
					continue;
				}
				
				if(curHeight == map[j][i]+1) { // 내리막 경사로
					// L의 길이만큼  map[i][j]높이와 같은 지 확인
					int cnt = 1; // 연속적으로 연결된 갯수
					int tempJ = j;
					isSlope[i][tempJ][i] = true;
					while(tempJ+1<N && map[tempJ][i] == map[tempJ+1][i] && cnt < L && !isSlope[i][tempJ+1][i]) {
						cnt++; tempJ++;
						isSlope[i][tempJ][i] = true;
					}
					
					if(cnt == L) { // 경사로 연결에 성공한 경우
						road += cnt;
						j = tempJ;
						curHeight = map[j][i];						
					} else { // 경사로 연결에 실패한 경우
						break;
					}
					
				} else if(curHeight == map[j][i]-1) { // 오르막 경사로
					
					int cnt = 1; // 연속적으로 연결된 갯수
					int tempJ = j-1;
					
					if(isSlope[i][tempJ][i]) { break;}
					isSlope[i][tempJ][i] = true;
					while(tempJ-1>=0 && map[tempJ][i] == map[tempJ-1][i] && cnt < L && !isSlope[i][tempJ-1][i]) {
						cnt++; tempJ--;
						isSlope[i][tempJ][i] = true;
					}
					
					if(cnt == L) { // 경사로 연결에 성공한 경우
						road++;
						curHeight = map[j][i];
					} else { // 경사로 연결에 실패한 경우
						break;
					}
					
				} else { // 높이가 1이상 차이나는 경우
					break;
				}
			}
			
			if(road == N) { 
				answer++; 
			}
		}
		
		System.out.println(answer);
	}

}
