package week1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class JewelryShopping {

    static int[] solution(String[] gems) {
        int size = Integer.MAX_VALUE;
        int answerS = 0, answerE = 0;

        // gems size 1~100,000
        // 종류 찾기
        HashMap<String, Integer> map = new HashMap<>();
        for (String gem : gems) {
            if (!map.containsKey(gem)) {
                map.put(gem, 0);
            }
        }

        // 포인터 2개로 접근
        int start = 0, end = 0;

        map.put(gems[start], 1);

        while (true) {
            boolean flag = map.containsValue(0);

            if (map.size() == size || (end == gems.length - 1 && flag)) break;

            if (flag) {
                // 아직 모든 종류를 사지 않았다면
                // end 위치의 보석을 확인하여 갯수 추가
                end++;
                if (end > gems.length - 1 || start > end) break;
                map.put(gems[end], map.get(gems[end]) + 1);
                continue;
            }

            // 모든 종류를 샀다면
            // 현재 범위의 크기 확인
            int curSize = end - start + 1;
            if (size > curSize) {
                // 더 작은 사이즈 발견하면 업데이트
                size = curSize;
                answerS = start;
                answerE = end;
            }

            // 모든 종류가 있는 상태이므로, end를 늘리지 않고 start를 늘린다
            map.put(gems[start], map.get(gems[start]) - 1);
            start++;
        }

        return new int[]{answerS + 1, answerE + 1};
    }

    public static void main(String[] args) {
//        String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
        String[] gems2 = {"AA", "AB", "AC", "AA", "AC"};

//        System.out.println(Arrays.toString(solution(gems)));
        System.out.println(Arrays.toString(solution(gems2)));
    }
}
