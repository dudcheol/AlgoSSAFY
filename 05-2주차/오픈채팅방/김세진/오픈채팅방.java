package de;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class 오픈채팅방 {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new String[] { "Enter uid1234 Muzi", "Enter uid4567 Prodo",
				"Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan" })));

	}

	static public String[] solution(String[] record) {
		String[] answer;
		HashMap<String, String> map = new HashMap<String, String>();
		int leng = record.length;
		Queue<String> order = new LinkedList<String>();
		Queue<String> key = new LinkedList<String>();
		ArrayList<String> output = new ArrayList<String>();
		
		for (int i = 0; i < leng; i++) {
			String[] input = record[i].split(" ");
			order.offer(input[0]);
			key.offer(input[1]);
			if (input.length == 3) {
				map.put(input[1], input[2]);
			}
		}
		
		while (!order.isEmpty()) {
			String or=order.peek();
			
			
			StringBuilder sb = new StringBuilder();
			if (or.equals("Enter") ) {
				order.poll();
				sb.append(map.get(key.poll()));
				sb.append("님이 들어왔습니다.");
				output.add(sb.toString());
			}

			if (or.equals("Leave")) {
				order.poll();
				sb.append(map.get(key.poll()));
				sb.append("님이 나갔습니다.");
				output.add(sb.toString());
			}

			if (or.equals("Change")) {
				order.poll();
				key.poll();
			}

		}
		
		answer = new String[output.size()];
		for (int i = 0; i < output.size(); i++) {
			answer[i] = output.get(i);
		}

		return answer;

	}
}
