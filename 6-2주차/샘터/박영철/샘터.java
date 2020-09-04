package week6;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 샘터 {
    static int n, k; // 입력값, n = 치킨집의 수, k = 지으려는 집의 수
    static int[] chickPos; // 치킨집의 위치를 담은 배열

    public static void main(String[] args) throws IOException {
        // 입력진행
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        chickPos = new int[n]; // n개의 치킨집이 있으므로 n크기의 치킨집 위치 배열을 생성한다
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            chickPos[i] = Integer.parseInt(st.nextToken()); // 치킨집 위치를 저장한다
        }

        // -1억 <= 치킨집의 위치 <= 1억 : 배열을 이용해서 풀기에 size가 매우 클 수 있다
        // 1 <= 치킨집과 집의 수 <= 10만 : 범위를 감안하여 구현해야 한다
        Queue<Integer> q = new LinkedList<>(); // 치킨집 주변을 확인
        HashSet<Integer> set = new HashSet<>(); // 이미 집이 지어져있는지 확인 (일종의 visited)

        for (int i = 0; i < n; i++) {
            q.offer(chickPos[i]); // 우선 치킨집의 위치를 큐에 넣는다
            set.add(chickPos[i]); // 서로 겹칠 수 없으므로 치킨집의 위치도 visited 처리
        }

        long answer = 0; // 치킨지수의 합 저장 ,, 정답
        int dist = 1; // 치킨집으로부터의 거리
        loop:
        while (!q.isEmpty()) {
            int size = q.size(); // 현재 큐에 담겨있는 위치들은 치킨집과 동일한 거리로 떨어져 있는 것임. 그것들을 한번에 처리한다
            for (int i = 0; i < size; i++) {
                int polled = q.poll(); // 큐에서 뽑은 위치의 좌,우는 집을 지을 수 있는 후보 위치다.
                // 단, 좌,우위치가 set(visited)에 포함되어 있다면 이미 집이 지어져 있는 위치이므로 건너뛴다.
                int right = polled + 1; // 현재 뽑은 위치의 우
                int left = polled - 1; // 현재 뽑은 위치의 좌
                if (!set.contains(right)) { // 아무것도 지어져있지 않은 자리라면 집을 지을 수 있다
                    q.offer(right); // 집을 지었다면 큐에 넣는다.
                    answer += dist; // 집을 지었으므로 치킨지수(집과 치킨집 사이의 거리)를 더한다.
                    set.add(right); // 집을 지었다는 것을 표시하기(visited) 위해 set에 현재 위치를 넣는다.
                    if (--k == 0) break loop; // 집 한 채를 지었으므로 앞으로 지을 집의 수에서 1을 뺀다. 만약 0이 된다면 지을 집을 모두 지은 것이므로 반복문을 종료한다.
                }
                if (!set.contains(left)) { // 아무것도 지어져있지 않은 자리라면 집을 지을 수 있다
                    q.offer(left); // 집을 지었다면 큐에 넣는다.
                    answer += dist; // 집을 지었으므로 치킨지수(집과 치킨집 사이의 거리)를 더한다.
                    set.add(left); // 집을 지었다는 것을 표시하기(visited) 위해 set에 현재 위치를 넣는다.
                    if (--k == 0) break loop; // 집 한 채를 지었으므로 앞으로 지을 집의 수에서 1을 뺀다. 만약 0이 된다면 지을 집을 모두 지은 것이므로 반복문을 종료한다.
                }
            }
            dist++; // q의 사이즈 만큼 돌았다면 동일한 거리로 떨어져있던 위치들을 모두 계산한 것이므로, 그 거리보다 1만큼 더 먼 거리에 집을 짓기 위해 거리를 1 늘린다
        }
        System.out.println(answer); // 정답을 출력한다
    }
}
