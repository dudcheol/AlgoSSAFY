/**
 * programmers - 2021 카카오 블라인드 채용. 메뉴 리뉴얼
 * 신규아이디추천.java
 * @date 2021-03-02
 * @author hansolKim
 **/

package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class 메뉴리뉴얼 {

	public static void main(String[] args) {
		메뉴리뉴얼 메뉴리뉴얼 = new 메뉴리뉴얼();
		String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
		int[] course = {2,3,4};
		메뉴리뉴얼.solution(orders, course);

	}
	
	static ArrayList<Character> input;
	static ArrayList<String>[] resultList; 
	static int[] selectedIdx;
	static int[] counts;
	static int[] courseList; 
	static String[] orderList;
	static int R;
	
	public String[] solution(String[] orders, int[] course) {
        String[] answer;
        
        input = new ArrayList<>();
        orderList = orders;
        courseList = course;
        
        for(String order : orders) {
        	for(int i=0; i<order.length(); i++) {
        		if(!input.contains(order.charAt(i))) {
        			input.add(order.charAt(i));
        		}
        	}
        }
        
        Collections.sort(input);
        
        resultList = new ArrayList[course.length];
        counts = new int[course.length];
        
        int allCount = 0;
        for(int i=0; i<course.length; i++) {
        	R = course[i];
        	selectedIdx = new int[R];
        	resultList[i] = new ArrayList<>();
        	comb(0, 0, i);
        	allCount += resultList[i].size();
        }
        
        
        answer = new String[allCount];
        
        int idx = 0;
        for(int i=0; i<course.length; i++) {
        	for(String str : resultList[i]) {
        		answer[idx++] = str;
        	}
        }
        
        Arrays.sort(answer);
        
        for(String str : answer) {
        	System.out.println(str);
        }
        return answer;
    }

	private void comb(int start, int cnt, int idx) {
		if(cnt == R) {
			
			int containsCount = 0;
			// 해당 인덱스의 문자를 모두 가지고 있는 지 확인 							
			main:for(String order : orderList) {
				for(int i=0; i<R; i++) {
					boolean isContain = false;
					char c = input.get(selectedIdx[i]);
					for(int j=0; j<order.length(); j++) {
						if(order.charAt(j) == c) {
							isContain = true;
							continue;
						}
					}
					
					if(!isContain) {
						continue main;
					}
				}
				
				containsCount++;
			}			
			
			if(containsCount>=2 && counts[idx] <= containsCount) {
				if(counts[idx] < containsCount) {
					resultList[idx].clear();
					counts[idx] = containsCount;
				}
				
				StringBuilder sb = new StringBuilder();
				for(int i=0; i<R; i++) {
					sb.append(input.get(selectedIdx[i]));
				}
								
				resultList[idx].add(sb.toString());
			}
			
			return;
		}
		
		for(int i=start; i<input.size(); i++) {
			selectedIdx[cnt] = i;
			comb(i+1, cnt+1, idx);
		}
	}
}