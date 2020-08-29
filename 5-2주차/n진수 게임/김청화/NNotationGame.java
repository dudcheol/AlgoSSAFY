package programmers;

import java.util.ArrayList;

// 프로그래머스 N진수 게임 https://programmers.co.kr/learn/courses/30/lessons/17687
public class NNotationGame {
	
	// n: 진법, t: 말해야 하는 숫자 개수, m: 게임에 참가하는 인원, p: 튜브 순서
	//static int n = 2, t = 4, m = 2, p = 1;
	static int n = 16, t = 16, m = 2, p = 1;
	//static int n = 16, t = 16, m = 2, p = 2;
	
	public static void main(String[] args) {
		String result = solution(n, t, m , p);
		System.out.println(result);
	}
	
    public static String solution(int n, int t, int m, int p) {
        String answer = "";
        
        ArrayList<String> string = new ArrayList<String>();
        int num = 0; 
        // array크기가 m*t가 될때까지 구함 (튜브가 게임에 참가하는 인원 중 가장 마지막에 말할 경우가 제일 큼)
        while(true) {
        	
        	String result = convert(num, n);
        	for (int i = 0; i < result.length(); i++) {
				string.add(result.split("")[i]);
			}
        	
        	num++;
        	
        	if(string.size() > m*t)
        		break;
        }
        
        for (int i = 0; i < string.size(); i++) {
			if(i%m == (p - 1))
				answer += string.get(i);
			if(answer.length() == t)
				break;
		}
        
        
        return answer;
    }
    

    private static String convert(int num, int n) {
    	 String tmp = "";
    	 
    	 if(num == 0) return "0";
    	 
         while(num != 0) {
        	 
             if(num % n < 10)
                 tmp += num % n;
             else // 10진수 이상 알파벳 저장
                 tmp += String.valueOf((char)(num % n + 55));
             num /= n;
         }
         
         String convert = new StringBuilder(tmp).reverse().toString();

        return convert;
    }
}
