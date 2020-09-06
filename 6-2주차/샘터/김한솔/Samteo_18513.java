/**
 * Backjoon - 18513. 샘터
 * Samteo_18513.java
 * @date 2020-09-05
 * @author hansolKim
 **/

package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Samteo_18513 {

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long answer = 0;
		boolean[][][] build = new boolean[2][10001][10001]; // 음수와 양수구분, 1억을 쪼개서 사용 10000*10000
		
		int N = Integer.parseInt(st.nextToken()); // 치킨 집의 수 입력
		int K = Integer.parseInt(st.nextToken()); // 집의 수 입력
		
		int[] chickenFirstIdx = new int[N]; // 치킨 집의 build 첫 번째 인덱스 저장공간 생성
		int[] chickenSecondIdx = new int[N]; // 치킨 집의 build 두 번째 인덱스 저장공간 생성
		int[] chickenThirdIdx = new int[N]; // 치킨 집의 build 세 번째 인덱스 저장공간 생성
		
		st = new StringTokenizer(br.readLine()); // 치킨집의 위치 라인 입력 및 토큰생성
		
		for(int i=0; i<N; i++) {
			int chicken = Integer.parseInt(st.nextToken());
			if(chicken >= 0) { // 양수인 경우(0포함)
				chickenFirstIdx[i] = 0; // 치킨 집의 build 첫 번째 인덱스 저장
				if(chicken <= 10000) {
					chickenSecondIdx[i] = 0; // 치킨 집의 build 두 번째 인덱스 저장
					chickenThirdIdx[i] = chicken; // 치킨 집의 build 세 번째 인덱스 저장
					build[1][0][chicken] = true; // 해당위치에 치킨집이 차려졌음을 표기(더 이상 이 위치 사용 불가)
				} else {
					int second = chicken/10000; // 두 번째 인덱스 
					int third = chicken%10000; // 세 번째 인덱스
					chickenSecondIdx[i] = second; // 치킨 집의 build 두 번째 인덱스 저장
					chickenThirdIdx[i] = third; // 치킨 집의 build 세 번째 인덱스 저장
					build[1][second][third] = true; // 해당위치에 치킨집이 차려졌음을 표기(더 이상 이 위치 사용 불가)
				}
			} else { // 음수인 경우
				chickenFirstIdx[i] = 1; // 치킨 집의 build 첫 번째 인덱스 저장
				chicken = Math.abs(chicken);
				if(chicken <= 10000) {
					chickenSecondIdx[i] = 0; // 치킨 집의 build 두 번째 인덱스 저장
					chickenThirdIdx[i] = chicken; // 치킨 집의 build 세 번째 인덱스 저장
					build[0][0][chicken] = true; // 해당위치에 치킨집이 차려졌음을 표기(더 이상 이 위치 사용 불가)
				} else {
					int second = chicken/10000; // 두 번째 인덱스 
					int third = chicken%10000; // 세 번째 인덱스 
					chickenSecondIdx[i] = second; // 치킨 집의 build 두 번째 인덱스 저장
					chickenThirdIdx[i] = third; // 치킨 집의 build 세 번째 인덱스 저장
					build[0][second][third] = true; // 해당위치에 치킨집이 차려졌음을 표기(더 이상 이 위치 사용 불가)
				}
			}
		}
		
		main:for(int dist=1; ; dist++) { // 각 치킨집으로부터 가까운 곳부터 집 지을 위치 찾기(해당위치가 비어있는 경우 집 짓기)
			for(int idx=0; idx<N; idx++) { // 치킨집 리스트
				int chickenPosition = 10000*chickenSecondIdx[idx] + chickenThirdIdx[idx]; // 치킨집의 위치를 가져옴
				chickenPosition = (chickenFirstIdx[idx] == 0) ? chickenPosition : chickenPosition*(-1); // 음수 양수 확인
				
				int homePosition = chickenPosition+dist; // 해당 치킨집의 오른쪽 확인
				System.out.println(homePosition);
				
				if(homePosition<100000000) { // 범위를 초과한 경우
					if(homePosition >= 0) { // 새로 집지을 위치가 양수위치인 경우
						if(homePosition <= 10000) {
							if(!build[0][0][homePosition]) { // 해당 위치에 집을 지을 수 있는 경우
								build[0][0][homePosition] = true; // 집을 지었음을 표기
								answer += dist; // 치킨집과의 거리 추가
								K--; // 지어야하는 집의 갯수 -1
							}
						} else {
							int second = homePosition/10000;
							int third = homePosition%10000;
							if(!build[0][second][third]) {
								build[0][second][third] = true;
								answer += dist;
								K--;
							}
						}
					} else { // 새로 집지을 위치가 음수위치인 경우
						homePosition = Math.abs(homePosition);							
						if(homePosition <= 10000) {
							if(!build[1][0][homePosition]) {
								build[1][0][homePosition] = true;
								answer += dist;
								K--;
							}
						} else {
							int second = homePosition/10000;
							int third = homePosition%10000;
							if(!build[1][second][third]) {
								build[1][second][third] = true;
								answer += dist;
								K--;
							}
						}
					}
				}
				if(K == 0) { break main;} // 모든 집을 다 지은 경우
				homePosition = chickenPosition-dist; // 해당 치킨집의 왼쪽 확인
				System.out.println(homePosition);
				if(homePosition>-100000000) { // 범위를 초과한 경우
					if(homePosition >= 0) { // 새로 집지을 위치가 양수위치인 경우
						if(homePosition <= 10000) {
							if(!build[0][0][homePosition]) {
								build[0][0][homePosition] = true;
								answer += dist;
								K--;
							}
						} else {
							int second = homePosition/10000;
							int third = homePosition%10000;
							if(!build[0][second][third]) {
								build[0][second][third] = true;
								answer += dist;
								K--;
							}
						}
					} else { // 새로 집지을 위치가 음수위치인 경우
						homePosition = Math.abs(homePosition);							
						if(homePosition <= 10000) {
							if(!build[1][0][homePosition]) {
								build[1][0][homePosition] = true;
								answer += dist;
								K--;
							}
						} else {
							int second = homePosition/10000;
							int third = homePosition%10000;
							if(!build[1][second][third]) {
								build[1][second][third] = true;
								answer += dist;
								K--;
							}
						}
					}
				}
				
				if(K == 0) { break main;} // 모든 집을 다 지은 경우
				
			}
		}
		
		System.out.println(answer); // 결과 출력
	}

}
