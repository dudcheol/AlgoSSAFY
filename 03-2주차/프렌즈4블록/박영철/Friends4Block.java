package week3;

public class Friends4Block {
    public int solution(int m, int n, String[] board) {
        int[] dy = {0, 1, 1};
        int[] dx = {1, 0, 1};
        char[][] map;
        boolean[][] visited;
        int answer = 0;

        map = new char[m][n];
        for (int i = 0; i < m; i++) {
            map[i] = board[i].toCharArray();
        }

        int find = -1;
        while (find != 0) {
            find = 0;
            visited = new boolean[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    char cur = map[i][j];
                    if (cur == '#') continue;
                    int cnt = 0;
                    for (int d = 0; d < 3; d++) {
                        int ny = i + dy[d];
                        int nx = j + dx[d];
                        if (ny < 0 || nx < 0 || ny >= m || nx >= n || cur != map[ny][nx])
                            continue;
                        cnt++;
                    }
                    if (cnt >= 3) {
                        // 4칸이 같은 블록이므로 방문처리
                        visited[i][j] = true;
                        for (int d = 0; d < 3; d++) {
                            int ny = i + dy[d];
                            int nx = j + dx[d];
                            visited[ny][nx] = true;
                        }
                    }
                }
            }

            // 캐릭터 없애기
            for (int i = 0; i < m; i++)
                for (int j = 0; j < n; j++)
                    if (visited[i][j]) {
                        map[i][j] = '#';
                        find++;
                    }

            // 밑줄에서부터 빈 칸 매꾸기
            for (int i = m - 1; i >= 0; i--)
                for (int j = 0; j < n; j++)
                    if (map[i][j] == '#') {
                        // 현재보다 위에 빈칸이 아닌 블럭이 있다면 가져온다
                        int ny = i;
                        while (true) {
                            ny = ny - 1; /* ny = y - 1 이 아니라 ny = ny - 1 이다. 이 실수 너무 자주한다!!! 주의!!! */
                            if (ny < 0) break;
                            if (map[ny][j] != '#') {
                                map[i][j] = map[ny][j];
                                map[ny][j] = '#';
                                break;
                            }
                        }
                    }

            answer += find;
        }

        return answer;
    }

    public static void main(String[] args) {
        Friends4Block friend4Block = new Friends4Block();

        int m = 4;
        int n = 5;
        int mn2 = 6;
        String[] board = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
        String[] board2 = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};

        System.out.println(friend4Block.solution(mn2, mn2, board2));
    }
}
