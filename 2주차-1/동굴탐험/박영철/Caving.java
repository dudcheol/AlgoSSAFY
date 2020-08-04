package week2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Caving {
    static boolean[] visited;
    static Node[] nodes;
    static Queue<Integer> notYet;

    static class Node {
        int name;
        ArrayList<Node> adj;
        int preOrder;

        Node(int name) {
            this.name = name;
            adj = new ArrayList<>();
            preOrder = -1;
        }
    }

    static void dfs(int node) {
        Node current = nodes[node];
        int size = current.adj.size();

        /* 이슈 : 74번줄 */
        if (current.preOrder != -1 && !visited[current.preOrder]) {
            return;
        }

        visited[current.name] = true;
        for (int i = 0; i < size; i++) {
            Node adjNode = current.adj.get(i);
            if (visited[adjNode.name]) continue;
            if (adjNode.preOrder != -1 && !visited[adjNode.preOrder]) {
                notYet.add(adjNode.name);
                return;
            }
            dfs(adjNode.name);
        }
    }

    static boolean solution(int n, int[][] path, int[][] order) {
        // 노드 만들기
        nodes = new Node[n];
        visited = new boolean[n];
        notYet = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i);
        }

        // path 적용하기
        for (int i = 0; i < path.length; i++) {
            int from = path[i][0];
            int to = path[i][1];

            nodes[from].adj.add(nodes[to]);
            nodes[to].adj.add(nodes[from]);
        }

        // order 에서 반드시 나중에 들러야 하는 노드 정하기
        for (int i = 0; i < order.length; i++) {
            int pre = order[i][0];
            int post = order[i][1];

            nodes[post].preOrder = pre;
        }

        // dfs 실행하되, 다끝나고 나오면 visited가 모두 true인지 확인하기
        dfs(0);
        while (!notYet.isEmpty()) {
            dfs(notYet.poll());
            /* 만약 NotYet에 1, 2 순서로 들어가 있다고 가정하면,
            * 2를 방문해야 1을 방문할 수 있는 경우에 문제가 된다.
            * 1을 poll하면 2가 방문되지 않은 상태기 때문에 dfs에서 그냥 return할 것이고,
            * 그 후 2를 poll하면 그제서야 1을 방문할 수 있는 상태가 된다.
            * 하지만 큐는 비었으므로 반복이 종료된다.
            * 그렇다고해서 dfs에서 계속해서 notYet에 offer하면 불가능한 경우에는 무한루프를 돌 것이다.
            * 이 문제를 해결하면 답을 맞출 수 있을 것이다.
            */
        }

        for (boolean v : visited) {
            if (!v) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 9;
        int[][] path = {{0, 1}, {0, 3}, {0, 7}, {8, 1}, {3, 6}, {1, 2}, {4, 7}, {7, 5}};
        int[][] order = {{8, 5}, {6, 7}, {4, 1}};

        int n2 = 9;
        int[][] path2 = {{8, 1}, {0, 1}, {1, 2}, {0, 7}, {4, 7}, {0, 3}, {7, 5}, {3, 6}};
        int[][] order2 = {{4, 1}, {5, 2}};

        int n3 = 9;
        int[][] path3 = {{0, 1}, {0, 3}, {0, 7}, {8, 1}, {3, 6}, {1, 2}, {4, 7}, {7, 5}};
        int[][] order3 = {{4, 1}, {8, 7}, {6, 5}};

        System.out.println(solution(n, path2, order2));
    }
}
