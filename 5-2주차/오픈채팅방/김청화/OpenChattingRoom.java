package programmers;

import java.util.ArrayList;
import java.util.HashMap;

// 프로그래머스 오픈채팅방 https://programmers.co.kr/learn/courses/30/lessons/42888
public class OpenChattingRoom {
	
	static String[] record = { "Enter uid1234 Muzi", "Enter uid4567 Prodo", 
			"Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan" };
	public static void main(String args[]){
		
		String[] result = solution(record);
		for(int i = 0; i < result.length; i++){
			System.out.println(result[i]);
		}
	}
		public static String[] solution(String[] record) {
			String[] answer;
			HashMap<String, String> map = new HashMap<String, String>();
			ArrayList<User> users = new ArrayList<User>();
			
			String uid, name;
			for(int i = 0; i < record.length; i++) {
				String action = record[i].split(" ")[0];
				if(action.equals("Enter")){
					uid = record[i].split(" ")[1];
					name = record[i].split(" ")[2];
					map.put(uid, name);
					action = "님이 들어왔습니다.";
					users.add(new User(uid, action));
				}
				if(action.equals("Leave")){
					uid = record[i].split(" ")[1];
					name = map.get(uid);
					action = "님이 나갔습니다.";
					users.add(new User(uid, action));
				}
				if(action.equals("Change")){
					uid = record[i].split(" ")[1];
					name = record[i].split(" ")[2];
					map.put(uid, name);
				}
			}

			answer = new String [users.size()];
			for(int i = 0; i < answer.length; i++){
				String idx = users.get(i).uid;
				answer[i] = map.get(idx) + users.get(i).action;
			}
			return answer;
		}
		
		static class User {
			String uid;
			String action;
			
			User(String uid, String action){
				this.uid = uid;
				this.action = action;
			}
		}

}
