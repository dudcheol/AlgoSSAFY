package ssafy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ProtectiveFilm_2112 {

	private static StringBuilder sb;
	private static int answer, D, W, K, count; // D: 두께, W: 길이, K: 합격기준
	private static int[][] map, temp;
	private static boolean[] isSelected;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			sb.append("#").append(test_case).append(" ");
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			init();
			
			for(int i=0; i<D; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			subSet(0);
			sb.append(answer).append("\n");
		}
		br.close();
		System.out.println(sb);
	}

	private static void init() {
		answer = Integer.MAX_VALUE;
		map = new int[D][W];
		temp = new int[D][W];
		isSelected = new boolean[D];
		count = 0;
	}
	
	/* 부분집합 */
	private static void subSet(int cnt) {
		if(cnt == D) {
			int selectedCnt = 0;
			StringBuilder stringBuilder = new StringBuilder();
			
			for(int i=0; i<isSelected.length; i++) {
				if(isSelected[i]) selectedCnt++;
			}
			
			if(selectedCnt >= answer) { return;} // 이미 더 작거나 같은 값이 있으므로 바꿔서 비교할 필요가 없다
			
			for(int i=0; i<Math.pow(2, selectedCnt); i++) {
				String state = Integer.toBinaryString(i);
				int dist = selectedCnt - state.length();
				for(int j=0; j<dist; j++) {
					stringBuilder.append("0");
				}
				stringBuilder.append(state);
				convertMap(stringBuilder.toString());
				if(isCheck()) {
					answer = selectedCnt;
					return;
				}
				stringBuilder.replace(0, stringBuilder.length(), "");
			}
			return;
		}
		
		isSelected[cnt] = false;
		subSet(cnt+1);
		isSelected[cnt] = true;
		subSet(cnt+1);
	}
	
	private static void convertMap(String state) { // 임시배열에 변환한 값을 넣어줌
		for(int i=0; i<D; i++) {
			temp[i] = map[i].clone();
		}
		
		int stateIdx = 0;
		
		for(int i=0; i<D; i++) {
			if(isSelected[i]) { // 해당 열이 true인 경우
				for(int j=0; j<W; j++) {
					temp[i][j] = state.charAt(stateIdx)-'0';
				}
				stateIdx++;
			}
		}
	}

	private static boolean isCheck() { // 성능 검사에 통과되는 지 확인
		
		int tempCnt;
		
		// temp배열에서 성능검사 통과하는 지 확인
		for(int i=0; i<W; i++) {
			tempCnt = 1;
			for(int j=0; j<D-1; j++) {
				if(temp[j][i] != temp[j+1][i]) {
					tempCnt = 1;
					continue;
				}
				tempCnt++;
				if(tempCnt >= K) { break;}
			}
			
			if(tempCnt < K) { // 검증 실패
				return false;
			}
		}
		
		return true; // 검증 성공
	}

}
