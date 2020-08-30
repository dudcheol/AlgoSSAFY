package de;

public class N진수게임2 {
	public static void main(String[] args) {
		System.out.println(solution(2, 4, 2, 1));
		System.out.println(solution(16, 16, 2, 1));
	}

	static public String solution(int n, int t, int m, int p) {
        
        StringBuilder sb=new StringBuilder();
        StringBuilder output=new StringBuilder();
        char[] arr= {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        
        for(int i=0;i<t*m;i++) {
        	sb.append(numTOn(i, n, arr));
        }
        
        for(int i=0;i<t;i++) {
        	output.append(sb.charAt(i*m+p-1));
        }
        
        
        
        
        return output.toString();
	  }

	
	public static String numTOn(int num,int n,char[] arr) {
		StringBuilder sb=new StringBuilder();
		
		while(num/n!=0) {
			sb.append(arr[num%n]);
			num/=n;
		}
		
		sb.append(arr[num%n]);
		sb.reverse();
		
		
		
		return sb.toString();
	}
}
