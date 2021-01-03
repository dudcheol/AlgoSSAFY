package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class FailureRate {
	static int N = 5; // 스테이지 수
	static int[] stages = {2, 1, 2, 6, 2, 4, 3, 3}; // 사용자가 현재 도전 중인 스테이지 번호
	public static void main(String[] args) {
		int[] result = solution(N, stages);
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
	}
	
	public static int[] solution(int N, int[] stages) {
        int[] answer = {}; // 실패율 저장할 배열
        
        int Inchallenge = stages.length; // 도전한 사람(사용자 명수로 초기화)
        int Notclear = 0; // 클리어 못한 사람
    
        // key: stage number, value: 클리어 못한 사람 수
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        ArrayList<User> users = new ArrayList<User>();
        
        for (int i = 0; i < stages.length; i++) {
        	map.put(stages[i], map.getOrDefault(stages[i], 0) + 1);
  		}
        
        double failureRate;
        for (int i = 1; i <= N; i++) {
        	if(map.containsKey(i))
        		Notclear = map.get(i);
        	else
        		Notclear = 0;
			failureRate = (double) Notclear / Inchallenge;
			if(Inchallenge == 0)
				failureRate = 0;
			users.add(new User(failureRate, i));
			
			Inchallenge -= Notclear;
		}
        
        Collections.sort(users);
        
        answer = new int[N];
        for (int i = 0; i < users.size(); i++) {
			answer[i] = users.get(i).idx;
		}
        return answer;
    }
	
	static class User implements Comparable<User> {
		double failureRate;
		int idx; // 스테이지 번호
		
		User(double failureRate, int idx){
			this.failureRate = failureRate;
			this.idx = idx;
		}
		
		@Override
		public int compareTo(User o) {
			if (this.failureRate >  o.failureRate) {   // 내림차순 , 오름차순으로 하려면 < 으로~
				return -1;
			} else if (this.failureRate == o.failureRate) {
				return 0;
			} else {
				return 1;
			}
		}

	}

}
