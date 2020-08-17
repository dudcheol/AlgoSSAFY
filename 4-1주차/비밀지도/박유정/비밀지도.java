
public class 비밀지도 {
	public String[] solution(int n, int[] arr1, int[] arr2) {
		String[] answer = new String[n];
		int[][] bin1 = new int[n][n];
		int[][] bin2 = new int[n][n];

		for (int i = 0; i < n; i++) {
			tobin(bin1[i], n, arr1[i]);
			tobin(bin2[i], n, arr2[i]);
		}

		for (int i = 0; i < n; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < n; j++) {
				if ((bin1[i][j] | bin2[i][j]) == 0) {
					sb.append(" ");
				} else if ((bin1[i][j] | bin2[i][j]) == 1) {
					sb.append("#");
				}
			}
			answer[i] = sb.toString();
		}
		return answer;
	}

	public void tobin(int[] arr, int n, int num) {
		for (int i = n - 1; i >= 0; i--) {
			if (num == 0) {
				arr[i] = 0;
				continue;
			}
			arr[i] = num % 2;
			num /= 2;
		}
	}

}
