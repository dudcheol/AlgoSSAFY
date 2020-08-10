import java.util.*;

public class 뉴스클러스터링 {
	public static int solution(String str1, String str2) {
		HashMap<String, Integer> hm = new HashMap<String, Integer>();//str1의 문자열을 두개씩 잘라서 넣어줄것 <문자열,카운트>
		HashMap<String, Integer> hm2 = new HashMap<String, Integer>();//str2의 문자열을 두개씩 잘라서 넣어줄것

		str1 = str1.toUpperCase();// 대문자로변환
		str2 = str2.toUpperCase();

		char[] arr1 = str1.toCharArray();
		char[] arr2 = str2.toCharArray();

		for (int i = 0; i < arr1.length - 1; i++) {
			if (check(arr1[i]) && check(arr1[i + 1])) {//연속된 두문자열이 영어인지확인
				
				String key = arr1[i] + "" + arr1[i + 1];//두문자열 붙임
				
				if (hm.containsKey(key)) {//해쉬에 있는지확인
					hm.put(key, hm.get(key) + 1);//있으면 갯수 +1
				} else {
					hm.put(key, 1);
				}
			}
		}

		for (int i = 0; i < arr2.length - 1; i++) {
			if (check(arr2[i]) && check(arr2[i + 1])) {
				String key = arr2[i] + "" + arr2[i + 1];
				System.out.println(key);
				if (hm2.containsKey(key)) {
					hm2.put(key, hm2.get(key) + 1);
				} else {
					hm2.put(key, 1);
				}
			}
		}
		double hap = 0;//합집합갯수
		double kyo = 0;//교집합갯수

		if (hm.size() == 0 && hm2.size() == 0)//해쉬에 둘다 아무것도 없으면 return
			return 65536;

		for (String key : hm.keySet()) {//string1의 해쉬의 키(hm의 키)로 hm2와 교집합을 찾는다.
			if (hm2.containsKey(key)) {
				int x = hm.get(key);
				int y = hm2.get(key);
				int max = Math.max(x, y);
				int min = Math.min(x, y);
				hap += max;//큰것이 합집합에 더해줌
				kyo += min;//작은것을 교집합에 더해줌
			} else {
				hap += hm.get(key);//교집합없으면 합집합에 더해줌
			}
		}
		for (String key : hm2.keySet()) {// string1이 가지고 있지않은것들을 합집합에 더해줌
			if (!hm.containsKey(key)) {
				hap += hm2.get(key);
			}
		}
		return (int) (kyo / hap * 65536);
	}

	public static boolean check(char a) {//영어인지확인
		if (a >= 'A' && a <= 'Z')
			return true;
		return false;
	}

	public static void main(String[] args) {
		int ans = solution("FRANCE", "french");
		System.out.println(ans);
	}

}
