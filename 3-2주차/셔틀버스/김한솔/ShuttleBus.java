/**
 * programmers - 2018 카카오 블라인드 채용. 셔틀버스
 * ShuttleBus.java
 * @date 2020-08-15
 * @author hansolKim
 **/

package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class ShuttleBus {

	public static void main(String[] args) {
		ShuttleBus shuttleBus = new ShuttleBus();
		
		String[] timetable = {"08:00", "08:01", "08:02", "08:03"};
		String[] timetable2 = {"09:10", "09:09", "08:00"};
		String[] timetable3 = {"09:00", "09:00","09:00","09:00"};
		String[] timetable4 = {"00:01", "00:01","00:01","00:01","00:01"};
		String[] timetable5 = {"23:59"};
		String[] timetable6 = {"23:59","23:59","23:59","23:59","23:59","23:59","23:59","23:59","23:59","23:59","23:59","23:59","23:59","23:59","23:59","23:59"};
		shuttleBus.solution(2, 1, 2, timetable3 );
	}

	private static ArrayList<Integer> timeList;
	private static int cornTime; // 콘의 대기 시간
	private static int idx = 0; // 탑승 대기 승객 idx
	
	public String solution(int n, int t, int m, String[] timetable) {
        
        int busTime = 540; // 09:00을 뜻함
        timeList = new ArrayList<>();
        
        
        for(int i=0; i<timetable.length; i++) {
        	StringTokenizer st = new StringTokenizer(timetable[i], ":");
        	int hh = Integer.parseInt(st.nextToken());
        	int mm = Integer.parseInt(st.nextToken());
        	
        	timeList.add((hh*60) + mm);
        }
        
        Collections.sort(timeList);
        
        main:for(int i=1; i<=n; i++) {
        	
        	/* 마지막 버스인 경우 */
        	if(i==n) { 
        		
        		// 콘이 탈 자리가 있는 지 여부 확인 
        		for(int j=1; j<=m; j++) {
        			if(idx < timeList.size()) {
        				if(timeList.get(idx) <= busTime) {
        					if(j == m) { // corn이 탈 자리가 없는 경우 ---> 승객 한명을 밀어내고 타야함
        						cornTime = timeList.get(idx) - 1;
        						break main;
        					}
        					idx++;
        				} else { // 자리가 있는 경우
        					cornTime = busTime;
        				}
        			}
        		}
        		cornTime = busTime;
        		break;
        	}
        	
        	/* 버스가 여유 있는 경우 */
    		for(int j=0; j<m; j++) {
    			if(idx < timeList.size()) { // 기다리는 승객이 존재하는 경우
    				if(timeList.get(idx) <= busTime) { // 기다리는 승객이 현재 버스를 탈 수 있는 경우
    					idx++;
    				} else { // 탈 수 없는 경우 -> 뒤에 기다리는 승객도 못타므로 for-loop 탈출
    					break;
    				}
    			}
    		}
        	
        	/* 다음버스로 이동 */
        	busTime+=t;
        }
        
        /* (시간)Integer ---> String형으로 변환 */
        int HH = cornTime/60;
        int mm = cornTime - HH*60;
        
        StringBuilder sb = new StringBuilder();
        if(HH < 10) {
        	sb.append(0).append(HH);
        } else {
        	sb.append(HH);
        }
        
        sb.append(":");
        if(mm < 10) {
        	sb.append(0).append(mm);
        } else {
        	sb.append(mm);
        }
        
        return sb.toString();
    }

}
