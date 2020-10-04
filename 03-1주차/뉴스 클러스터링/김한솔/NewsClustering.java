/**
 * programmers - 2018 카카오 블라인드 채용. 뉴스트래픽
 * NewsClustering.java
 * @date 2020-08-11
 * @author hansolKim
 **/

package programmers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class NewsClustering {
	
	public static void main(String[] args) {
		NewsClustering newsClustering = new  NewsClustering();
		newsClustering.solution("E=M*C^2", "e=m*c^2");
	}

	public int solution(String str1, String str2) {
        int answer = 0;
        final int help = 65536;
        
        HashMap<String, Integer> map1 = new HashMap<>(); // str1 담을 맵
        HashMap<String, Integer> map2 = new HashMap<>(); // str2 담을 맵
        Set<String> strList = new HashSet<>();
        int intersect = 0; // 교집합 원소의 수
        int sum = 0; // 합집합 원소의 수
        
        str1 = str1.toUpperCase(); // 대문자로 통일
        str2 = str2.toUpperCase(); // 대문자로 통일
        
        for(int i=0; i<str1.length()-1; i++) {
        	if(isAlphabet(str1.charAt(i)) && isAlphabet(str1.charAt(i+1))) { // 둘다 영문자인경우
        		String str = str1.substring(i, i+2);
        		 if(!map1.containsKey(str)) {
        			 strList.add(str);
        			 map1.put(str, 1); // 초기 문자열인 경우 횟수 1 상태로 해시맵 삽입
        		 } else {
        			 map1.put(str, map1.get(str) + 1); // 기존에 있는 경우 횟수 추가
        		 }
        	}        	
        }
        
        for(int i=0; i<str2.length()-1; i++) {
        	if(isAlphabet(str2.charAt(i)) && isAlphabet(str2.charAt(i+1))) { // 둘다 영문자인경우
        		String str = str2.substring(i, i+2);
        		 if(!map2.containsKey(str)) {
        			 strList.add(str);
        			 map2.put(str, 1); // 초기 문자열인 경우 횟수 1 상태로 해시맵 삽입
        		 } else {
        			 map2.put(str, map2.get(str) + 1); // 기존에 있는 경우 횟수 추가
        		 }
        	}        	
        }
        
        for(String str : strList) {
        	int cnt1 = 0;
        	int cnt2 = 0;
        	
        	if(map1.containsKey(str)) {
        		cnt1 = map1.get(str);
        	}
        	
        	if(map2.containsKey(str)) {
        		cnt2 = map2.get(str);
        	}
        	
        	sum+=Math.max(cnt1, cnt2);
        	intersect+=Math.min(cnt1, cnt2);
        }
        
        if(sum == 0) {
        	return help;
        }
        
        answer = Math.round(help*intersect/sum);
        
        return answer;
    }

	private boolean isAlphabet(char c) {
		return (c>=65 && c<=90);
	}

}
