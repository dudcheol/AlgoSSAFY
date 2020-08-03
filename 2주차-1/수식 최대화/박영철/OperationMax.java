package week2;

import java.util.StringTokenizer;

public class OperationMax {
    static final char[] ops = {'+', '-', '*'};
    static boolean[] visitedOp = new boolean[3];
    static char[] selectedOp = new char[3];
    static String[] cals;
    static int size;
    static long answer;

    static void maximize(int k) {
        if (k == 3) {
            // 선택된 순서로 계산시작
            String[] temp = cals.clone();
            for (int i = 0; i < 3; i++) {
                char curOp = selectedOp[i];
                // 현재 연산자찾아서 String[] 계산한것 적용시키기
                for (int j = 0; j < size; j++) {
                    if (cals[j].equals(curOp + "")) {
                        long left = findLeft(j - 1, temp);
                        long right = findRight(j + 1, temp);
                        long res = calc(left, right, curOp);
                        temp[j] = res + "";
                    }
                }
            }
            for (String t : temp) {
                if (t != null) {
                    answer = Math.max(answer, Math.abs(Long.parseLong(t)));
                    return;
                }
            }
            return;
        }

        for (int i = 0; i < 3; i++) {
            if (visitedOp[i]) continue;
            visitedOp[i] = true;
            selectedOp[k] = ops[i];
            maximize(k + 1);
            visitedOp[i] = false;
        }
    }

    static long findLeft(int idx, String[] cal) {
        if (cal[idx] == null) {
            return findLeft(idx - 1, cal);
        }
        long ret = Long.parseLong(cal[idx]);
        cal[idx] = null;
        return ret;
    }

    static long findRight(int idx, String[] cal) {
        if (cal[idx] == null) {
            return findRight(idx + 1, cal);
        }
        long ret = Long.parseLong(cal[idx]);
        cal[idx] = null;
        return ret;
    }

    static long calc(long a, long b, char op) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
        }
        return 0;
    }

    static long solution(String expression) {
        StringTokenizer stNum = new StringTokenizer(expression, "+-*");
        StringTokenizer stOp = new StringTokenizer(expression, "0123456789");
        size = stNum.countTokens() + stOp.countTokens();

        cals = new String[size];

        for (int i = 0; i < size; i++) {
            if (i % 2 == 0) {
                cals[i] = stNum.nextToken();
            } else {
                cals[i] = stOp.nextToken();
            }
        }

        maximize(0);

        return answer;
    }

    public static void main(String[] args) {
        String expression = "100-200*300-500+20";
        String expression2 = "50*6-3*2";

        System.out.println(solution(expression2));
    }
}
