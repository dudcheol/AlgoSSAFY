import java.util.Scanner;
import java.util.Stack;

public class hanoi {
	static Stack<int[]> st;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();

	//	int[] arr=new int[3];
		st=new Stack<>();
		
		hoi(1,2,3,n);
//		while(!st.isEmpty()) {
//			int[] arr=st.pop();	
//			System.out.println(arr[2]+":"+"from:"+arr[0]+"to:"+arr[1]);
//		}
//		for(int[] arr:st) {
//			System.out.println(arr[2]+":"+"from:"+arr[0]+"to:"+arr[1]);
//		}
	}
	public static void hoi(int from,int tmp,int to,int n){
		if(n==1) {
//			st.push(new int[] {1,3,n});
			System.out.println(n+" : "+ " "+from+" -> "+to);
		}
		else {
			hoi(from,to,tmp,n-1);//맨밑의 원판을 제외한 나머지 원판들을 tmp로 옮긴다
			System.out.println(n+" : "+ " "+from+" -> "+to);//form 에 있는 한개의 원판을 to로 옮긴다
			hoi(tmp,from,to,n-1);//tmp의 원판들을 to로 옮긴다.
		}
		
	}

}
