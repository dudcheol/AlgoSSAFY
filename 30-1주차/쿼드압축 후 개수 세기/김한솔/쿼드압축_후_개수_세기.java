/**
 * programmers - 쿼드압축 후 개수 세기
 * 쿼드압축_후_개수_세기.java
 * @date 2021-03-16
 * @author hansolKim
 **/

package programmers;

public class 쿼드압축_후_개수_세기 {

	static int zeroCnt, oneCnt;
	static int[][] map;

	public int[] solution(int[][] arr) {

		map = new int[arr.length][arr.length];
		for (int i = 0; i < arr.length; i++) {
			map[i] = arr[i].clone();
		}

		divide(0, 0, arr.length - 1, arr.length - 1);

		int[] answer = new int[2];

		answer[0] = zeroCnt;
		answer[1] = oneCnt;
		return answer;
	}

	private static int divide(int sX, int sY, int eX, int eY) {
		// System.out.println(sX + " " + sY + " " + eX + " " + eY);
		if (sX == eX && sY == eY) { // 단 하나의 좌표인 경우
			if (map[sX][sY] == 0) {
				zeroCnt++;
			}
			if (map[sX][sY] == 1) {
				oneCnt++;
			}
			return map[sX][sY];
		}

		// 분리할 수 있는 경우
		int term = (eX - sX + 1) / 2;
		int leftUp = divide(sX, sY, (sX + eX) / 2, (sY + eY) / 2);
		int leftDown = divide(sX + term, sY, (sX + eX) / 2 + term, (sY + eY) / 2);
		int rightUp = divide(sX, sY + term, (sX + eX) / 2, (sY + eY) / 2 + term);
		int rightDown = divide(sX + term, sY + term, (sX + eX) / 2 + term, (sY + eY) / 2 + term);

		if (leftUp == 1 && leftDown == 1 && rightUp == 1 && rightDown == 1) {
			oneCnt -= 3;
			return 1;
		}

		if (leftUp == 0 && leftDown == 0 && rightUp == 0 && rightDown == 0) {
			zeroCnt -= 3;
			return 0;
		}

		return -1;
	}
}