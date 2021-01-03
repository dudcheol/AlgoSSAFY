package algoSSAFY.week18;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class _4195_친구_네트워크 {
    private static int F;
    private static HashMap<String, String> map;
    private static HashMap<String, Integer> cnt;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int TC = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= TC; test_case++) {
            F = Integer.parseInt(br.readLine());
            map = new HashMap<String,String>();
            cnt = new HashMap<String,Integer>();
            for (int i = 0; i < F; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                String p1 = st.nextToken();
                String p2 = st.nextToken();
                if (!map.containsKey(p1)) map.put(p1,p1);
                if (!cnt.containsKey(p1)) cnt.put(p1,1);
                if (!map.containsKey(p2)) map.put(p2,p2);
                if (!cnt.containsKey(p2)) cnt.put(p2,1);
                sb.append(union(p1,p2)).append('\n');
            }
        }
        System.out.print(sb);
    }

    private static int union(String p1, String p2) {
        String p1Root = find(p1);
        String p2Root = find(p2);
        if (p1Root.equals(p2Root)){
            return cnt.get(p1Root);
        }
        map.put(p2Root, p1Root); // p2의 부모를 p1의 부모로 바꿈(union)
        cnt.put(p1Root, cnt.get(p1Root)+cnt.get(p2Root));
        return cnt.get(p1Root);
    }

    private static String find(String x) {
        if (x.equals(map.get(x))) return x;
        map.put(x, find(map.get(x)));
        return map.get(x);
    }
}

/*
1
3
F D
R F
D R
 */