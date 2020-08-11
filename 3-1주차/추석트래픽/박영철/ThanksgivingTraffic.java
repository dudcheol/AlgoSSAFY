package week3;

import java.util.StringTokenizer;

public class ThanksgivingTraffic {
    static int solution(String[] lines) {
        int answer = 0;
        double[] times = {3600000, 60000, 1000};

        // 데이터 가공
        // 시간을 뭐가 더 빠르고 느린지 알 수 있어야 함
        double[] endTime = new double[lines.length];
        double[] startTime = new double[lines.length];
        for (int i = 0; i < lines.length; i++) {
            StringTokenizer st = new StringTokenizer(lines[i]);
            st.nextToken();
            String endTimeStr = st.nextToken();

            StringTokenizer st2 = new StringTokenizer(endTimeStr, ":");
            double time = 0.0;
            for (int t = 0; t < 3; t++) {
                time += Double.parseDouble(st2.nextToken()) * times[t];
            }
            endTime[i] = time;
            StringTokenizer st3 = new StringTokenizer(st.nextToken(), "s");
            startTime[i] = endTime[i] - Double.parseDouble(st3.nextToken()) * 1000 + 1;
        }

        // 끝나는 시간 순서로 정렬
        // lines 배열은 응답완료시간 S를 기준으로 오름차순 정렬되어 있다. 라고 했으므로 생략

        // 반복문을 돌면서, 현재 타겟 설정
        // 타겟보다 늦게 끝나는 것들 중, 시작시간이 타겟시간+1초보다 빠른애들 갯수 카운트
        // 반복
        // 매 반복마다 더 큰 값으로 answer 변경
        for (int i = 0; i < lines.length; i++) {
            double target = endTime[i];
            int cnt = 1;
            for (int j = i + 1; j < lines.length; j++) {
                if (startTime[j] <= target + 999) {
                    cnt++;
                }
            }
            answer = Math.max(answer, cnt);
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] lines = {"2016-09-15 00:00:00.000 3s"};
        String[] lines3 = {"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"};
        String[] lines4 = {"2016-09-15 01:00:04.002 2.0s", "2016-09-15 01:00:07.000 2s"};
        String[] lines2 = {"2016-09-15 20:59:57.421 0.351s", "2016-09-15 20:59:58.233 1.181s", "2016-09-15 20:59:58.299 0.8s", "2016-09-15 20:59:58.688 1.041s", "2016-09-15 20:59:59.591 1.412s", "2016-09-15 21:00:00.464 1.466s", "2016-09-15 21:00:00.741 1.581s", "2016-09-15 21:00:00.748 2.31s", "2016-09-15 21:00:00.966 0.381s", "2016-09-15 21:00:02.066 2.62s"};

        System.out.println(solution(lines4));
    }
}
