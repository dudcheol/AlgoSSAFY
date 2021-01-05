package algoSSAFY.week23;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 실수
 * "회전 연산은 모두 한 번씩 사용해야 하며, 순서는 임의로 정해도 된다." 라는 조건이 있다.
 * 문제 제대로 읽자!!
 */
public class _17406_배열돌리기4 {
    private static int N,M,K, origin[][],order[][],selected[],min;
    private static int[] dy = {0,1,0,-1}; // 우,하,좌,상
    private static int[] dx = {1,0,-1,0}; // 우,하,좌,상
    private static boolean[] visited;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());
        origin = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine()," ");
            for (int j = 0; j < M; j++) {
                origin[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        order = new int[K][];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine()," ");
            order[i] = new int[]{Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())};
        }

        visited = new boolean[K];
        selected = new int[K];
        min = Integer.MAX_VALUE;
        perm(0);

        System.out.println(min);
    }

    private static void perm(int k) {
        if (k==K){
            int[][] tmp = deepcopy(origin);
            rotate(tmp);
            findMinRow(tmp);
            return;
        }
        for (int i = 0; i < K; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            selected[k] = i;
            perm(k+1);
            visited[i] = false;
        }
    }

    private static int[][] deepcopy(int[][] origin) {
        int[][] ret = new int[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(origin[i],0,ret[i],0,M);
        }
        return ret;
    }

    private static void findMinRow(int[][] map) {
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = 0; j < M; j++) {
                sum+= map[i][j];
            }
            min = Math.min(min, sum);
        }
    }

    private static void rotate(int[][] map) {
        for (int i = 0; i < K; i++) {
            int select = selected[i];
            int r=order[select][0], c=order[select][1], s=order[select][2];
            for (int size = 1; size <= s; size++) {
                int nr = r - size;
                int nc = c - size;
                int pre = map[nr][nc];
                for (int d = 0; d < 4; d++) {
                    for (int j = 0; j < size * 2; j++) {
                        nr += dy[d];
                        nc += dx[d];
                        int tmp = map[nr][nc];
                        map[nr][nc] = pre;
                        pre = tmp;
                    }
                }
            }
        }
    }

    private static void print(int[][] map) {
        System.out.println();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
}
