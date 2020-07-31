package week1;

public class RacewayMaker {
    static int[] dy = {0, 1, -1, 0}; // 우하상좌
    static int[] dx = {1, 0, 0, -1};
    static int[][] map;
    static boolean[][] visited;
    static int n;
    static int answer;

    static void dfs(int y, int x, int jik, int cor, int dir) {
        // basis
        if (y == n - 1 && x == n - 1) {
            answer = Math.min(answer, 100 * jik + 500 * cor);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny < 0 || nx < 0 || ny >= n || nx >= n || map[ny][nx] == 1) {
                continue;
            }

            if (visited[ny][nx]) {
                continue;
            }

            visited[y][x] = true;
            if (dir == -1)
                dfs(ny, nx, jik + 1, cor, i);
            else
                dfs(ny, nx, jik + 1, i == dir ? cor : cor + 1, i);
            visited[y][x] = false;
        }
    }

    static int solution(int[][] board) {
        map = board;
        n = board.length;
        visited = new boolean[n][n];
        answer = Integer.MAX_VALUE;

        dfs(0, 0, 0, 0, -1);

        return answer;
    }

    public static void main(String[] args) {
        int[][] board = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};

        System.out.println(solution(board));
    }
}
