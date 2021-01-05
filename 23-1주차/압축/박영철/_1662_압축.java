package algoSSAFY.week23;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class _1662_압축 {
    private static class Num{
        int num;
        boolean isLen;

        public Num(int num, boolean isLen) {
            this.num = num;
            this.isLen = isLen;
        }

        @Override
        public String toString() {
            return "Num{" +
                    "num=" + num +
                    ", isLen=" + isLen +
                    '}';
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();

        /**
         * 실수
         * 스택에 문자열을 그대로 저장하는 방법으로 진행했다. 3(123)이면 123123123 << 이런식
         * 이렇게되면 수가 너무 길어질 때, 에러가 발생하게 된다.
         * 문제에서는 길이를 답으로 출력하라고 했으므로, 길이만 스택에 담아도 충분하다.
         * 문제에서 "길이"를 답으로 내라고하는 것은 이유가 있는듯..
         */
        Stack<Num> st = new Stack<>();

        for (int i = 0; i < input.length; i++) {
            if (input[i]==')'){
                int len = 0;
                while (!st.isEmpty() && st.peek().num != -1){
                    Num n = st.pop();
                    if (n.isLen) len+=n.num;
                    else len++;
                }
                st.pop(); // '(' 뽑기
                int k = st.pop().num; // K 뽑기
                st.push(new Num(len * k, true));
                continue;
            }
            st.push(new Num(input[i]=='(' ? -1 : input[i]-'0', false));
        }

        int len = 0;
        while (!st.isEmpty()){
            Num n = st.pop();
            if (n.isLen) len+=n.num;
            else len++;
        }

        System.out.println(len);
    }
}
