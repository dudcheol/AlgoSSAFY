package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class 카드정렬하기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> q=new PriorityQueue<Integer>();

		int n=Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			q.add(Integer.parseInt(br.readLine()));
		}
		
		int answer=0;

		
		while(q.size()>1) {
			int num1=q.poll();
			int num2=q.poll();
			
			answer+=num1+num2;
			
			q.add(num1+num2);
		}
		System.out.println(answer);
	}

}
