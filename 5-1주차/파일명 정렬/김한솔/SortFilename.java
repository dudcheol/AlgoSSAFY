/**
 * programmers - 2018 카카오 블라인드 채용. 파일명 정렬
 * SortFilename.java
 * @date 2020-08-25
 * @author hansolKim
 **/

package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class File implements Comparable<File> {
	String filename;
	String head;
	int number;
	
	public File(String filename, String head, int number) {
		this.filename = filename;
		this.head = head;
		this.number = number;
	}
	
	@Override
	public int compareTo(File o) {
		if(this.head.equals(o.head)) { // 파일명이 같은 경우
			return Integer.compare(this.number, o.number);
		}
		return this.head.compareTo(o.head);
	}
	
}

public class SortFilename {

	public static void main(String[] args) {
		SortFilename sortFilename = new SortFilename();
		String[] files = {"foo010bar020.zip"}; // foo, 010, bar020.zip
		String[] files2 = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
		String[] files3 = {"F-5 Freedom Fighter", "f-5 Freedom Fighter", "f-10 Thunderbolt II", "F-5 Tomcat"};
		sortFilename.solution(files3);
	}

	public String[] solution(String[] files) {
		String[] answer = {};
		List<File> fileList = new ArrayList<>();
		
		for(int i=0; i<files.length; i++) {
			String file = files[i];
			
			StringTokenizer st = new StringTokenizer(file, "0123456789");
			
			String head = st.nextToken(); // head를 가지고 옴
			head = head.toLowerCase(); // 소문자로 통일 
			
			String outOfHead = file.substring(head.length(), file.length()); // head를 제외한 문자열
			
			st = new StringTokenizer(outOfHead, "abcdefghijklmnopqrstuvwxyz .-");
			int number = Integer.parseInt(st.nextToken());
			
			fileList.add(new File(file, head, number));
		}
		
		Collections.sort(fileList); // 정렬
		
		answer = new String[fileList.size()];
		
		for(int i=0; i<fileList.size(); i++) {
			answer[i] = fileList.get(i).filename;
		}
		
		for(int i=0; i<fileList.size(); i++) {
			System.out.println(answer[i]);
		}
		
		return answer;
	}
}
