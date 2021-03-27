package algoSSAFY.week31;

// 오답!! DP문제였음 ㅠㅠ
public class 멀리_뛰기 {
    long answer = 0;

    public long solution(int n) {
        subset(n,0);

        return answer%1234567;
    }

    public void subset(int n, int k){
        if(n==k){
            answer++;
            return;
        } else if(k>n) return;

        subset(n, k+1);
        subset(n, k+2);
    }

    public static void main(String[] args) {
        멀리_뛰기 c = new 멀리_뛰기();
        System.out.println(c.solution(100));
    }
}
