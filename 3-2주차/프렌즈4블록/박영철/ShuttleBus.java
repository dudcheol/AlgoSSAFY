package week3;

import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class ShuttleBus {

    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";

        // 우선순위 큐 - 오름차순 정렬
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
            return Integer.compare(o1, o2);
        });

        // 다른 사람들이 타는 시간 파싱해서 pq에 넣기 (온 순서로 줄 세우기)
        for (String timet : timetable) {
            StringTokenizer st = new StringTokenizer(timet, ":");
            int time = Integer.parseInt(st.nextToken()) * 60;
            time += Integer.parseInt(st.nextToken());
            pq.offer(time);
        }

        // n,t,m 따지면서 가장 늦은 시간에 갈 수 있는 경우를 찾아야 함
        // n : 운행횟수
        // t : 운행간격
        // m : 셔틀버스 최대 인원
        int mTime = 0; // 내가 탈 수 있는 시간
        int shuttleTime = 9 * 60;
        Stack<Integer> s = new Stack<>(); // suttleTime에 셔틀버스에 탄 인원

        // 운행횟수가 남아있고 버스 줄을 선 사람이 남아있을 때 까지 반복
        while (n != 0 && !pq.isEmpty()) {
            n--; // 운행횟수 차감
            for (int i = 0; i < m; i++) {
                // m명까지 셔틀시간 전에 왔다면 차에 태움
                if (!pq.isEmpty() && pq.peek() <= shuttleTime)
                    s.push(pq.poll());
            }

            if (n > 0) {
                // 운행횟수가 남아있으면 다음에 타도 됨
            } else if (n == 0) {
                // 운행횟수가 없다면 이제는 타야한다
                if (s.size() < m) {
                    // 차가 만석이 아니라면
                    // 셔틀시간에 딱 맞게 도착하면 됨
                    mTime = shuttleTime;
                } else {
                    // 차가 만석이라면
                    // 마지막에 탄 사람보다 일찍 와야 함
                    mTime = s.peek() - 1;
                }
            }
            shuttleTime += t; // 셔틀시간 늘림
            s.clear(); // 다음 셔틀버스는 모두 비어있음
        }

        answer = String.format("%02d:%02d", mTime / 60, mTime % 60);
        return answer;
    }

    public static void main(String[] args) {
        ShuttleBus shuttleBus = new ShuttleBus();

        int n = 1;
        int t = 1;
        int m = 5;
        String[] timetable = {"08:00", "08:01", "08:02", "08:03"}; // 09:00
        String[] timetable2 = {"09:00", "09:00", "09:00", "09:00"}; // 08:59

        System.out.println(shuttleBus.solution(n, t, m, timetable));
        System.out.println(shuttleBus.solution(2, 1, 2, timetable2));
    }
}
