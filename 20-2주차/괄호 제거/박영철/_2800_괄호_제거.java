package algoSSAFY.week20;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.TreeSet;

public class _2800_괄호_제거 {
    private static String str;
    private static int size;
    private static TreeSet<String> set;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        size = str.length();
        set = new TreeSet<String>((o1,o2)->{
            return o1.compareTo(o2);
        });

        powerSet(0, new Stack<Character>(), "");

        set.remove(str);

        StringBuilder sb = new StringBuilder();
        for (String l:set) {
            sb.append(l).append('\n');
        }
        System.out.print(sb);
    }

    private static void powerSet(int idx, Stack<Character> st, String s) {
        if(idx==size){
            set.add(s);
            return;
        }

        if (str.charAt(idx)=='('){
            st.push('.');
            powerSet(idx+1, st, s);
            st.pop();
            st.push('(');
            powerSet(idx+1, st, s+'(');
            st.pop();
        } else if (str.charAt(idx)==')'){
            if (st.isEmpty()) return;
            char tmp = st.peek();
            if (st.pop() == '.') {
                powerSet(idx+1, st, s);
            } else {
                powerSet(idx+1, st, s+')');
            }
            st.push(tmp);
        } else {
            powerSet(idx+1, st, s+str.charAt(idx));
        }
    }
}
