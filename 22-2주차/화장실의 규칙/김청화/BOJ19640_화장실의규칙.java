package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ19640_화장실의규칙 {
	static int N, M, K;
	static ArrayList<ArrayList<Employee>> employees = new ArrayList<>();
	static PriorityQueue<Employee> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		for (int i = 0; i < M; i++) {
			employees.add(new ArrayList<Employee>());
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			long h = Integer.parseInt(st.nextToken());
			
			if(i == K)
				employees.get(i % M).add(new Employee(i % M, d, h, true));
			else
				employees.get(i % M).add(new Employee(i % M, d, h, false));
		}
		
		for (int i = 0; i < M; i++) {
			if(employees.get(i).size() != 0) {
				pq.add(employees.get(i).get(0));
				employees.get(i).remove(0);
			}
		}
		int order = 0;
		while(true) {
			
			Employee cur = pq.poll();
			if(cur.deka) {
				System.out.println(order);
				break;
			}
			order++;

			if(employees.get(cur.line).size() != 0) {
				pq.add(employees.get(cur.line).get(0));
				employees.get(cur.line).remove(0);
			}
		}
	}
	
	static class Employee implements Comparable<Employee> {
		int line;
		int day;
		long hasty;
		boolean deka;
		
		Employee(int line, int day, long hasty, boolean deka) {
			this.line = line;
			this.day = day;
			this.hasty = hasty;
			this.deka = deka;
		}

		@Override
		public int compareTo(Employee o) {
			if(o.day != this.day)
				return o.day - this.day;
			else if (o.hasty != this.hasty)
				return (int) (o.hasty - this.hasty);
			else
				return this.line - o.line;
		}
	}
}