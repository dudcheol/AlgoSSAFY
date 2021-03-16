package programmers;
//재귀로 풀면 간단하넹...

public class 쿼드압축후개수세기 {
	public int[] solution(int[][] arr) {
		int[] answer = { 0, 0 };
		int num = arr.length;
		boolean[][] arrcheck = new boolean[arr.length][arr.length];

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				answer[arr[i][j]]++;//갯수세기
			}
		}

		while (num > 1) {
			for (int i = 0; i < arr.length; i += num) {
				for (int j = 0; j < arr.length; j += num) {
					if (check(arr, arrcheck, num, i, j)) {//압축 가능한 곳인지 체크
						fill(answer, arr, arrcheck, num, i, j);//압축
					}
				}
			}
			num /= 2;
		}

		return answer;
	}

	public boolean check(int[][] arr, boolean[][] check, int num, int x, int y) {
		if (check[x][y])//이미 압축된 곳이면 pass
			return false;
		//압축 가능한지 확인
		for (int i = x; i < x + num; i++) {
			for (int j = y; j < y + num; j++) {
				if (arr[x][y] != arr[i][j])
					return false;
			}
		}
		return true;
	}

	public void fill(int[] answer, int[][] arr, boolean[][] check, int num, int x, int y) {
		//압축 가능한곳은 true 처리
		for (int i = x; i < x + num; i++) {
			for (int j = y; j < y + num; j++) {
				check[i][j] = true;
			}
		}
		answer[arr[x][y]] -= num * num - 1;//n제곱 -1만큼 빼줌
	}
}
