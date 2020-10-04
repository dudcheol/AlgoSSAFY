package de;

import java.util.Arrays;

public class AutoCom3 {

	public static void main(String[] args) {
		
//		System.out.println(solution(new String[] {"go", "gone", "guild"}));
//		System.out.println(solution(new String[] {"abc", "def", "ghi", "jklm"}));
//		System.out.println(solution(new String[] {"word", "war", "warrior", "world"}));
//		System.out.println(solution(new String[] {"aaaaa", "aaaab", "aaabb", "aabbb", "abbbb"}));
//		System.out.println(solution(new String[] {"q", "ware", "efr", "er", "f", "d"}));
		System.out.println(solution(new String[] {"user", "add", "addr", "qwe", "ss", "use","u"}));
		
	}

	
	
	/* 문제 이해
	 * 입력된 글자중에 먼저 입력한 글자와 겹치는 애들이 나오면 안됨
	 * 
	 * 아이디어 
	 * 
	 * map? set? table?
	 * 3중포문?
	 * 
	 * 단어를 넣으면 
	 * 셋에 저장한다.  ?
	 * 
	 * 
	 * 마지막 아이디어
	 * sort 하면 근처에 비슷한 애들끼리 있으니까
	 * 앞뒤로만 비교하면 되지 않을까?
	 * 
	 * 
	 */
	
    static public int solution(String[] words) {
        int answer = 0;
        Arrays.sort(words);
        
        
        for(int i=0;i<words.length;i++) {
        	char[] arr;
        	char[] arr2;
        	//맨앞
        	if(i==0) {
        		arr=words[i].toCharArray();
        		arr2=words[i+1].toCharArray();
        		for(int j=0;j<Math.min(arr.length, arr2.length);j++) {
        			if(arr[j]!=arr2[j]) {
        				break;
        			}
        			answer++;
        		}
        		//비교하는 main이 더 크면 1을 더해줘야함 왜?-> 비교를 작은거 기준으로 하니까
        		if(arr.length>=arr2.length) {
        			answer++;
        		}
        		
        		//만약에 a와 b가 아예 다른 글자면 한 글자 입력해야됨
        		if(answer==0) {
        			answer+=1;
        		}
        		
        	//맨끝
        	}else if(i==words.length-1) {
        		arr=words[i].toCharArray();
        		arr2=words[i-1].toCharArray();
        		
        		for(int j=0;j<Math.min(arr.length, arr2.length);j++) {
        			//
        			if(arr[j]!=arr2[j]) {
        				break;
        			}
        			answer++;
        		}
        		//맨 끝의 경우 한글자 더 입력 해줘야함 why? sort 개념상 앞에 있는 애랑 가장 비슷하고 길이가 긴 친구가 뒤로 가기 때문
        		//만약 모든 글자가 다르다고 해도 최소한 1글자가 있어야함
        			answer++;
        		
        	//그외
        	}else {
        		arr=words[i-1].toCharArray();
        		arr2=words[i+1].toCharArray();
        		char[] arr3=words[i].toCharArray();
        		int left=0;
        		int right=0;
        		
                //앞에꺼랑 비교
        		for(int j=0;j<Math.min(arr.length, arr3.length);j++) {
        			if(arr3[j]!=arr[j]) {
        				break;
        			}
        			left++;
        			
        		}
                
                //뒤에꺼랑 비교
        		for(int j=0;j<Math.min(arr2.length, arr3.length);j++) {
        			if(arr3[j]!=arr2[j]) {
        				break;
        			}
        			right++;
        		}
        		
        		//둘중에 큰 값을 결과값으로 받는다 -> 글자수가 비슷하면 많이 입력하는 쪽 해야하기 때문
        		int result=Math.max(left, right);

        		//만약 result값이 현재 비교하는 글자의 길이와 다르면 1을 해줘야한다.
        		//why? -> 같으면 비교 for문에서 문제가 없지만, 크기가 다르면 - 작은 글자에 맞춰서 for문이 끝까지 돌지 않기 때문에 글자의 길이만큼 돌다가 마지막에 +1 해줘야  한다.
        		//따라서 비교대상의 길이 +1 해주어도 된다 밑에 result+=1 대신에 arr.length+1 혹은 arr2.length+1 해주어도되나, 그럴경우 코드가 좀더 스파게티 같아지기 때문에 아래 방법을 선택
   
        		if(result!=arr3.length) {
        			result+=1;
        		}
        		
        		answer+=result;
        	}
        }
        
        
        return answer;
    }
}
