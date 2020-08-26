import java.util.*;

public class 자동완성 {
	public int solution(String[] words) {
		int answer = 0;

		HashMap<Character, ArrayList<String>> hm = new HashMap<Character, ArrayList<String>>();// 사전만들기

		for (int i = 0; i < words.length; i++) {
			if (!hm.containsKey(words[i].charAt(0))) {// 첫글자 입력
				hm.put(words[i].charAt(0), new ArrayList<String>());
			}
			hm.get(words[i].charAt(0)).add(words[i]);
		}

		for (int i = 0; i < words.length; i++) {
			if (hm.get(words[i].charAt(0)).size() == 1) {// 해당 첫글자가 사전에 한개
				answer += 1;
				continue;
			}
			if (words[i].length() == 2) {// 두글자일경우는 항상 다입력해야함
				answer += 2;
				continue;
			}

			// 세글자이상
			ArrayList<String> al = hm.get(words[i].charAt(0));

			String com = "";
			int cnt;
			boolean flag=false;
			
			for (cnt = 2; cnt <= words[i].length(); cnt++) {
				String h = words[i].substring(0, cnt);// 잘라서 있는지 확인하기
				flag=false;
				
				for (String s : al) {
					if (s.equals(words[i])||cnt > s.length())
						continue;
					com = s.substring(0, cnt);
					if (com.equals(h)) {
						flag=true;
						break;
					}
				}
				if(!flag)
					break;
			}
			if(flag) cnt--;
			answer += cnt;

		}
		return answer;
	}

	public static void main(String[] args) {
		자동완성 a = new 자동완성();
		// System.out.println(a.solution(new String[] { "go", "gone", "guild" }));
		System.out.println(a.solution(new String[] { "word", "war", "warrior", "world" }));
	}
}
