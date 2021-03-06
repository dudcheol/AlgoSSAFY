/**
 * programmers - 2021 카카오 블라인드 채용. 순위검색
 * 순위검색.java
 * @date 2021-03-03
 * @author hansolKim
 **/

package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 순위검색 {

	public static void main(String[] args) {
		순위검색 순위검색 = new 순위검색();
		String[] info = {"java backend junior pizza 150",
						 "python frontend senior chicken 210",
						 "python frontend senior chicken 150",
						 "cpp backend senior pizza 260",
						 "java backend junior chicken 80",
						 "python backend senior chicken 50"};
		String[] query = {"java and backend and junior and pizza 100",
						  "python and frontend and senior and chicken 200",
						  "cpp and - and senior and pizza 250",
						  "- and backend and senior and - 150",
						  "- and - and - and chicken 100",
						  "- and - and - and - 150"};
		순위검색.solution(info, query);
	}
	
	static class Applicant {
		String language;
		String field;
		String career;
		String food;
		int point;
		
		public Applicant(String language, String field, String career, String food, int point) {
			this.language = language;
			this.field = field;
			this.career = career;
			this.food = food;
			this.point = point;
		}
	}
	
	public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        
        HashMap<String, ArrayList<Applicant>> map = new HashMap<>();
        
        // info를 가지고 infoList 만들기        
        StringBuilder sb = new StringBuilder();
        
        for(String i : info) {
        	StringTokenizer st = new StringTokenizer(i);
        	String lang = st.nextToken();
        	String field = st.nextToken();
        	String career = st.nextToken();
        	String food = st.nextToken();
        	int point = Integer.parseInt(st.nextToken());
        	
        	// 키 생성
        	sb.append(lang).append(field).append(career).append(food);
        	
        	// 해당 키 존재여부 확인
        	ArrayList<Applicant> applicantList;
        	if(map.containsKey(sb.toString())) {
        		applicantList = map.get(sb.toString());        		
        	} else {
        		applicantList = new ArrayList<>();
        	}
        	
        	applicantList.add(new Applicant(lang, field, career, food, point));
        	map.put(sb.toString(), applicantList);
        	
        	// StringBuilder 리셋
        	sb.replace(0, sb.length(), "");
        }
        
        int idx = 0;
        for(String q : query) {        	
        	String[] strs = q.split(" and ");
        	String lang = strs[0];
        	String field = strs[1];
        	String career = strs[2];
        	String foodAndPoint = strs[3];
        	StringTokenizer st = new StringTokenizer(foodAndPoint);
        	String food = st.nextToken();
        	int point = Integer.parseInt(st.nextToken());
        	
        	sb.append(lang).append(field).append(career).append(food);
        	System.out.println(sb.toString());
        	ArrayList<Applicant> searchList = map.get(sb.toString());        	
        	System.out.println(searchList.size());
        	
        	int cnt = 0;
        	for(Applicant a : searchList) {
        		if(a.point >= point) { cnt++;}
        	}

        	answer[idx++] = cnt;
        	
        	// StringBuilder 리셋
        	sb.replace(0, sb.length(), "");
        }
        
        System.out.println(Arrays.toString(answer));
        return answer;
    }

}
