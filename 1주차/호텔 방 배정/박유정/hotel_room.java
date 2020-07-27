import java.util.*;

class hotel_room {
	public long[] solution(long k, long[] room_number) {
		int size = room_number.length;
		long[] answer = new long[size];
		HashMap<Long, Long> hm = new HashMap<>(); // 자식 (차있는방),부모 (빈방)

		for (int i = 0; i < size; i++) {

			long room = room_number[i];

			// 방비어있는경우
			if (!hm.containsKey(room)) {
				hm.put(room, room + 1);// 부모노드를 방이 차있는 것과 관계없이 room+1로 일단 저장.
				answer[i] = room;
			}

			// 이미 방이 차있는경우
			else {
				Long value = hm.get(room);// 부모노드값 받아옴
				ArrayList<Long> list = new ArrayList<>();
				while (hm.containsKey(value)) { // 부모노드가 키값으로 없을 때 까지 돈다.-->빈방 찾음
					list.add(value);// 부모노드값 바꿔줄 방번호
					value = hm.get(value); // 부모노드에 있는 값을 얻어온다.
				}
				hm.put(value, value + 1);
				answer[i] = value;

				for (long l : list) {// 자기보다 큰값에 한해서 리셋
					hm.put(l, value + 1);
				}

			}
		}
		return answer;
	}

}
