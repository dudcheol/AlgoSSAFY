/**
 * programmers - 2019 카카오 블라인드 채용. 실패율
 * FailureRate.java
 * @date 2020-09-01
 * @author hansolKim
 **/

package programmers;

public class FailureRate {

	public static void main(String[] args) {
		FailureRate failureRate = new FailureRate();
		int[] stages = { 4,4,4,4 };
		failureRate.solution(4, stages);
	}

	public int[] solution(int N, int[] stages) {
		int[] answer = new int[N];
		int[] map = new int[N + 2];
		double[] results = new double[N + 1];
		boolean[] visited = new boolean[N + 1];
		
		int size = stages.length;

		for (int i = 0; i < size; i++) { // 각 스테이지 도전자 명수 저장
			map[stages[i]]++;
		}

		for (int i = 1; i <= N; i++) { // 실패율 계산 후 results에 삽입
			results[i] = (double)map[i] / size;
			size -= map[i];
		}
		
		int resultIdx=0;
		
		for(int idx=1; idx<=N; idx++) {
			
			if(visited[idx]) continue;
			double value = results[idx]; // 기준 값
			
			for(int j=1; j<=N; j++) { // 최댓 값 찾기
				if(value < results[j] && !visited[j]) {
					value = results[j];
					idx = j;
				}
			}
			
			visited[idx] = true;
			answer[resultIdx++] = idx;
			idx = 0;
		}
		
		return answer;
	}

}
