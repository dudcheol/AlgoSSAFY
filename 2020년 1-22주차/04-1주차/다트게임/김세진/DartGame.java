package de;

public class DartGame {

	public static void main(String[] args) {
		System.out.println(solution("10S0S0S"));
	}

	static public int solution(String dartResult) {
		
		char[] input=dartResult.toCharArray();
		
		
		int[] arr = new int[3];
		int index=0;
		int k=0;
		for (int i = 1; i < dartResult.length(); i++) {
			
			if(input[i-1]!='0') {
				k=input[i-1]-'0';
			}else {
				if(i>1 &&input[i-2]=='1' ) {
					k=10;
				}else {
					k=0;
				}
			}
			
			
			switch (input[i]) {
		
			//싱글
			case 'S':
				arr[index++]=(int) Math.pow(k, 1);
				break;
				
			//더블
			case 'D':
				arr[index++]=(int) Math.pow(k, 2);
				break;
			
			//트리플
			case 'T':
				arr[index++]=(int) Math.pow(k, 3);
				break;
				
			//2배
			case '*':
				//마지막
				if(index==3) {
					arr[2]*=2;
					arr[1]*=2;
					
				//두번째까지
				}else if(index==2) {
					arr[1]*=2;
					arr[0]*=2;
					
				}else {
					arr[0]*=2;
					
				}
				break;
			
			//마이너스
			case '#':
				arr[index-1]=-(arr[index-1]);
				break;

			}

		}
		return arr[0]+arr[1]+arr[2];
	}
}
