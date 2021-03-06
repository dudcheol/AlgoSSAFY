/**
 * programmers - 2021 카카오 블라인드 채용. 신규 아이디 추천
 * 신규아이디추천.java
 * @date 2021-03-02
 * @author hansolKim
 **/

package programmers;

public class 신규아이디추천 {

	public static void main(String[] args) {
		신규아이디추천 신규아이디추천 = new 신규아이디추천();
		System.out.println(신규아이디추천.solution("...!@BaT#*..y.abcdefghijklm"));
	}

	public String solution(String new_id) {

		StringBuilder sb = new StringBuilder();
		// 1. 대문자 -> 소문자로 치환
		sb.append(new_id.toLowerCase());
				
		// 2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
		for(int i=0; i<sb.length(); i++) {
			if(!((sb.charAt(i) >= 97 
				&& sb.charAt(i) <= 122) ||
				(sb.charAt(i)-'0' >= 0 
				&& sb.charAt(i)-'0' <= 9) ||
				sb.charAt(i) == '-' ||
				sb.charAt(i) == '_' ||
				sb.charAt(i) == '.')) {
				sb.replace(i, i+1, "");
				i--;
			}
		}
				
		// 3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.		
		for(int i=0; i<sb.length()-1; i++) {			
			if(sb.charAt(i) == '.' && 
					sb.charAt(i+1) == '.') {
				sb.replace(i, i+2, ".");
				i--;
			}
		}
				
		// 4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
		if(sb.length() > 0 && sb.charAt(0) == '.') {
			sb.replace(0, 1, "");
		}
		
		if(sb.length() > 0 && sb.charAt(sb.length()-1) == '.') {
			sb.replace(sb.length()-1, sb.length(), "");
		}
		
		// 5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
		if(sb.length() == 0) {
			sb.append("a");
		}
		
		// 6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
		// 만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
		if(sb.length() >= 16) {
			sb.replace(15, sb.length(), "");
			if(sb.charAt(sb.length()-1) == '.') {
				sb.replace(sb.length()-1, sb.length(), "");
			}
		}
				
		// 7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
		while(sb.length() <= 2) {
			sb.append(sb.charAt(sb.length()-1));
		}

		return sb.toString();
	}
}
