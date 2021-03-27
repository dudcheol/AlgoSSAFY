package programmers;

public class 멀리뛰기 {
	static long[] dp;
	
	public static void main(String[] args) {
		int n = 1;
		
		System.out.println(solution(n));
	}

    public static long solution(int n) {
    	dp = new long[n + 1];
    	if(n == 1)
    		return 1;
    	if(n == 2)
    		return 2;
    	
    	dp[1] = 1;
    	dp[2] = 2;
    	
    	return recur(n);
    }
    
    public static long recur(int n) {
    	if(dp[n] != 0)
    		return dp[n];
    	
    	return dp[n] = (recur(n - 1) + recur(n - 2)) % 1234567;
    }
}
