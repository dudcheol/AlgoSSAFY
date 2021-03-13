/**
 * programmers - 광고 삽입
 * 광고_삽입.java
 * @date 2021-03-13
 * @author hansolKim
 **/

package programmers;

import java.util.StringTokenizer;

public class 광고_삽입 {

	public static void main(String[] args) {
		광고_삽입 광고_삽입 = new 광고_삽입();
		String play_time = "99:59:59";
		String adv_time = "25:00:00";
		String[] logs = {"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"};
		광고_삽입.solution(play_time, adv_time, logs);

	}
	
	public String solution(String play_time, String adv_time, String[] logs) {
        
        // 1. 시간 파싱해서 수치화
        int playTime = timeToInt(play_time);
        int advTime = timeToInt(adv_time);
        
        // 2. 누적합을 저장할 배열 생성
        int[] sum = new int[playTime+1];
        
        for(String log : logs) {
        	StringTokenizer st = new StringTokenizer(log, "-");
        	int startTime = timeToInt(st.nextToken());
        	int finishTime = timeToInt(st.nextToken());
        	sum[startTime]++; sum[finishTime]--;
        }
        
        // 3. sum배열에서 각 지점마다 시청 수 계산
        for(int i=1; i<=playTime; i++) {
        	sum[i] += sum[i-1];
        }
        
        // 4. sum배열에서 각 지점마다 시청 누적 수 계산
        for(int i=1; i<=playTime; i++) {
        	sum[i] += sum[i-1];
        }
        
        // 5. 누적합이 가장 큰 시작점 찾기
        int start = 0;
        long max = sum[advTime];         
        for(int i=advTime; i<=playTime; i++) {        	
        	if(sum[i] - sum[i-advTime] > max) {
        		start = i-advTime+1;
        		max = sum[i] - sum[i-advTime];
        	}
        }

        String answer = timeToString(start);        
        return answer;
    }

	private String timeToString(int time) {
		int h = time/3600;
		time -= 3600 * h;
		int m = time/60;
		time -= 60 * m;
		int s = time;
				
		return String.format("%02d:%02d:%02d", h, m, s);
	}

	private int timeToInt(String play_time) {
		StringTokenizer st = new StringTokenizer(play_time, ":");
		int h = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		return h*3600 + m*60+s;
	}
}