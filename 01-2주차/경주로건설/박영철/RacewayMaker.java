package week1;

import java.util.Arrays;

public class RacewayMaker {
    static int[] dy = {0, 1, -1, 0}; // 우하상좌
    static int[] dx = {1, 0, 0, -1};
    static int[] reverseDir = {3, 2, 1, 0};
    static int[][] map;
    static int[][] visited;
    static int n;
    static int answer;

    static void dfs(int y, int x, int cost, int dir) {
        // basis
        if (y == n - 1 && x == n - 1) {
            answer = Math.min(answer, cost);
            return;
        }

        for (int i = 0; i < 4; i++) {
//            if (dir != -1 && reverseDir[dir] == i) continue;

            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny < 0 || nx < 0 || ny >= n || nx >= n || map[ny][nx] == 1) {
                continue;
            }

            if (visited[ny][nx] < cost) {
                continue;
            }

            visited[y][x] = cost;
            if (dir == -1)
                dfs(ny, nx, cost + 100, i);
            else
                dfs(ny, nx, i == dir ? cost + 100 : cost + 600, i);
        }
    }

    static int solution(int[][] board) {
        map = board;
        n = board.length;
        visited = new int[n][n];
        for (int i = 0; i < visited.length; i++)
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        answer = Integer.MAX_VALUE;

        dfs(0, 0, 0, -1);

        return answer;
    }

    public static void main(String[] args) {
        int[][] board = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };

        int[][] board2 = {
                {0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 0, 1, 0},
                {0, 1, 0, 0, 0, 1, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0}
        };

        System.out.println(solution(board));
        System.out.println(solution(board2));
    }
}
