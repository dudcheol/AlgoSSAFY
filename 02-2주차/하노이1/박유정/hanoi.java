import java.util.Scanner;

public class hanoi {
	static Stack<int[]> st;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();

		
		hoi(1,2,3,n);

	public static void hoi(int from,int tmp,int to,int n){
		if(n==1) {
			System.out.println(n+" : "+ " "+from+" -> "+to);
		}
		else {
			hoi(from,to,tmp,n-1);//맨밑의 원판을 제외한 나머지 원판들을 tmp로 옮긴다
			System.out.println(n+" : "+ " "+from+" -> "+to);//form 에 있는 한개의 원판을 to로 옮긴다
			hoi(tmp,from,to,n-1);//tmp의 원판들을 to로 옮긴다.
		}
		
	}

}
