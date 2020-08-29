package week5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 오픈채팅방 {

    public String[] solution(String[] record) {
        ArrayList<String> memoCmds = new ArrayList<>();
        ArrayList<String> memoUids = new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();

        int cnt = 0;
        for (String line : record) {
            // 명령어 파싱
            StringTokenizer st = new StringTokenizer(line);
            String cmd = st.nextToken();
            String uid = st.nextToken();
            memoCmds.add(cmd); // cmd 기억
            memoUids.add(uid); // cmd 기억

            if (cmd.equals("Leave")) continue;
            String nickname = st.nextToken();
            cnt++;

            map.put(uid, nickname);
        }

        // memo한 cmd를 가지고 result 생성
        StringBuilder sb = new StringBuilder();
        ArrayList<String> answer = new ArrayList<>();

        for (int i = 0; i < record.length; i++) {
            switch (memoCmds.get(i)) {
                case "Enter":
                    sb.append(map.get(memoUids.get(i))).append("님이 들어왔습니다.");
                    break;
                case "Leave":
                    sb.append(map.get(memoUids.get(i))).append("님이 나갔습니다.");
                    break;
                case "Change":
                    continue;
            }
            answer.add(sb.toString());
            sb.setLength(0);
        }
        return answer.toArray(new String[answer.size()]);
    }

    public static void main(String[] args) {
        오픈채팅방 c = new 오픈채팅방();

        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"};

        System.out.println(Arrays.toString(c.solution(record)));
    }
}


/*
import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        String[] answer = {};
		ArrayList<String> answerL = new ArrayList<String>();
		HashMap<String, String> map = new HashMap<>();
		int size = record.length;
		String[][] rec = new String[size][3];
		for (int i = 0; i < size; i++) {
			rec[i] = record[i].split(" ");
		}

		// 매칭
		for (int i = 0; i < size; i++) {
			switch (rec[i][0]) {
			case "Enter":
				map.put(rec[i][1], rec[i][2]);
				break;
			case "Change":
				map.put(rec[i][1], rec[i][2]);
				break;
			}
		}

		// 메시지 출력
		for (int i = 0; i < size; i++) {
			switch (rec[i][0]) {
			case "Enter":
				answerL.add(map.get(rec[i][1])+"님이 들어왔습니다.");
				break;
			case "Leave":
				answerL.add(map.get(rec[i][1])+"님이 나갔습니다.");
				break;
			}
		}

		return answerL.toArray(new String[answerL.size()]);
    }
}
 */