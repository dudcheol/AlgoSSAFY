/**
 * Backjoon - 1713. 후보 추천하기
 * RecommendCandidate_1713.java
 * @date 2020-11-29
 * @author hansolKim
 **/

package p1000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class RecommendCandidate_1713 {
	
	static int CANTDIDATE_CNT = 0; // 후보 수
	static int RECOMMEND_CNT = 0; // 추천 수
	static int MIN_RECOMMEND = 0; // 최소 추천 수
	static int[] persons;
	static Deque<Integer> candidates; // 후보 리스트
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		CANTDIDATE_CNT = Integer.parseInt(br.readLine()); // 후보 수 입력
		RECOMMEND_CNT = Integer.parseInt(br.readLine()); // 추천 수 입력
		
		persons = new int[101];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		candidates = new LinkedList<>();
		for(int i=0; i<RECOMMEND_CNT; i++) {
			int person = Integer.parseInt(st.nextToken());
			
			// 1. 이미 사진틀에 존재하는 후보인지 확인
			if(candidates.contains(person)) {
				persons[person]++;
				continue;
			} 
			
			// 2. 새로운 후보인 경우
			// 2-1. 사진틀이 비어있는 지 확인
			if(candidates.size() < CANTDIDATE_CNT) {
				insertCandidate(person);
				continue;
			} else { // 2-2. 사진틀이 비어있지 않은 경우
				
				MIN_RECOMMEND = getMinRecommend(); // 최소 추천 수 가 몇 개 인지 확인
				
				Stack<Integer> q = new Stack<>();
				while(persons[candidates.peek()] > MIN_RECOMMEND) { // 최소 추천수보다 높은 경우 ---> 뺏다 다시 넣음
					q.add(candidates.poll());
				}
				
				int deletePerson = candidates.poll(); // 사진틀 제거
				persons[deletePerson] = 0; // 후보 수 0으로 세팅

				int size = q.size();
				for(int j=0; j<size; j++) {
					candidates.addFirst(q.pop());
				}
				
				// 새로운 후보 삽입
				insertCandidate(person);
			}
		}
		
		int[] candidateList = new int[CANTDIDATE_CNT];
		
		for(int i=0; i<candidateList.length; i++) {
			int person = candidates.poll();
			candidateList[i] = person;
		}
		
		Arrays.sort(candidateList);
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<candidateList.length; i++) {
			sb.append(candidateList[i]).append(" ");
		}
		
		System.out.println(sb);
		br.close();
		
	}
	
	private static void insertCandidate(int person) {
		candidates.add(person);
		persons[person]++;
		MIN_RECOMMEND = 1;
	}
	
	private static int getMinRecommend() {
		
		int recommend = Integer.MAX_VALUE;
		
		for(int i=1; i<persons.length; i++) {
			if(persons[i]!=0 && persons[i] < recommend) {
				recommend = persons[i];
			}
		}
		
		return recommend;
	}
}
