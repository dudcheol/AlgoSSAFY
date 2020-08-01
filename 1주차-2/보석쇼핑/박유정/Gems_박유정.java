import java.util.*;

class Gems_박유정 {
	public static int[] solution(String[] gems) {
		int[] answer = new int[2];
		int n = gems.length;
		int start = 0, end = n-1;
		HashMap<String, Integer> hm = new HashMap<>();//해쉬<종류,갯수>

		for (int i = 0; i < n; i++) {
			if (hm.containsKey(gems[i]))
				continue;
			hm.put(gems[i], 0);//보석별로 해쉬에 넣는다.
		}

		int size = hm.size();//보석 종류의 갯수

		if (size == n)//보석 종류의 갯수와 전체 배열의 길이가 같다면 
			return new int[] { 1, n };
		
		int count = 0;//보석 종류 카운트
		int min_length = Integer.MAX_VALUE;//인덱스 최소거리 계산

		for (int i = 0; i < n; i++) {
			if (hm.get(gems[i]) == 0) {//처음들어왔으면 count 증가시킴
				count++;
			}

			hm.put(gems[i], hm.get(gems[i]) + 1);//해쉬에 하나를 증가시켜 넣어줌

			if (count == size) {
				for (int j = start; j <= i; j++) {//앞에꺼 부터 빼줌
					
					if (hm.get(gems[j])-1  == 0) {//빼주려는 보석이 마지막 보석이면 
						start=j;//start 인덱스
						end=i;//end 인덱스
						System.out.println(start+"ddddddddddddddddddddddddddd"+end);
						break;
					} 
					
					hm.put(gems[j], hm.get(gems[j]) - 1);//빼주려는 보석이 마지막 보석이 아니면 -1
				}

			//	System.out.println(length);
				
				if ( min_length > end - start) {
					answer[0] = start+1;//인덱스가 1부터 시작
					answer[1] = end+1;
					min_length=end - start;
				}
				
			}
		}

		return answer;
	}
	public static void main(String[] args) {
		String[] arr={"XYZ", "XYZ", "XYZ"};
		int[] m=solution(arr);
		System.out.println(m[0]+"ddddd"+m[1]);
	}
}
