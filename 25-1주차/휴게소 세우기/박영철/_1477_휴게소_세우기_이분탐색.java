package algoSSAFY.week25;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _1477_휴게소_세우기_이분탐색 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());
        int L=Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine()," ");
        int[] arr = new int[N+2];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        arr[N+1] = L;

        Arrays.sort(arr);

        // 이분탐색!
        // 먼저 답을 정해보고, 그 답이 정말 답이 되는지 확인해보자
        int start = 0, end = L, answer=0;
        while (start <= end){
            // mid 값을 세울 휴게소의 간격이라고 생각해보자
            int mid = (start+end)/2;

            int cnt=0;
            for (int i = 0; i <= N; i++) {
                cnt+=(arr[i+1]-arr[i]-1)/mid; // 나누어떨어지는 경우, 마지막 지점엔 이미 휴게소가 있으므로 성립되지 않는다
            }

            if (cnt > M){
                // 최대가 mid가 되도록 간격을 나눴을때,
                // 지으려는 휴게소 수(M)보다 많은 갯수가 필요하므로 간격을 넓혀서 갯수를 줄여야 함
                start = mid+1;
            } else {
                // 최대가 mid가 되도록 간격을 나눴을때,
                // 지으려는 휴게소 수(M)보다 적은 갯수가 필요하므로 간격을 좁혀서 갯수를 늘려야 함
                // 지으려는 휴게소 수(M)와 같을 경우, 간격을 더 좁혀서 가능하다면 그 값이 최소가 되므로 확인해봐야함
                end = mid-1;
            }
        }
        System.out.println(start);
    }
}

/*
3 1 1000
200 701 800

6 7 800
622 411 201 555 755 82
 */