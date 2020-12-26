package algoSSAFY.week21;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _17281_야구공 {
    private static int[][] player; // 이닝별 플레이어 능력치 저장
    private static int[] game; // 게임 순서 player index 저장
    private static boolean[] visited;
    private static int max, N;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        player = new int[N][9];
        game = new int[9];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 9; j++) {
                player[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[9];
        game[3] = 0;
        max = Integer.MIN_VALUE;
        // 순열로 순서 배정
        // 타순은 이닝이 변경되어도 순서를 유지해야 한다.
        permutation(0);

        System.out.println(max);
    }

    private static void permutation(int k) {
        if (k==9){
            calScore();
            return;
        }
        for (int i = 1; i < 9; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            game[k] = i;
            permutation(k==2?k+2:k+1);
            visited[i] = false;
        }
    }

    private static void calScore() {
        int out = 0, gameNo = 0, score = 0, order = 0;
        boolean[] ru = new boolean[4];
        while(gameNo<N){
            if (out==3){ // 아웃3회시
                gameNo++; // 다음 이닝으로 넘어가고
                out=0; // out 횟수 초기화
                ru = new boolean[4]; // 루도 모두 비움
                continue;
            }

            int cur = player[gameNo][game[order]]; // 현재 순서 플레이어의 능력

            if(cur==0) { // 아웃
                out++;

            } else if (cur==4){ // 홈런
                for (int i = 0; i < 4; i++) {
                    if (ru[i]){ // 루에 있던 주자들 모두 홈으로 이동
                        ru[i] = false;
                        score++;
                    }
                }
                score++; // 타자까지 홈으로 이동

            } else { // 안타,2루타,3루타
                for (int i = 2; i >= 0; i--) { /* 실수! 1루,2루,3루,홈이다 따라서 3개 루에 대해서 생각해야 함 */
                    if (ru[i]) { // 루에 주자가 있으면 cur 만큼 진루한다
                        ru[i] = false;
                        int ni = i + cur;
                        if (ni >= 3) {
                            score++;
                        } else {
                            ru[ni] = true;
                        }
                    }
                }
                ru[cur-1] = true; // 타자는 cur-1만큼 진루한다(0부터 시작하므로)
            }

            order = (order+1)%9;
        }

        max = Math.max(max, score);
    }
}
