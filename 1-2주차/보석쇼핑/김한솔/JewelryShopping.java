package programmers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class JewelryShopping {

	public static void main(String[] args) {
		Solution s = new Solution();
		String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};		
		
		s.solution(gems);
	}
}

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = {};
        int answerCnt = 0; 
        int cnt = Integer.MAX_VALUE;
        int startIdx = 0;
        
        Map<String, Integer> buyMap = new HashMap<>();
        HashSet<String> gemList = new HashSet<>();
        Queue<String> buyListQueue = new LinkedList<>();
        answer = new int[2];
        
        for(String gem : gems){
            gemList.add(gem);
        }
        
        answerCnt = gemList.size(); // 보석 종류의 갯수
        int startIdxTemp = 0;
        
        for(int i=0; i<gems.length; i++) {
         	  	
        	// 해당 보석을 구매하지 않은 경우
        	if(!buyMap.containsKey(gems[i]))
        		buyMap.put(gems[i], 1);
        	else buyMap.put(gems[i], buyMap.get(gems[i]) + 1); // 보석이 있는 경우 구매횟수 추가
        	        	
        	buyListQueue.add(gems[i]);
        	
        	while(buyMap.get(buyListQueue.peek()) > 1) { // 구매한 보석이 2개 이상인 경우   
        		
    			buyMap.put(buyListQueue.peek(), buyMap.get(buyListQueue.peek())-1); // 구매횟수 감소
    			buyListQueue.poll(); // 앞에서 구매한 겹치는 보석을 구매하지 않음
    			startIdxTemp++;        			        		
        	}        	        	
        	
        	if(buyMap.size() == answerCnt && buyListQueue.size() < cnt) { // 구매해야하는 보석을 모두 구매했고 현재 구매한 보석 수가 이전 구매한 보석 수보다 적을 경우
        		cnt = buyListQueue.size(); // 구매한 총 횟수 갱신
        		startIdx = startIdxTemp;
        	}
        }
        
        answer[0] = startIdx+1;
        answer[1] = startIdx+cnt;
        
        return answer;
    }
}