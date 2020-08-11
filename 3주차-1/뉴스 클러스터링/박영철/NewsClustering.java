package week3;

import java.util.ArrayList;

public class NewsClustering {
    static final int NUM = 65536;

    static int solution(String str1, String str2) {
        ArrayList<String> set1 = new ArrayList<>();
        ArrayList<String> set2 = new ArrayList<>();
        int set1Size;
        int set2Size;

        // 원소만들어서 집합에 넣기
        for (int i = 0; i < str1.length() - 1; i++) {
            String str = str1.substring(i, i + 2).toLowerCase();
            if (str.matches("[a-z][a-z]")) set1.add(str);
        }

        for (int i = 0; i < str2.length() - 1; i++) {
            String str = str2.substring(i, i + 2).toLowerCase();
            if (str.matches("[a-z][a-z]")) set2.add(str);
        }

        set1Size = set1.size();
        set2Size = set2.size();

        // 교집합이 되는 원소를 kyo에 담기
        ArrayList<String> kyo = new ArrayList<>();
        for (String s : set2) {
            if (set1.contains(s)) {
                kyo.add(s);
                set1.remove(s);
            }
        }

        // 교집합의 갯수와 합집합의 갯수 구하기
        double kyoSize = kyo.size();
        double hapSize = set1Size + set2Size - kyo.size();


        if (hapSize == 0 && kyoSize == 0) return NUM;
        return (int) ((kyoSize / hapSize) * NUM);
    }

    public static void main(String[] args) {
        String str1 = "aa1+aa2";
        String str1_4 = "E=M*C^2";
        String str2 = "AAAA12";
        String str2_4 = "e=m*c^2";

        System.out.println(solution(str1, str2));
//        System.out.println(solution(str1_4, str2_4));
    }
}
