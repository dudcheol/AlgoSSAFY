//시간이 400대가 나옴....
package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 센서 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		StringTokenizer st=new StringTokenizer(br.readLine());
		ArrayList<Integer> li=new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			int sensor=Integer.parseInt(st.nextToken());
			if(!li.contains(sensor))//센서 중복되지않게
				li.add(sensor);
		}
		Collections.sort(li);//오름차순 정렬
		
		PriorityQueue<Integer> pq=new PriorityQueue<>(Collections.reverseOrder());//센서사이 거리로 내림차순
		
		for (int i = 0; i < li.size()-1; i++) {
			pq.add(li.get(i+1)-li.get(i));
		}
		
		int min= li.get(li.size()-1)-li.get(0);//센서의 처음에서 끝까지 거리
		
		for (int i = 1; i < K&&!pq.isEmpty(); i++) {
			min-=pq.poll();
		}
	
		System.out.println(min);

	}

}
