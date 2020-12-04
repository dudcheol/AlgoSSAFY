package algoSSAFY.week18;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class _9935_문자열_폭발 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String boom = br.readLine();
        int strSize = str.length(), bSize = boom.length();
        StringBuilder sb = new StringBuilder();
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < strSize; i++) {
            char cur = str.charAt(i);
            st.push(cur);
            if(st.size()>=bSize) {
                boolean check = true;
                for (int j = bSize-1, k=0; j >= 0; j--,k++) {
                    if(boom.charAt(j)!=st.get(st.size()-1-k)){
                        check = false;
                        break;
                    }
                }
                if (check){
                    for (int j = 0; j < bSize; j++) st.pop();
                }
            }
        }

        while(!st.isEmpty()){
            sb.append(st.pop());
        }

//        System.out.println(str.length()==0?"FRULA":str);
        System.out.println(sb.length()==0?"FRULA":sb.reverse());
    }
}
