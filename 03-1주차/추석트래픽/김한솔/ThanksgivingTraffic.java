/**
 * programmers - 2018 카카오 블라인드 채용. 추석트래픽
 * ThanksgivingTraffic.java
 * @date 2020-08-12
 * @author hansolKim
 **/

package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class ThanksgivingTraffic {

	public static void main(String[] args) {
		ThanksgivingTraffic thanksgivingTraffic = new ThanksgivingTraffic();
		String[] lines = {
				"2016-09-15 20:59:57.421 0.351s", 
				"2016-09-15 20:59:58.233 1.181s", 
				"2016-09-15 20:59:58.299 0.8s", 
				"2016-09-15 20:59:58.688 1.041s", 
				"2016-09-15 20:59:59.591 1.412s", 
				"2016-09-15 21:00:00.464 1.466s", 
				"2016-09-15 21:00:00.741 1.581s", 
				"2016-09-15 21:00:00.748 2.31s", 
				"2016-09-15 21:00:00.966 0.381s", 
				"2016-09-15 21:00:02.066 2.62s"
		};
		
		String[] lines2 = {
				"\"2016-09-15 00:00:00.000 3s", 
				"\"2016-09-15 00:00:01.076 3s",
		};
		
		thanksgivingTraffic.solution(lines2);
		
	}
	
	private static int startTime, endTime;
	
	public int solution(String[] lines) {
		int answer = 1;
		
		ArrayList<Integer> startTimeList = new ArrayList<>();
		ArrayList<Integer> endTimeList = new ArrayList<>();
		
		for(int i=0; i<lines.length; i++) {
			StringTokenizer st = new StringTokenizer(lines[i], " ");
			st.nextToken(); // 2016-09-15 버림
			StringTokenizer time = new StringTokenizer(st.nextToken(), ":"); // 01:00:04 -> 01, 00, 04로 토큰 분할
			
			String hh = time.nextToken();
			String mm = time.nextToken();
			
			StringTokenizer st2 = new StringTokenizer(time.nextToken(), "."); // 04.002 -> 04, 002로 토큰 분할
			String ss = st2.nextToken();
			String msStr = st2.nextToken();
			
			int h = (10*(hh.charAt(0)-'0') + (hh.charAt(1)-'0')) * 3600;
			int m = (10*(mm.charAt(0)-'0') + (mm.charAt(1)-'0')) * 60;
			int s = 10*(ss.charAt(0)-'0') + (ss.charAt(1)-'0');
			int ms = 100*(msStr.charAt(0)-'0') + 10*(msStr.charAt(1)-'0') + (msStr.charAt(2)-'0');
			
			endTime = (h + m + s)*1000 + ms; // endTime 정수 계산
		
			StringTokenizer st3 = new StringTokenizer(st.nextToken(), "s");
			double processTime = Double.parseDouble(st3.nextToken()) * 1000;
			
			startTime = (int) (endTime-processTime + 1); // 처리시간은 시작시간과 끝시간 포함하므로 +1 추가 
			
			startTimeList.add(startTime);
			endTimeList.add(endTime);
		}
		
		ArrayList<Integer> tempTimeList = new ArrayList<>();
		tempTimeList.addAll(startTimeList); // ArrayList 깊은 복사
		tempTimeList.addAll(endTimeList);
		
		Collections.sort(tempTimeList); // 오름차순 정렬
		
		for(int i=0; i<tempTimeList.size(); i++) {
			int count = 0;
			startTime = tempTimeList.get(i);
			endTime = startTime + 999;
			
			for(int j=0; j<startTimeList.size(); j++) {
				
				if(endTimeList.get(j) < startTime) { continue; } // 현재 끝시간보다 기준의 시작시간이 더 큰 경우
				
				if(isInTime(startTimeList.get(j), endTimeList.get(j))) { // startTime이 범위 안에 있거나
					count++;
				}
//				
//				if(startTimeList.get(j) > endTime) { // 왜 주석처리 해야될까
//					break;
//				}
			}
			
			answer = Math.max(answer, count);
		}
		
		return answer;
	}

	private boolean isInTime(int start, int end) { // 범위 내 확인
		return ((end == startTime)  // 왼쪽에 걸친 경우 
				|| (start == endTime)  // 오른쪽에 걸친 경우
				|| (start <= startTime && end >= startTime) // 왼쪽 중간에 있는 경우
				|| (start <= endTime && end >= endTime) // 오른쪽 중간에 있는 경우
				|| (start >= startTime && end <= endTime)); // 중간에 들어와 있는 경우
	}
	
}
