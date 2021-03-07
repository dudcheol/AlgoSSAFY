import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 순위검색 {

	public static void main(String[] args) {
		String[] info = new String[] { "java backend junior pizza 150", "python frontend senior chicken 210",
				"python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80",
				"python backend senior chicken 50" };
		String[] query = new String[] { "java and backend and junior and pizza 100",
				"python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250",
				"- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150" };

		System.out.println(Arrays.toString(solution(info, query)));
	}

	static public int[] solution(String[] info, String[] query) {

		Map<String, Integer> map = new HashMap<>();
		List<List<Integer>> ScoreList = new ArrayList<>();

		// 모든 경우의수를 넣는 과정

		// 인덱스를 미리 만들어두기
		map.put("-", 0);
		map.put("cpp", 1);
		map.put("java", 2);
		map.put("python", 3);
		map.put("backend", 1);
		map.put("frontend", 2);
		map.put("junior", 1);
		map.put("senior", 2);
		map.put("chicken", 1);
		map.put("pizza", 2);

		for (int i = 0; i < 4 * 3 * 3 * 3; ++i) {
			ScoreList.add(new ArrayList<>());
		}

		// info 분류
		// 모든 지원자에 대해서 ScoreList에 들어가게 만드는 코드
		for (String str : info) {
			String[] word = str.split(" ");
			int[] arr = { map.get(word[0]) *3*3*3,
						  map.get(word[1]) *3*3, 
						  map.get(word[2]) *3, 
						  map.get(word[3]),
						};
			int score=Integer.parseInt(word[4]);
			
			
			//모든 부분집합을 bit로 표현
			//1을 4만큼 왼쪽으로 shift 1을 왼쪽으로 4번 가게하면? -> 0001 -> 10000 == 16
			for(int i=0;i<(1<<4);++i) {
				int idx=0;
				for(int j=0;j<4;++j) {
					if((i & 1<<j) != 0) {
						idx+=arr[j];
					}
				}
				ScoreList.get(idx).add(score);
			}
		}

		
		//이분 탐색
		for(int i=0;i<4*3*3*3;++i) {
			Collections.sort(ScoreList.get(i));
		}
		
		
		int[] answer = new int[query.length];
		
		for(int i=0;i<query.length;++i) {
			String[] word=query[i].split(" ");
			int idx=map.get(word[0]) *3*3*3 +
				map.get(word[2]) *3*3 +
				map.get(word[4]) *3 +
				map.get(word[6]);
			int score=Integer.parseInt(word[7]);
			int ret=Collections.binarySearch(ScoreList.get(idx), score);
			
			//없다면
			if(ret<0) {
				ret=-(ret+1);
			}
			//같은 스펙
			else {
				for(int j=ret - 1; j>=0;--j) {
					if(ScoreList.get(idx).get(j)==score) {
						ret=j;
					}else {
						break;
					}
				}
				
			}
			answer[i]=ScoreList.get(idx).size()-ret;
		}
		return answer;
	}
}
