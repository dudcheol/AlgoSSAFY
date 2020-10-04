package week5;

public class N진수게임 {
    StringBuilder sb;
    char[] numbers;

    public String solution(int n, int t, int m, int p) {
        String answer = "";
        numbers = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        sb = new StringBuilder();
        // 구해야하는 자릿수는 m * t

        // 진법 확인해서 0~?? 까지 n진수 문자열 만들기
        int length = m * t;
        int i = 0;
        while (true) {
            if (makeStr(i++, n, length)) break;
        }

        // 튜브의 순서를 가지고 해당 자리 숫자만 뽑아오기
        int order = p - 1; // 0부터 시작이므로
        StringBuilder sb2 = new StringBuilder();
        for (int k = 0; k < length / m; k++) {
            sb2.append(sb.charAt(order));
            order += m;
        }

        return sb2.toString();
    }

    private boolean makeStr(int target, int n, int length) {
        if (sb.length() >= length) return true;
        if (target < n) {
            sb.append(numbers[target]);
            return false;
        }
        makeStr(target / n, n, length);
        sb.append(numbers[target % n]);
        return false;
    }

    public static void main(String[] args) {
        N진수게임 n진수게임 = new N진수게임();

//        int n = 2;
//        int t = 4;
//        int m = 2;
//        int p = 1;

        int n = 16;
        int t = 16;
        int m = 2;
        int p = 1;

        System.out.println(n진수게임.solution(n, t, m, p));
    }
}
