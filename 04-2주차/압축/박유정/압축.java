import java.util.ArrayList;
import java.util.HashMap;

public class 압축 {
	public int[] solution(String msg) {
		int[] answer = {};
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		ArrayList<Integer> al = new ArrayList<Integer>();
		int index = 26;

		for (int i = 0; i < 26; i++) {// 사전 초기화
			hm.put(Character.toString((char) ('A' + i)), i + 1);
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < msg.length(); i++) {
			sb.append(msg.charAt(i));

			if (!hm.containsKey(sb.toString())) {// 단어가 사전에없음
				hm.put(sb.toString(), ++index);// 사전에 넣기

				sb.delete(sb.length() - 1, sb.length());// 맨뒤 하나지우기
				al.add(hm.get(sb.toString()));// 출력

				sb.delete(0, sb.length());// sb clear
				sb.append(msg.charAt(i));// sb에 현재꺼넣어주기
			}
		}

		al.add(hm.get(sb.toString()));// 마지막 남은 것 넣기(항상 사전에 있는 경우임)

		int j = 0;
		answer = new int[al.size()];

		for (int i : al) {// 배열에 넣기
			answer[j++] = i;
		}

		return answer;
	}
}
