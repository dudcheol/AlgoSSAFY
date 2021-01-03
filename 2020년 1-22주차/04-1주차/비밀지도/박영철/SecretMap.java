package week4;

import java.util.Arrays;

public class SecretMap {

    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];

        for (int i = 0; i < n; i++) {
            String str1 = Integer.toBinaryString(arr1[i]);
            String str2 = Integer.toBinaryString(arr2[i]);
            StringBuilder sb = new StringBuilder();

            str1 = String.format("%"+n+"s", str1);
            str2 = String.format("%"+n+"s", str2);

            for (int j = 0; j < n; j++) {
                char c1 = str1.charAt(j);
                char c2 = str2.charAt(j);
                if ((c1 == '0' || c1 == ' ') && (c2 == '0' || c2 == ' ')) {
                    sb.append(" ");
                } else {
                    sb.append("#");
                }
            }

            answer[i] = sb.toString();
        }

        return answer;
    }

    public static void main(String[] args) {
        SecretMap secretMap = new SecretMap();

        System.out.println(Arrays.toString(secretMap.solution(5, new int[]{9, 20, 28, 18, 11}, new int[]{30, 1, 21, 17, 28})));
    }
}