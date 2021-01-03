package algoSSAFY.week22;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class _1826_연료_채우기_스택풀이_오답 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] infos = new int[N][];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            infos[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int L = Integer.parseInt(st.nextToken()); // 마을까지 거리
        int P = Integer.parseInt(st.nextToken()); // 처음 트럭에 있던 연료 양

        // 연료가 많은 순서로, 같다면 거리가 먼 순서로 정렬
        Arrays.sort(infos, (o1, o2) -> {
            int ret = -Integer.compare(o1[1], o2[1]);
            if (ret == 0) ret = -Integer.compare(o1[0], o2[0]);
            return ret;
        });

        Stack<int[]> s = new Stack<>();

        int mPos = 0;
        int mFuel = P;
        int cnt = 0;

        // 정렬된 배열 순회
        for (int i = 0; i < N; i++) {
            if (mPos+mFuel >= L) {
                System.out.println(cnt);
                return;
            }

            int[] info = infos[i];

            // 현재 발견한 주유소를 스택에 우선 넣음
            s.push(info);

            while (!s.isEmpty()) {
                int[] peek = s.peek();
                int dist = peek[0];
                int fuel = peek[1];
                if (dist <= mPos + mFuel) { // 갈 수 있는 주유소면 간다
                    s.pop();
                    cnt++;
                    mFuel -= (dist - mPos);
                    mFuel += fuel;
                    mPos = dist;
                } else break; // 갈 수 없으면 반복문 종료
            }
        }
        if (mPos+mFuel >= L) {
            System.out.println(cnt);
            return;
        }
        System.out.println(-1);
    }
}
