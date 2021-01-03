package data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class B1715 {

	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		PriorityQueue<Integer> qu=new PriorityQueue<Integer>();

		int N=Integer.parseInt(br.readLine());
		
		while(N-->0) {
			qu.add(Integer.parseInt(br.readLine()));
		}
		
		if(qu.size()==1) {
			System.out.println(0);
			return;
		}
		
		long result=0;
		
		while(true) {
			int one=qu.poll();
			int two=qu.poll();
			
			result+=one+two;
			
			if(qu.isEmpty()) {
				System.out.println(result);
				break;
			}
			
			qu.add(one+two);
		}
		
	}
		
	

}
