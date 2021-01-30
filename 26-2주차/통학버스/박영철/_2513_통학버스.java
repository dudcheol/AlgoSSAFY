package algoSSAFY.week26;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _2513_통학버스 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int N=Integer.parseInt(st.nextToken()); // 아파트수
        int K=Integer.parseInt(st.nextToken()); // 버스 정원
        int S=Integer.parseInt(st.nextToken()); // 학교 위치

        // 좌표, 학생수
        PriorityQueue<int[]> left = new PriorityQueue<>((o1, o2) -> {
            return -Integer.compare(Math.abs(S-o1[0]), Math.abs(S-o2[0]));
        });
        PriorityQueue<int[]> right = new PriorityQueue<>((o1, o2) -> {
            return -Integer.compare(Math.abs(S-o1[0]), Math.abs(S-o2[0]));
        });

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int pos = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());
            if (pos > S){
                right.offer(new int[]{pos,cnt});
            } else if (pos < S){
                left.offer(new int[]{pos,cnt});
            }
        }

        int min=0;

        // 가장 먼 곳 먼저 간다음 학교로 돌아가면서 학생들을 태워온다
        while (!left.isEmpty()){
            int sum=0; // 현재까지 태운 인원
            int pre=S; // 학교에서 출발
            while (!left.isEmpty() && sum<K) {
                int[] p = left.poll();
                int pos = p[0];
                int cnt = p[1];
                if (sum + cnt > K) { // 버스 인원 초과 -> 가능한 인원만 태운다
                    int rest = sum + cnt - K; // 버스에 타지 못한 학생
                    left.offer(new int[]{pos, rest});
                    sum += cnt-rest;
                } else {
                    sum += cnt;
                }
                min += Math.abs(pos - pre); // 이동거리 더하기
                pre = pos; // 버스위치이동
            }
            min+=Math.abs(S-pre); // 다시 학교로 이동
        }

        while (!right.isEmpty()){
            int sum=0; // 현재까지 태운 인원
            int pre=S; // 학교에서 출발
            while (!right.isEmpty() && sum<K) {
                int[] p = right.poll();
                int pos = p[0];
                int cnt = p[1];
                if (sum + cnt > K) { // 버스 인원 초과 -> 가능한 인원만 태운다
                    int rest = sum + cnt - K; // 버스에 타지 못한 학생
                    right.offer(new int[]{pos, rest});
                    sum += cnt-rest;
                } else {
                    sum += cnt;
                }
                min += Math.abs(pos - pre); // 이동거리 더하기
                pre = pos; // 버스위치이동
            }
            min+=Math.abs(S-pre); // 다시 학교로 이동
        }

        System.out.println(min);
    }
}

/*
4 2 4
0 1
1 1
2 3
5 1

16
 */