package week2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Caving {
	static boolean[] visited;
	static Node[] nodes;
	static Queue<Integer> notYet;
	static int dont;
	static int N;

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

		if (current.preOrder != -1 && !visited[current.preOrder]) {
			notYet.add(current.name);
			dont++;
			return;
		}

		visited[current.name] = true;
		dont = 0;

		int size = current.adj.size();
		for (int i = 0; i < size; i++) {
			int adjNode = current.adj.get(i).name;
			if (visited[adjNode])
				continue;
			dfs(adjNode);
		}
	}

	static boolean solution(int n, int[][] path, int[][] order) {
		// 노드 만들기
		N = n ;
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

		// dfs 실행하되, 다끝나고 나오면 큐 확인하기
		// 큐에는 선행노드가 방문되지 않아서 방문하지 못한 노드의 번호가 담겨있음
		dfs(0);
		while (!notYet.isEmpty()) {
			dont = 0;
			int size = notYet.size();

			for (int i = 0; i < size; i++) {
				dfs(notYet.poll());
			}

			if (size == dont) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		int n = 9;
		int[][] path = { { 0, 1 }, { 0, 3 }, { 0, 7 }, { 8, 1 }, { 3, 6 }, { 1, 2 }, { 4, 7 }, { 7, 5 } };
		int[][] order = { { 8, 5 }, { 6, 7 }, { 4, 1 } };

		int n2 = 9;
		int[][] path2 = { { 8, 1 }, { 0, 1 }, { 1, 2 }, { 0, 7 }, { 4, 7 }, { 0, 3 }, { 7, 5 }, { 3, 6 } };
		int[][] order2 = { { 4, 1 }, { 5, 2 } };

		int n3 = 9;
		int[][] path3 = { { 0, 1 }, { 0, 3 }, { 0, 7 }, { 8, 1 }, { 3, 6 }, { 1, 2 }, { 4, 7 }, { 7, 5 } };
		int[][] order3 = { { 4, 1 }, { 8, 7 }, { 6, 5 } };

		int n4 = 4;
		int[][] path4 = { { 0, 1 }, { 0, 2 }, { 0, 3 } };
		int[][] order4 = { { 1, 0 } };

		System.out.println(solution(n, path, order));
		System.out.println(solution(n2, path2, order2));
		System.out.println(solution(n3, path3, order3));
		System.out.println(solution(n4, path4, order4));
	}
}
