package algoSSAFY.week25;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _1477_휴게소_세우기 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());
        int L=Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine()," ");
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        /**
         * 실수
         * 주의!
         * int a=1,b=3;
         * double test = a/b;
         * 이면, 0.0이 출력됨
         * 0.333333을 나오게하고 싶다면 a,b중 하나를 double형으로 변환해야 함.
         * 이 문제는 나뉘어진 값의 결과로 우선순위를 결정하므로, 소수점자리까지 고려되어야 함.
         */
        // 숫자, 나눈횟수
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((o1,o2)->{
            double l = o1[0]/(double)o1[1], r = o2[0]/(double)o2[1];
            if(l > r) return -1;
            else if (l < r) return 1;
            else return 0;
        });

        pq.offer(new int[]{arr[0]-0, 1});
        for (int i = 1; i < N; i++) {
            pq.offer(new int[]{arr[i]-arr[i-1], 1});
        }
        pq.offer(new int[]{L-arr[N-1], 1});

        while (!pq.isEmpty() && M--!=0){
            int[] p = pq.poll();
            pq.offer(new int[]{p[0], p[1]+1});
        }

        int[] p = pq.poll();
        System.out.println(p[0]%p[1]==0?p[0]/p[1]:p[0]/p[1]+1);
    }
}

/*
3 1 1000
200 701 800

6 7 800
622 411 201 555 755 82
 */