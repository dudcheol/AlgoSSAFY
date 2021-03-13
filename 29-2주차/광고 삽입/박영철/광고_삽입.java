package algorithm.programmers.kakaoBlind2021;

import java.util.*;

// 23:18~ 못풂ㅠㅠ
public class 광고_삽입 {

    public String solution(String play_time, String adv_time, String[] logs) {
        // 100시간 = 360000초이므로 배열을 잡는데 무리가 없음
        int[] ad = new int[360000];

        // 재생시간, 광고시간 변환
        StringTokenizer st = new StringTokenizer(play_time, ":");
        int pTime = Integer.parseInt(st.nextToken()) * 3600 + Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());
        st = new StringTokenizer(adv_time, ":");
        int adTime = Integer.parseInt(st.nextToken()) * 3600 + Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());

        // ad에 각 초마다 재생된 시각을 표시함 => Q) logs에 데이터가 30만개가 있고 모두 100시간짜리면 시간초과 아닌가?
        for (int i = 0; i < logs.length; i++) {
            st = new StringTokenizer(logs[i], "-");
            StringTokenizer startToken = new StringTokenizer(st.nextToken(), ":");
            StringTokenizer endToken = new StringTokenizer(st.nextToken(), ":");
            int startTime = Integer.parseInt(startToken.nextToken()) * 3600 + Integer.parseInt(startToken.nextToken()) * 60 + Integer.parseInt(startToken.nextToken());
            int endTime = Integer.parseInt(endToken.nextToken()) * 3600 + Integer.parseInt(endToken.nextToken()) * 60 + Integer.parseInt(endToken.nextToken());
            for (int j = startTime; j < endTime; j++) {
                ad[j]++;
            }
        }

        // 큐 이용
        // 0초~광고시간만큼 본 사람을 미리 구해놓고 구간합구하기
        Queue<Integer> q = new LinkedList<>();
        long sum=0;
        for (int i = 0; i < adTime; i++) {
            sum+=ad[i];
            q.offer(ad[i]);
        }

        long max = sum;
        int idx = 0;
        for (int i = adTime; i < pTime; i++) {
            sum+=ad[i];
            q.offer(ad[i]);
            sum-=q.poll();
            if(max < sum){
                max = sum;
                idx = i-adTime+1; // Q) idx=i; 가 아닌 이유? i는 광고가 끝난 시간을 가르키므로 시작시간을 찾기 위해서
            }
        }

        int h = idx/3600; idx%=3600;
        int m = idx/60; idx%=60;
        int s = idx;
        return String.format("%02d:%02d:%02d",h,m,s);
    }

    public static void main(String[] args) {
        광고_삽입 c = new 광고_삽입();
        String play_time = "02:03:55";
        String adv_time = "00:14:15";
        String[] logs = {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"};
        System.out.println(c.solution(play_time, adv_time, logs));
    }
}
