package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// 프로그래머스 파일명 정렬 https://programmers.co.kr/learn/courses/30/lessons/17686
public class SortFileNames {

	//static String[] input = { "img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG" };
	static String[] input = { "F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat" };
	 
	public static void main(String[] args) {
		String[] answer = new String[input.length];
		
		answer = solution(input);
		for (int i = 0; i < input.length; i++) {
			System.out.println(answer[i]);
		}
	}
	
	public static String[] solution(String[] files) {
		String[] answer = {};
		
		ArrayList<FileName> filenames = new ArrayList<FileName>();
		String filename;
		String head;
		int num;
		
		// 1. head와 number 부분으로 나눔
		for (int j = 0; j < files.length; j++) {
			filename = files[j].toLowerCase(); // 영어는 다 소문자로 만들어줌
			
			StringTokenizer st = new StringTokenizer(filename, "0123456789"); // 숫자만날때까지
			StringTokenizer stNum = new StringTokenizer(filename, "abcdefghijklmnopqrstuvwxyz.- ");
			head = st.nextToken();
			num = Integer.parseInt(stNum.nextToken());
			
			filenames.add(new FileName(head, num, j));	
		}
		
		// 2. head 부분 기준으로 사전순 // 3. if head가 같다면, number 숫자 순으로 정렬 (숫자 앞 0은 무시)
		Collections.sort(filenames);
		
		answer = new String[files.length];
		// 4. 정렬한 Array에서 idx에 해당하는 파일이름을 files배열에서 꺼내서 answer 배열에 담기
		for (int i = 0; i < filenames.size(); i++) {
			answer[i] = files[filenames.get(i).getIdx()];
		}
		
		return answer;
		
	}
	
	static class FileName implements Comparable<FileName> {
		String head;
		int number;
		int idx;
		
		public FileName(String head, int number, int idx) {
			this.head = head;
			this.number = number;
			this.idx = idx;
		}

		public int getIdx() {
			return idx;
		}

		@Override
		public int compareTo(FileName o) {
			int result = this.head.compareTo(o.head);
			
			// 3. if head가 같다면, number 숫자 순으로 정렬 (숫자 앞 0은 무시)
			if(result == 0) {
				result = this.number - o.number;
				return result;
			} else {
				return result;
			}
		}
		
	}
}
