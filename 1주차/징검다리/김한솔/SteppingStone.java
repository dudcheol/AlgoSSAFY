/**
 * 
 * SteppingStone.java
 * 2020-07-28
 * @author hansolKim
 * 
 **/

package intern_winter_2019;

public class SteppingStone {
	
	public static void main(String[] args) {
		SteppingStone s = new SteppingStone();
		int[] stones = {2,4,5,3,2,1,4,2,5,1};
		s.solution(stones , 3);
	}
	
	public int solution(int[] stones, int k) {
		
		/**
		 * 총 건널수 있는 최대 인원 수 : stones의 최대값
		 * k의 범위 : 1~200000
		 * */
		
        int answer = 0;        
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int value;
        boolean isCross;
        
        for(int i=0; i<stones.length; i++) {
        	max = Math.max(max, stones[i]);
        	min = Math.min(min, stones[i]);
        }
        
        while(true) {
        	if(max < min) { // 해당 인원 수 만큼 건널 수 있다
        		answer = min;
        		break;
        	}
        	
        	value = (max+min)/2; // 해당 인원 수가 건너고 난 후 가정
        	int zeroCnt = 0; // 건널 수 없는 디딤돌 세기
        	isCross = true;

        	// 건널 수 없는 디딤돌 세기
        	for(int i=0; i<stones.length; i++) {
        		
        		if(stones[i]-value <= 0) {
        			zeroCnt++;
        			if(zeroCnt == k) { // 건널 수 없다고 판단된 경우
        				isCross = false;
        				break;
        			}
        			continue;
        		}
        		
        		zeroCnt=0;
        	}
        	
        	if(!isCross) {
        		max = value-1;
        	} else {
        		min = value+1;
        	}
        	
        	
        }
        
        return answer;
    }
}
