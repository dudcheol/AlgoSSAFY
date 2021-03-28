/**
 * Samsung SW Expert - 2477. 차량정비소
 * 차량정비소.java
 * @date 2031-03-28
 * @author hansolKim
 **/

package ssafy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 차량정비소_2477 {

	static int N, M, K, A, B; // 접수창구의 개수, 정비창구의 개수, 고객의 수, 접수 창구번호, 정비 창구번호
	static int answer, remainReception, remainRepair;
	static int[] receptionTime, repairTime, arriveTime; // 각 접수창구 처리시간표, 각 정비창구 처리시간표, 도착 시간표
	static int[][] receptionState, repairState; // 남은 시간
	static int[][] log; // 고객마다 접수창구, 정비창구 번호기록
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=T; ++tc) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			receptionTime = new int[N+1];
			repairTime = new int[M+1];
			arriveTime = new int[K+1];
			log = new int[K+1][2];
			
			st = new StringTokenizer(br.readLine());			
			for(int i=1; i<=N; ++i) { receptionTime[i] = Integer.parseInt(st.nextToken()); }
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=M; ++i) { repairTime[i] = Integer.parseInt(st.nextToken()); }
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=K; ++i) { arriveTime[i] = Integer.parseInt(st.nextToken()); }
			
			int t = 0;
			int idx = 1;
			int finish = 0;
			Queue<Integer> receptionQ = new LinkedList<>();
			Queue<Integer> repairQ = new LinkedList<>();
			receptionState = new int[N+1][2];
			repairState = new int[M+1][2];
			remainReception = N;
			remainRepair = M;
			
			while(true) {
				
				// 1. 현재 시간에 도착한 고객이 있는 지 확인
				while(idx <= K && arriveTime[idx] == t) {					
					receptionQ.add(idx);
					idx++;
				}
				
				// 2. 접수창구를 돌면서 접수처리가 완료된 고객이 있는지 확인
				for(int i=1; i<=N; ++i) {
					// 고객이 있고 접수처리가 완료된 경우
					if(receptionState[i][0] != 0) {
						if(--receptionState[i][1] == 0) {
							repairQ.add(receptionState[i][0]);
							receptionState[i][0] = 0;
							remainReception++;
						}
					}
				}
				
				// 3. 접수창구에 대기하고 있는 고객이 있는 경우
				while(remainReception>0 && !receptionQ.isEmpty()) {
					// 접수창구를 돌면서 비어있는 곳 확인
					
					for(int i=1; i<=N; ++i) {
						if(receptionState[i][0] == 0) { // 비어있는 경우
							receptionState[i][0] = receptionQ.peek(); // 고객번호
							receptionState[i][1] = receptionTime[i]; // 접수시간 초기화
							log[receptionQ.poll()][0] = i; // i번째 접수창구 사용했음을 표기
							remainReception--;
							break;
						}
					}					
				}
				
				// 4. 정비창구를 돌면서 정비가 완료된 고객이 있는지 확인
				for(int i=1; i<=M; ++i) {
					// 정비창구에 고객이 있고 완료된 경우
					if(repairState[i][0] != 0) {
						if(--repairState[i][1] == 0) {
							repairState[i][0] = 0;
							remainRepair++;
							finish++;
						}
					}
				}
				
				// 5. 정비창구에 대기하고 있는 고객이 있는 경우
				while(remainRepair>0 && !repairQ.isEmpty()) {		
					for(int i=1; i<=M; ++i) {
						if(repairState[i][0] == 0) { // 비어있는 경우
							repairState[i][0] = repairQ.peek(); // 고객번호
							repairState[i][1] = repairTime[i]; // 정비시간 초기화							
							log[repairQ.poll()][1] = i; // i번째 정비창구 사용했음을 표기
							remainRepair--;
							break;
						}
					}
				}
				
				// 6. 접수+정비처리가 완료된 고객의 수 확인
				if(finish == K) { break; }				
				t++;
			}
			
			answer = 0;
			for(int i=1; i<=K; ++i) {
				if(log[i][0] == A && log[i][1] == B) { answer += i; } 
			}
			
			answer = answer == 0 ? -1 : answer;
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}