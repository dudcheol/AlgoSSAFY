import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class B2812 {

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz=new StringTokenizer(br.readLine());
		
		int N=Integer.parseInt(stz.nextToken());
		int K=Integer.parseInt(stz.nextToken());
		int size=N-K;
		String input=br.readLine();
		Deque<Integer> dq=new LinkedList<Integer>();

		
		for(int i=0;i<N;i++) {
			while(K>0 && !dq.isEmpty() && dq.peekLast()<input.charAt(i)-'0') {
				dq.removeLast();
				K--;
			}
			
			dq.addLast(input.charAt(i)-'0');
		}
		
		
		while(!dq.isEmpty() && size>0) {
			System.out.print(dq.pollFirst());
			size--;
		}

	}

}
