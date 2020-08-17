package week4;

import java.util.Stack;

public class DartGame {
    public int solution(String dartResult) {
        int answer = 0;
        Stack<Integer> s = new Stack<>();
        String number = "0123456789";

        for (int i = 0; i < dartResult.length(); i++) {
            char cur = dartResult.charAt(i);
            if (number.contains(cur + "")) {
                // 10일 경우 처리
                if (cur == '1' && dartResult.charAt(i + 1) == '0') {
                    s.push(10);
                    i++;
                } else {
                    s.push(cur - '0');
                }
                continue;
            }

            switch (cur) {
                case 'S':
                    break;
                case 'D':
                    s.push((int) Math.pow(s.pop(), 2));
                    break;
                case 'T':
                    s.push((int) Math.pow(s.pop(), 3));
                    break;
                case '*':
                    // 당첨 시 해당 점수와 바로 전에 얻은 점수를 각 2배
                    int pop1 = s.pop() * 2;
                    if (!s.isEmpty()) s.push(s.pop() * 2);
                    s.push(pop1);
                    break;
                case '#':
                    // 당첨 시 해당 점수는 마이너스
                    s.push(s.pop() * -1);
                    break;
            }
        }

        while (!s.isEmpty()) {
            answer += s.pop();
        }

        return answer;
    }

    public static void main(String[] args) {
        DartGame dartGame = new DartGame();

        System.out.println(dartGame.solution("1S2D*3T"));
    }
}
