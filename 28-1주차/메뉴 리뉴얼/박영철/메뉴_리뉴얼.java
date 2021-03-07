package algorithm.programmers.kakaoBlind2021;

import java.util.*;

public class 메뉴_리뉴얼 {

    private char[] selected;
    private Map<String, Integer> map;

    // 문제 너무 헷갈리게 만들어 놓은듯 싶음
    // 가장 많이 함께 주문된 단품메뉴 조합을 출력하되, 메뉴 구성이 여러 개라면 모두 출력
    // ex) 메뉴조합 a,b가 2개이고 a,c가 3개라면 a,c만 출력
    public String[] solution(String[] orders, int[] course) {
        List<String> list = new ArrayList<>();
        map = new HashMap<>();

        // orders 알파벳순으로 변경
        for (int i = 0; i < orders.length; i++) {
            orders[i] = sortAlpha(orders[i]);
        }

        // course[j]개를 뽑은 조합 만들기
        for (int j = 0; j < course.length; j++) {
            selected = new char[course[j]];
            // orders로 조합만들기
            for (int i = 0; i < orders.length; i++) {
                String str = orders[i];
                comb(0, 0, course[j], str);
            }

            // 뽑은 조합 중 가장 많은 조합의 개수 찾기
            int max = 0;
            for (int cnt : map.values()) {
                max = Math.max(max, cnt);
            }

            if (max >= 2) {
                // max개인 애만 뽑기
                for (Map.Entry<String, Integer> e : map.entrySet()) {
                    if (e.getValue() == max) {
                        list.add(e.getKey());
                    }
                }
            }
            map.clear();
        }

        list.sort((o1, o2) -> {
            return o1.compareTo(o2);
        });

        String[] answer = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }

    private String sortAlpha(String str) {
//        List<Character> tmp = new ArrayList<>();
//        for (int i = 0; i < str.length(); i++) {
//            tmp.add(str.charAt(i));
//        }
//        tmp.sort((o1,o2)->{
//            return Integer.compare(o1,o2);
//        });
//        String res = "";
//        for(char c : tmp) res+=c;
//        return res;
        char[] ary = str.toCharArray();
        Arrays.sort(ary);
        return String.valueOf(ary);
    }

    private void comb(int k, int idx, int limit, String str) {
        if (k == limit) {
            String res = "";
            for (int i = 0; i < limit; i++) {
                res += selected[i];
            }
            map.put(res, map.getOrDefault(res, 0) + 1);
            return;
        }

        for (int i = idx; i < str.length(); i++) {
            selected[k] = str.charAt(i);
            comb(k + 1, i + 1, limit, str);
        }
    }

    public static void main(String[] args) throws Exception {
        메뉴_리뉴얼 c = new 메뉴_리뉴얼();
//        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        String[] orders = {"XYZ", "XWY", "WXA"};
        int[] course = {2, 3, 4};
        for (String a : c.solution(orders, course)) {
            System.out.println(a);
        }
    }
}
