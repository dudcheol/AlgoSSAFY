/**
 * programmers - 2019 카카오 블라인드 채용. 오픈채팅방
 * OpenChattingRoom.java
 * @date 2020-08-29
 * @author hansolKim
 **/

package programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class OpenChattingRoom {

	public static void main(String[] args) {
		OpenChattingRoom ocr = new OpenChattingRoom();
		String[] record = { "Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo",
				"Change uid4567 Ryan" };
		ocr.solution(record);
	}

	public String[] solution(String[] record) {
		String[] answer = {};
		List<String> commandList = new ArrayList<>();
		List<String> userIdList = new ArrayList<>();
		List<String> nicknameList = new ArrayList<>();
		List<String> result = new ArrayList<>();
		Map<String, String> nameMap = new HashMap<>();
		int size = record.length;

		/* 자료구조에 넣기위한 기초작업 */
		for (int i = 0; i < size; i++) {
			StringTokenizer st = new StringTokenizer(record[i]);
			String command = st.nextToken();
			commandList.add(command);
			userIdList.add(st.nextToken());
			if (command.equals("Leave")) {
				nicknameList.add("#");
			} else {
				nicknameList.add(st.nextToken());
			}
		}

		/* 유저Id를 키로하여 가장 마지막에 사용한 닉네임을 map에 삽입 */
		for (int i = size - 1; i >= 0; i--) {
			if(nicknameList.get(i).equals("#")) {continue;} // 'Leave'인 경우는 아무 작업을 하지 않음
			
			if (!nameMap.containsKey(userIdList.get(i))) {
				nameMap.put(userIdList.get(i), nicknameList.get(i));
			}
		}

		StringBuilder sb = new StringBuilder();

		// result결과 만들기
		for (int i = 0; i < size; i++) {

			sb.replace(0, sb.length(), "");

			sb.append(nameMap.get(userIdList.get(i)));

			/* command 결과 만들기 */
			if (commandList.get(i).equals("Enter")) { // 채팅방에 들어온 경우
				sb.append("님이 들어왔습니다.");
			} else if (commandList.get(i).equals("Leave")) { // 채팅방에 나간 경우
				sb.append("님이 나갔습니다.");
			} else { // 이름 변경한 경우
				continue;
			}

			result.add(sb.toString());
		}

		answer = new String[result.size()];

		for (int i = 0; i < result.size(); i++) {
			answer[i] = result.get(i);
			System.out.println(answer[i]);
		}

		return answer;
	}

}
