/**
 * Backjoon - 13458. 시험 감독
 * ExamSupervisor_13458.java
 * @date 2020-10-11
 * @author hansolKim
 **/

package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class ExamSupervisor_13458 {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		long answer = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		answer += N;
		
		for(int i=0; i<N; i++) {
			arr[i] -= B;
			if(arr[i]>0) {
				answer += (int)Math.ceil((double)arr[i]/C);
			}
		}
		
		System.out.println(answer);
	}

}
