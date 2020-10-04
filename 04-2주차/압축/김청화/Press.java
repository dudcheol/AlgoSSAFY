package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://programmers.co.kr/learn/courses/30/lessons/17684
public class Press {

	public static void main(String[] args) {
		
		String msg = "KAKAO";
		//String msg = "TOBEORNOTTOBEORTOBEORNOT";

		int[] answer = solution(msg);

		System.out.println(Arrays.toString(answer));
	}

	public static int[] solution(String msg) {
		int[] answer; 
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		String tmp;


		Map<String,Integer> msgdic = new HashMap<String,Integer>();
		boolean isEnd = false;
		int idx = 27;
		
		// 1. A ~ Z까지 사전에 넣어줌 (각각 색인 번호로)
		for (int i = 0; i < idx; i++) { // 아스키 코드 A = 65
			msgdic.put(String.valueOf((char)(i + 64)), i);
		}
		
		// 2. 받은 msg에서 겹치는 게 있는지 확인
		for (int i = 0; i < msg.length(); i++) {
			int j = 1;		
			int cnt = 0;
			tmp = msg.substring(i, i + j);	
			// 해시맵에 겹치는 문장이 있으면 msg길이 늘리고 추가
			while(msgdic.containsKey(tmp)) {		
				j++;
				//System.out.println(tmp);
				if(i + j - 1 == msg.length()) {
					isEnd = true;
					break;
				}
				tmp = msg.substring(i, i + j);
				cnt++;
			}

			if(isEnd) { // 마지막
				list.add(msgdic.get(msg.substring(i)));
				break;
			}
			
			msgdic.put(tmp, idx++);
			list.add(msgdic.get(msg.substring(i, i + j - 1)));
			j--;
			i += cnt - 1; // KA O 와 같은 경우
		}
		
		answer = new int[list.size()];
		for (int i = 0; i < answer.length; i++) {
			answer[i] = list.get(i);
		}

		return answer;
	}

}