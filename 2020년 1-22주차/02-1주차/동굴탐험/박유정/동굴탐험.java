import java.util.Arrays;

public class 동굴탐험 {
	static int[][] arr;
	static boolean[] visit;

	public static boolean solution(int n, int[][] path, int[][] order) {
		arr = new int[n][2];
		visit = new boolean[n];

		for (int i = 0; i < n; i++) {
			arr[i][0] = i;
		}
		

		for (int i = 0; i < path.length; i++) {
			union(path[i][0], path[i][1]);
			
		}
		for (int i = 0; i < n; i++) {
			System.out.println(Arrays.toString(arr[i]));
		}
		for (int i = 0; i < order.length; i++) {
			int from = order[i][0];
			int to = order[i][1];
			System.out.println(Arrays.toString(visit));
			if (!check(from, to))
				return false;
			

		}
		return true;
	}

	public static void union(int a, int b) {
		if (arr[a][0] == a && arr[b][0] == b) {
			if (a > b) {
				arr[a][0] = b;
				arr[a][1] = arr[b][1] + 1;
			} else {
				arr[b][0] = a;
				arr[b][1] = arr[a][1] + 1;
			}
		} else if (arr[a][0] == a) {
			arr[a][0] = b;
			arr[a][1] = arr[b][1] + 1;
		} else if (arr[b][0] == b) {
			arr[b][0] = a;
			arr[b][1] = arr[a][1] + 1;
		}
		return;
	}

	public static boolean check(int a, int b) {
		if (visit[a] || visit[b])
			return false;
		if(arr[a][1]>arr[b][1]) { 
			int k=find(a,b);
			if(k==b) return true;
			find(b,a);
		}
		else {
			int k=find(b,a);
			if(k==a) return true;
			find(a,b);
		}
		return true;
	}

	public static int find(int a, int b) {
		visit[a]=true;
		if (a == b)
			return a;
		if (arr[a][0] == a)
			return a;
		return find(arr[a][0], b);
	}

	public static void main(String[] args) {
		int[][] a1 = { { 0, 1 }, { 0, 3 }, { 0, 7 }, { 8, 1 }, { 3, 6 }, { 1, 2 }, { 4, 7 }, { 7, 5 } };
		int[][] a2 = { { 8, 5 }, { 6, 7 }, { 4, 1 } };
		boolean result = solution(9, a1, a2);
		System.out.println(result);
	}
}
