package week4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 7:22 시작
 * 7:53 끝
 */
public class Compression {

    public int[] solution(String msg) {
        int size = msg.length();
        ArrayList<Integer> answerL = new ArrayList<>();
        HashMap<String, Integer> dic = new HashMap<>();

        // 길이가 1인 모든 단어를 포함하도록 사전을 초기화한다.
        for (int i = 0; i < 26; i++) {
            char key = (char) ('A' + i);
            dic.put(String.valueOf(key), i + 1);
        }

        int index = 27;
        for (int i = 0; i < size; i++) {
            StringBuilder target = new StringBuilder();
            String pre = "";
            target.append(msg.charAt(i));
            // 사전에서 현재 입력과 일치하는 가장 긴 문자열 w를 찾는다.
            while (dic.containsKey(target.toString())) {
                pre = target.toString();
                if (++i >= size) break;
                target.append(msg.charAt(i));
            }
            // w에 해당하는 사전의 색인 번호를 출력하고, 입력에서 w를 제거한다.
            answerL.add(dic.get(pre));
            // 입력에서 처리되지 않은 다음 글자가 남아있다면(c), w+c에 해당하는 단어를 사전에 등록한다.
            dic.put(target.toString(), index++);
            // 단계 2로 돌아간다.
            --i;
        }

        int ansSize = answerL.size();
        int[] answer = new int[ansSize];
        for (int i = 0; i < ansSize; i++)
            answer[i] = answerL.get(i);

        return answer;
    }

    public static void main(String[] args) {
        Compression compression = new Compression();
        String msg = "ABABABABABABABAB";
        System.out.println(Arrays.toString(compression.solution(msg)));
    }
}
