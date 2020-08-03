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
            // 선택된 연사자 순서로 계산시작
            String[] temp = cals.clone();
            for (int i = 0; i < 3; i++) {
                char curOp = selectedOp[i];

                // cals를 순회하면서 curOp와 일치하는 연산자의 계산을 수행한다
                for (int j = 0; j < size; j++) {
                    if (cals[j].equals(curOp + "")) {
                        long left = findLeft(j - 1, temp); // j에서부터 배열의 왼쪽으로 계속 이동하여 숫자를 찾고, 그 자리를 Null로 바꾼다.
                        long right = findRight(j + 1, temp); // j에서부터 배열의 오른쪽으로 계속 이동하여 숫자를 찾고, 그 자리를 Null로 바꾼다.
                        long res = calc(left, right, curOp); // 찾은 숫자들을 계산한다
                        temp[j] = res + ""; // 해당 위치를 계산된 결과로 바꾼다
                    }
                }
            }
            for (String t : temp) {
                // Null이 아닌 단 하나의 원소가 현재 연산자 순서로 계산했을 때 도출할 수 있는 결과임.
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
            selectedOp[k/* <- 이거 i가 아니라 k임!!! 실수하지말자!!!!!! */] = ops[i];
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
        // 연산자를 제거하여
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
