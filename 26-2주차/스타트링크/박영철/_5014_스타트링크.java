package algoSSAFY.week26;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _5014_스타트링크 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int TOP = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        int dest = Integer.parseInt(st.nextToken());
        int U = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
            return Integer.compare(Math.abs(dest-o1), Math.abs(dest-o2));
        });

        int cnt=0;

        int mok;

        // 지저분하게(?) 생각해줘야할게 많은 문제여서 좀 찝찜함
        // 1. 0에 대한 처리
        // 2. 0이상 100만이하 고려
        // 3. 시작값, 도착값이 같을 경우 고려
        // 이렇게 푸는게 아닌거같은 느낌..?
        if (start < dest){
            if (U!=0) {
                mok = (dest-start) / U;
                if (start + U * mok <= 1000000) {
                    pq.offer(start + U * mok);
                    cnt += mok;
                }
            }
        } else if (start > dest){
            if (D!=0) {
                mok = (start-dest) / D;
                if (start - D * mok >= 0) {
                    pq.offer(start - D * mok);
                    cnt += mok;
                }
            }
        } else {
            System.out.println(0);
            return;
        }

        while (!pq.isEmpty()){
            int p = pq.poll();

            if (p==dest) {
                System.out.println(cnt);
                return;
            }

            if (p+U <= 1000000 && Math.abs(dest-(p+U)) <= Math.abs(dest-p)){
                pq.offer(p+U);
            }

            if (p-D >= 0 && Math.abs(dest-(p-D)) <= Math.abs(dest-p)){
                pq.offer(p-D);
            }

            cnt++;
        }

        System.out.println("use the stairs");
    }
}

//100 35 60 20 5