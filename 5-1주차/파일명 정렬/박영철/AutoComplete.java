package week5;

import java.util.Arrays;

public class AutoComplete {

    public int solution(String[] words) {
        int answer = 0;

        // 단어를 사전 순 정렬
        Arrays.sort(words);

        String pre = "";
        int preCnt = 1;
        int preSimilar = 0;
        for (String cur : words) {
            int sameCnt = 0;
            boolean isSimilar = false;
            // pre와 cur를 비교한다
            for (int i = 0; i < pre.length(); i++) {
                if (pre.charAt(i) == cur.charAt(i)) {
                    // 같다면 카운트해주어야 한다.
                    sameCnt++;
                    isSimilar = true;
                } else {
                    // 다르다면 비교를 종료한다.
                    break;
                }
            }

            // 현재 단어가 전 단어와 유사하다면
            // 전 단어를 찾기 위해서는 현재 단어와 같았던 길이 만큼은 입력을 해주어야 찾을 수 있다.
            if (isSimilar && sameCnt >= preSimilar) {
                answer -= preCnt; // 전 단어 입력 횟수를 새롭게 더해주기 위해 빼줌
                // 전 단어를 찾기 위해 입력해야 하는 단어의 수 업데이트
                if (pre.length() > sameCnt) {
                    // 전 단어의 길이가 현재 단어와 같은 갯수보다 크다면
                    // 같은 갯숩보다 1개 더 많은 입력을 해야 검색할 수 있다
                    answer += (sameCnt + 1);
                } else {
                    // 전 단어의 길이가 현재 단어와 같은 갯수와 같다면
                    // 전 단어는 단어를 전부 입력해야 찾을 수 있다
                    answer += (sameCnt);
                }
                // 현재 단어를 찾기 위해 입력해야 하는 단어의 수 더하기
                answer += (preCnt = sameCnt + 1);
            } else {
                // 현재 단어는 전 단어와 덜 유사하므로
                // 같았던 부분보다 1개 더 많은 입력을 해주면 찾을 수 있다
                answer += (preCnt = sameCnt + 1);
            }
            pre = cur;
            preSimilar = sameCnt;
        }

        return answer;
    }

    public static void main(String[] args) {
        AutoComplete autoComplete = new AutoComplete();

        String[] words = {"go", "gone", "guild"};
        String[] words2 = {"abc", "def", "ghi", "jklm"};
        String[] words3 = {"word", "war", "warrior", "world"};
        System.out.println(autoComplete.solution(words));
        System.out.println(autoComplete.solution(words2));
        System.out.println(autoComplete.solution(words3));
    }
}
