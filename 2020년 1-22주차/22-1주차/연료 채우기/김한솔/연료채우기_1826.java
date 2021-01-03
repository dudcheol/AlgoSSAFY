/**
 * Backjoon - 1826. 연료 채우기
 * 연료채우기_1826.java
 * @date 2020-12-29
 * @author hansolKim
 **/

package p1000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 연료채우기_1826 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); // 주유소의 개수
		
		ArrayList<int[]> charges = new ArrayList<>();
		
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // 성경이와의 거리
			int b = Integer.parseInt(st.nextToken()); // 연료의 양
			
			charges.add(new int[] {a, b});
		}
		
		Collections.sort(charges, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[0], o2[0]); // 위치 오름차순 정렬
			}
		});
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int cur = 0; // 현재위치
		int dest = Integer.parseInt(st.nextToken()); // 마을과의 거리
		int fuel = Integer.parseInt(st.nextToken()); // 현재 연료의 양
		
		int answer = 0;
		
		boolean isSuccess = true;
		while(true) {
			
			// 현재 목적지에 닿을 수 있는 경우
			if(cur+fuel >= dest) {break;}
			
			// 현재위치에서 갈 수 있는 모든 주유소 탐색
			int nextIdx = -1;
			int addFuel = -1;
			for(int i=0; i<charges.size() && cur+fuel>=charges.get(i)[0]; i++) {
				if(addFuel < charges.get(i)[1]) {
					addFuel = charges.get(i)[1]; // 추가할 연료
					nextIdx = i; // 이동할 인덱스
				}
			}
			
			// 아무곳도 갈 수 없는 경우
			if(nextIdx == -1) {
				isSuccess = false;
				break;
			}
			
			// 갈 수 있는 주유소에서 가장 많은 연료를 얻을 수 있는 곳으로 이동
			int chargeLoc = charges.get(nextIdx)[0]; // 이동한 위치
			
			fuel -= (chargeLoc-cur); // 이동한 칸 만큼 연료소모
			fuel += addFuel; // 해당 주유소에서 연료 추가
			cur = chargeLoc; // 해당 위치로 이동			
			answer++; // 주유소에 들린 횟수 추가 
			charges.remove(nextIdx);
		}
		
		answer = isSuccess ? answer : -1;
		System.out.println(answer);
		br.close();
	}
}