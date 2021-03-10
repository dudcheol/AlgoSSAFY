package algorithm.programmers.kakaoBlind2021;

import java.util.Arrays;

public class 합승_택시_요금 {

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;

        // 맵 만들기
        int[][] map = new int[n+1][n+1];
        for (int i = 0; i < fares.length; i++) {
            int start = fares[i][0];
            int end = fares[i][1];
            int cost = fares[i][2];
            map[start][end]=cost;
            map[end][start]=cost;
        }

        // 플루이드 와샬
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i==j) continue;
                    if (map[i][k]==0) continue;
                    if (map[k][j]==0) continue;
                    if (map[i][j]==0) map[i][j] = map[i][k] + map[k][j];
                    else if (map[i][j] > map[i][k]+map[k][j]){
                        map[i][j] = map[i][k]+map[k][j];
                    }
                }
            }
        }

        // 완전탐색
        // 중간지점을 i라고 했을 때, s->i를 거쳐 i->a, i->b로 가는 모든 경우의 수 찾는다
        for (int i = 1; i <= n; i++) {
            if(map[s][i]+map[i][a]+map[i][b]==0) continue;
            answer = Math.min(answer, map[s][i]+map[i][a]+map[i][b]);
        }

        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        return answer;
    }

    public static void main(String[] args) throws Exception{
        int n = 6, s = 4, a=6,b=2,fares[][]={{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};

        합승_택시_요금 c = new 합승_택시_요금();
        System.out.println(c.solution(n,s,a,b,fares));
    }
}
