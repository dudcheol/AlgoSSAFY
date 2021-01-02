/**
 * Backjoon - 19640. 화장실의 규칙
 * 화장실의규칙_19640.java
 * @date 2021-01-02
 * @author hansolKim
 **/

package p19000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Person implements Comparable<Person>{
	int day; // 근무일수
	int h; // 급한 정도
	int lineNumber; // 줄 번호
	boolean deca; // 데카의 구분

	public Person(int day, int h, int lineNumber, boolean deca) {
		this.day = day;
		this.h = h;
		this.lineNumber = lineNumber;
		this.deca = deca;
	}

	@Override
	public int compareTo(Person o) {
		if(this.day == o.day) { // 근무일수가 같을때
			if(this.h == o.h) { // 급한정도가 같을때
				return Integer.compare(this.lineNumber, o.lineNumber); // 줄 번호 오름차순 정렬
			}
			return Integer.compare(o.h, this.h); // 급한정도 내림차순
		}
		return Integer.compare(o.day, this.day); // 근무일수 내림차순
	}
}


public class 화장실의규칙_19640 {
	
	static int N, M, K; // 사원의 총 수, 새로운 줄 수, 자신의 앞에 서 있던 사원의 수
	static Queue<Person>[] lines;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		lines = new LinkedList[M];
		
		for(int i=0; i<M; i++) {
			lines[i] = new LinkedList<>();
		}
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int day = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			int line = i%M;
			boolean deca = i==K ? true : false;
			
			lines[line].add(new Person(day, h, line, deca));
		}
		
		
		PriorityQueue<Person> waitQueue = new PriorityQueue<>();
		
		for(int i=0; i<M; i++) {
			if(lines[i].isEmpty()) { break; }
			waitQueue.add(lines[i].poll());
		}
		
		int cnt = 0; // 데카보다 먼저 이용한 사람의 수
		int removeLine = 0; // 가장 최근에 빠져나갔던 줄 번호
		while(true) {
			
			Person usePerson = waitQueue.poll();
			if(usePerson.deca) { break; }
			removeLine = usePerson.lineNumber;
			
			if(!lines[removeLine].isEmpty()) {
				waitQueue.add(lines[removeLine].poll());
			}
			cnt++;
		}
		
		System.out.println(cnt);
		br.close();
	}
}