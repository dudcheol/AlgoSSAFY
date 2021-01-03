package algoSSAFY.week21;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _17136_색종이_붙이기 {
    private static int min;
//    private static int[][] map;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] map = new int[10][10];
        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        min = Integer.MAX_VALUE;
        dfs(0, new int[5], map);

        System.out.println(min==Integer.MAX_VALUE? -1 : min);
    }

    private static void dfs(int sum, int[] paper, int[][] map) {
//        System.out.println(sum);
//        for (int i = 0; i < 5; i++) {
//            System.out.print(paper[i]+", ");
//        }
//        System.out.println();
//        printMap(map);
        // 가지치기
        if (sum > min) return;

        // 1 찾기
        int[] pos = findOnePos(map);

        // 1이 없는 경우 -> 완성
        if (pos==null) {
            min = Math.min(min, sum);
            return;
        }

        // 1이 있는 경우
        // 해당 위치를 시작으로 가장 큰 종이부터 1씩 줄여가며 붙일 수 있는지 확인하기
        for (int len = 4; len >= 0; len--) {
            if (paper[len] == 5) continue; // 5장씩 가지고 있음
            if(isSticky(len, pos, map)){
                // 붙일 수 있으면 map copy하고, 붙일 부분을 0으로 바꾸고 sum, paper갯수 증가시키고 dfs 진입
                int[][] tmp = deepcopy(map);
                stick(len, pos, tmp);
                paper[len]++;
                dfs(sum+1, paper, tmp);
                paper[len]--;
            }
        }
    }

    private static void printMap(int[][] map) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void stick(int len, int[] pos, int[][] tmp) {
        for (int i = pos[0]; i <= pos[0]+len; i++) {
            for (int j = pos[1]; j <= pos[1]+len; j++) {
                tmp[i][j] = 0;
            }
        }
    }

    private static boolean isSticky(int len, int[] pos, int[][] map) {
        for (int i = pos[0]; i <= pos[0]+len; i++) {
            if (i>=10) return false;
            for (int j = pos[1]; j <= pos[1]+len; j++) {
                if (j>=10 || map[i][j]==0) return false;
            }
        }
        return true;
    }


    private static int[] findOnePos(int[][] map) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (map[i][j]==1) return new int[]{i,j};
            }
        }
        return null;
    }

    private static int[][] deepcopy(int[][] map) {
        int[][] ret = new int[10][10];
        for (int i = 0; i < 10; i++) {
            System.arraycopy(map[i], 0, ret[i], 0, 10);
        }
        return ret;
    }


}
