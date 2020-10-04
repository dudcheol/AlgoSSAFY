package de;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class ZIP2 {

	public static void main(String[] args) {
//		System.out.println(Arrays.toString(solution("KAKAO")));
		System.out.println(Arrays.toString(solution("ABABABABABABABAB")));
		
		

	}
    static public int[] solution(String msg) {
        int[] answer = {};
        //K,V
        //숫자를 출력해야 함으로, KEY값을 주면 VALUE를 출력하는 MAP을 사용한다.
        HashMap<String, Integer> map=new HashMap<String, Integer>();
        Queue<Integer> output=new LinkedList<Integer>();
        
        
        //단일 대문자 초기화
        for(int i=1;i<=26;i++) {
        	char b=(char) (64+i);
        	map.put(String.valueOf(b), i);
        }
        msg=msg.concat("!");
        int index=27;
        String k = null;
       
        
       
        for(int i=0;i<msg.length();) {
        	
        	for(int j=msg.length()-1;j>=i;j--) {
        		k=msg.substring(i, j);
        		if(k.contains("!") || k.equals("")) {
    				
    				answer=new int[output.size()];
    		        
    		        for(int z=0;z<answer.length;z++) {
    		        	answer[z]=output.poll();
    		        }
    		        return answer;
    			}
        		if(map.containsKey(k)) {
        			output.add(map.get(k));
        			
        			map.put(msg.substring(i,j+1), index++);

        			i+=k.length();
        		}

        	}
        }
        
        return answer;
    }
}
