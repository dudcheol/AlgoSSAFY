/**
 * Backjoon - 1038. 감소하는 수
 * DecreasingNumber_1038.java
 * @date 2020-12-14
 * @author hansolKim
 **/

package p1000;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DecreasingNumber_1038 {

	static int[] input, data;
	static int N, R, CNT, ANSWER;
	static boolean[] selected;
	static boolean isSuccess;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		input = new int[10];
		
		for(int i=0; i<10; i++) { input[i] = i;}
		
		CNT = -1;
		ANSWER = 0;
		isSuccess = false;
		sb = new StringBuilder();
		for(int i=1; i<=10; i++) {
			R = i;
			selected = new boolean[10];
			data = new int[R];
			permutation(0);
			if(isSuccess) { break; }
		}
		
		if(isSuccess) {
			System.out.println(sb.toString());
		} else {
			System.out.println("-1");
		}
			
		br.close();
	}

	private static void permutation(int cnt) {
		if(cnt == R) {
			CNT++;
			if(CNT == N) {
				for(int i=0; i<data.length; i++) {
					sb.append(data[i]);
				}
				isSuccess = true;
			}
			return;
		}
		
		for(int i=0; i<10; i++) {
			if(selected[i]) return;
			
			data[cnt] = i;
			selected[i] = true;
			permutation(cnt+1);
			selected[i] = false;
		}
	}

}
