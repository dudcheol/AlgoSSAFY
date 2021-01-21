package algoSSAFY.week25;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 알고리즘 분류 보고 풀었다,,
public class _2212_센서 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int pre=arr[0];
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < N; i++) {
            if (pre==arr[i]) continue;
            list.add(arr[i]-pre);
            pre=arr[i];
        }
        Collections.sort(list);

        int sum=0;
        int len=list.size()+1-K; // list.size(간격의수)+1=센서의수
        for (int i = 0; i < len; i++) {
            sum+=list.get(i);
        }

        System.out.println(sum);
    }
}
