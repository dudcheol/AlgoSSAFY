/**
 * Backjoon - 1700. 멀티탭 스케줄링
 * 멀티탭스케줄링_1700.java
 * @date 2021-01-24
 * @author hansolKim
 **/

package p1000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 멀티탭스케줄링_1700 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] flugList = new int[K];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<K; i++) {
			flugList[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] multitap = new int[N];
		int answer = 0;
		int useCnt = 0;
		
		for(int i=0; i<K; i++) {
			
			int flug = flugList[i];
			
			boolean isUse = false;
			// 1. 현재 꽂혀있는 플러그인 경우
			for(int j=0; j<useCnt; j++) {
				if(multitap[j] == flug) {
					isUse = true;
					break;
				}
			}
			
			if(!isUse) {
				if(useCnt<N) {
					multitap[useCnt++] = flug;
				} else { 
					if(!isUse) {
						int changeIdx = -1;
						int term = -1;
						for(int j=0; j<useCnt; j++) {
							int cur = multitap[j];
							boolean reuse = false;
							for(int k=i+1; k<K; k++) {
								if(cur == flugList[k]) {
									if(term < k) {
										term = k;
										changeIdx = j;
									}
									reuse = true;
									break;
								}
							}
							
							if(!reuse) {
								changeIdx = j;
								break;
							}
						}
						
						multitap[changeIdx] = flug;
						answer++;
					}
				}
			}
			
		}
		
		
		System.out.println(answer);
		br.close();
	}
}
