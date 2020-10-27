/**
 * Backjoon - 14719. 빗물
 * Rainwater_14719.java
 * @date 2020-10-27
 * @author hansolKim
 **/

package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Rainwater_14719 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		int[] map = new int[W];
		int[] left = new int[W];
		int[] right = new int[W];
		int[] newMap = new int[W];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<W; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		// 왼쪽에서 부터 시작한 배열 갱신
		int curHeight = 0;
		for(int i=0; i<W; i++) {
			if(curHeight<map[i]) { curHeight = map[i]; }
			left[i] = curHeight;
		}
		
		// 오른쪽 값 계산
		curHeight = 0;
		for(int i=W-1; i>=0; i--) {
			if(curHeight<map[i]) { curHeight = map[i]; }
			right[i] = curHeight;
		}
		
		for(int i=0; i<W; i++) { newMap[i] = Math.min(left[i], right[i]); }
		
		int answer = 0;
		for(int i=0; i<W; i++) { answer+=(newMap[i]-map[i]); }
		System.out.println(answer);
	}

}
