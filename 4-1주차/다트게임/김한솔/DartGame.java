package programmers;

public class DartGame {

	public static void main(String[] args) {
		DartGame dartGame = new DartGame();
		String dartResult = "1S2D*3T";
		String dartResult2 = "1D2S#10S";
		String dartResult3 = "1D2S0T";
		String dartResult4 = "1S*2T*3S";
		String dartResult5 = "1D#2S*3S";
		String dartResult6 = "1T2D3D#";
		String dartResult7 = "1D2S3T*";
		dartGame.solution(dartResult7);
	}
	
	public int solution(String dartResult) {
        int answer = 0;
        int score[] = new int[3]; // 3회분 점수 저장
        int num = 0;
        int idx = -1; // 순서
        
        for(int i=0; i<dartResult.length(); i++) {
        	
        	// S문자인경우
        	if(dartResult.charAt(i)=='S') {
        		score[++idx] = num;
        		num = 0;
        		continue;
        	}
        	
        	if(dartResult.charAt(i)=='D') {
        		score[++idx] = (int)(Math.pow((double)num, 2.0));
        		num = 0;
        		continue;
        	}
        	
        	if(dartResult.charAt(i)=='T') {
        		score[++idx] = (int)(Math.pow((double)num, 3.0));
        		num = 0;
        		continue;
        	}
        	
        	// *이 들어온 경우
        	if(dartResult.charAt(i) == '*') {
        		if(idx >= 1) { // 이전 점수가 있는 경우
        			score[idx-1] *= 2; // 이전 점수 *2
        		}
        		score[idx] *= 2; // 현재 점수 *2
        		continue;
        	}
        	
        	// #이 들어온 경우
        	if(dartResult.charAt(i) == '#') {
        		score[idx] *= -1;
        		continue;
        	}
        	
        	num *= 10;
        	num += dartResult.charAt(i)-'0';
        }
        
        answer = score[0]+score[1]+score[2];
        System.out.println(answer);
        return answer;
    }

}
