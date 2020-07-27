/**
 * 
 * HotelRoom.java
 * 2020-07-27
 * @author hansolKim
 * 
 **/

package intern_winter_2019;

import java.util.HashMap;

public class HotelRoom {

	public static HashMap<Long, Long> map = new HashMap<>();
	
	public long[] solution(long k, long[] room_number) {
		
		/**
		 * 1. 한 번에 한 명씩 신청한 순서대로 방을 배정합니다.
		 * 2. 고객은 투숙하기 원하는 방 번호를 제출합니다.
		 * 3. 고객이 원하는 방이 비어 있다면 즉시 배정합니다.
		 * 4. 고객이 원하는 방이 이미 배정되어 있으면 원하는 방보다 번호가 크면서 비어있는 방 중 가장 번호가 작은 방을 배정합니다.
         * 
         * KeyPoint! Union-find문제
		 * */
		long[] answer = new long[room_number.length];

		for(int i=0; i<room_number.length; i++) {
			
			long rNumber = room_number[i];
			answer[i] = getNextRoom(rNumber);

		}
		
        return answer;
    }
	
	public static long getNextRoom(long number) {
		if(!map.containsKey(number)) { // 다음 방을 가리킬 방이 사용하지 않은 방인 경우
			map.put(number, number+1); // 다음 방 연결
			return number;
		}
		
		// 방을 사용하고 있는 경우 -> 재귀적으로 update
		map.put(number, getNextRoom(map.get(number)));
		return map.get(number);
	}

}
