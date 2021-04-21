/**
 * Backjoon - 10836. 여왕벌
 * 여왕벌_10836.java
 * @date 2021-04-21
 * @author hansolKim
 **/

package p10000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 여왕벌_10836 {

	static int[][] map; // 애벌레의 자라는 정도를 담는 배열
	static int[][] change; // 변화정도를 담는 배열
	static int[][] growPointArr; // 날짜별 성장 값
	static int N, M; // 날짜 수, 격자칸의 크기
	
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		// 기본 배열 세팅
		init();
		
		// growPoint 입력받기
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; ++j) {				
				growPointArr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int day = 0;
		while(day < N) {
			// 1. 왼쪽부분과 윗쪽부분 성장시키기
			int idx = M;
			int growIdx = 0;		
			
			// 왼쪽하단부터 성장시키기
			while(--idx>0) {
				while(growPointArr[day][growIdx]==0) {
					growIdx++;
				}
				
				growPointArr[day][growIdx]--;
				map[idx][0] += growIdx; // 애벌레 성장 반영
				change[idx][0] += growIdx; // 변화 값 저장
			}
			
			while(idx<M) {
				while(growPointArr[day][growIdx]==0) {
					growIdx++;
				}
				
				growPointArr[day][growIdx]--;
				map[0][idx] += growIdx; // 애벌레 성장 반영
				change[0][idx] += growIdx; // 변화 값 저장
				idx++;
			}
			// 3. 날짜 추가
			day++;
		}		
		
		growUpRest();
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; ++i) {
			for(int j=0; j<M; ++j) {
				sb.append(map[i][j] + " ");
			}
			sb.append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static void growUpRest() {				
		for(int i=1; i<M; ++i) {	
			for(int j=1; j<M; ++j) {
				map[i][j] += change[i-1][j];
				change[i][j] = change[i-1][j];
			}
		}
	}

	private static void init() {
		map = new int[M][M];
		change = new int[M][M];
		growPointArr = new int[N][3];
		
		for(int i=0; i<M; ++i) {
			Arrays.fill(map[i], 1);
		}				
	}
}