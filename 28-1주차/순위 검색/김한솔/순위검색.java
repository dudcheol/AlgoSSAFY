/**
 * programmers - 2021 카카오 블라인드 채용. 순위검색
 * 순위검색.java
 * @date 2021-03-03
 * @author hansolKim
 **/

package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 순위검색 {

	public static void main(String[] args) {
		순위검색 순위검색 = new 순위검색();
		String[] info = {"java backend junior pizza 150",
						 "python frontend senior chicken 150",
						 "python frontend senior chicken 150",
						 "cpp backend senior pizza 150",
						 "java backend junior chicken 150",
						 "python backend senior chicken 50"};
		String[] query = {"java and backend and junior and pizza 100",
						  "python and frontend and senior and chicken 200",
						  "cpp and - and senior and pizza 250",
						  "- and backend and senior and - 150",
						  "- and - and - and chicken 100",
						  "- and - and - and - 150"};
		순위검색.solution(info, query);
	}
	
	public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        
        HashMap<String, ArrayList<Integer>> map = new HashMap<>();
        
        // info를 가지고 infoList 만들기        
        StringBuilder sb = new StringBuilder();
        
        for(String in : info) {
        	StringTokenizer st = new StringTokenizer(in);
        	String lang = st.nextToken();
        	String field = st.nextToken();
        	String career = st.nextToken();
        	String food = st.nextToken();
        	int point = Integer.parseInt(st.nextToken());
        	
        	// 키 생성
        	for(int i=0; i<2; i++) {
        		for(int j=0; j<2; j++) {
        			for(int k=0; k<2; k++) {
        				for(int t=0; t<2; t++) {        	        		
        					sb.append(i==0 ? "-" : lang);
        					sb.append(j==0 ? "-" : field);
        					sb.append(k==0 ? "-" : career);
        					sb.append(t==0 ? "-" : food);
        					
        					// 해당 키 존재여부 확인
        		        	ArrayList<Integer> pointList;
        		        	if(map.containsKey(sb.toString())) {
        		        		pointList = map.get(sb.toString());        		
        		        	} else {
        		        		pointList = new ArrayList<>();
        		        	}
        		        	
        		        	pointList.add(point);
        		        	map.put(sb.toString(), pointList);
        		        	
        		        	// StringBuilder 리셋
        		        	sb.replace(0, sb.length(), "");
        	        	}
                	}        			
            	}
        	}                	
        }
        
        for(String key : map.keySet()) {
        	ArrayList<Integer> pointList = map.get(key);
			Collections.sort(pointList);
			map.put(key, pointList);
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
        	
        	int cnt = 0;
        	if(map.containsKey(sb.toString())) {
        		ArrayList<Integer> pointList = map.get(sb.toString());
        		cnt = pointList.size() - lowerBound(pointList, point);
        	} 

        	answer[idx++] = cnt;
        	        	
        	// StringBuilder 리셋
        	sb.replace(0, sb.length(), "");
        }
        
        System.out.println(Arrays.toString(answer));
        return answer;
    }

	private int lowerBound(ArrayList<Integer> pointList, int point) {		
		int left = 0;
		int right = pointList.size()-1;
		
		if(pointList.get(right) < point) { return pointList.size(); }
		
		while(left<right) {
			int mid = (left+right)/2;
			
			if(pointList.get(mid) < point) {
				left = mid+1;
			} else {
				right = mid; 
			}
		}
				
		return right;
	}

}
