/**
 * programmers - 2018 카카오 블라인드 채용. 방금 그 곡
 * RecentlyMusic.java
 * @date 2020-08-22
 * @author hansolKim
 **/

package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Music implements Comparable<Music>{
	String code;
	String title;

	public Music(String title, String code) {
		this.code = code;
		this.title = title;
	}
	
	public String getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}


	// 제약조건
	// 1. 음악시간으로 sorting(내림차순)
	// 2. 1번조건이 동일할 경우 먼저입력된 음악 리턴
	
	@Override
	public int compareTo(Music o) {
		return o.code.length()-this.code.length();
	}
}

public class RecentlyMusic {

	public static void main(String[] args) {
		RecentlyMusic recentlyMusic = new RecentlyMusic();
		String m = "ABC";
		String[] musicinfos = { "12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF" };
		String[] musicinfos2 = { "03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B" };
		String[] musicinfos3 = { "12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF" };
		recentlyMusic.solution(m, musicinfos3);
	}

	public String solution(String m, String[] musicinfos) {
		String answer = "(None)";
		List<Music> musicList = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		String mCode = "";
		
		/* m의 #코드 변환 */ 
		for(int i=0; i<m.length(); i++) {
			if(m.charAt(i) == '#') { // B#이들어온 경우 ---> b로 변환
				sb.append((char)(sb.charAt(sb.length()-1)+32));
				sb.deleteCharAt(sb.length()-2); // 
			} else {
				sb.append(m.charAt(i));
			}
		}
		
		mCode = sb.toString();
		
		sb.replace(0, sb.length(), "");

		// musicinfos
		// 시작시간과 끝시간 ==> 곡이 틀어져 있던 시간(차이값 계산)
		// 음악 제목
		// 곡이 틀어져 있던 시간 * 음악제목 문자열

		/** 리스트 자료구조에 저장(전처리 작업) */
		for (int i = 0; i < musicinfos.length; i++) {

			StringTokenizer st = new StringTokenizer(musicinfos[i], ","); // "12:00" "12:14" "HELLO" "CDEFGAB"

			StringTokenizer sTime = new StringTokenizer(st.nextToken(), ":");
			StringTokenizer fTime = new StringTokenizer(st.nextToken(), ":");

			int startTime = Integer.parseInt(sTime.nextToken()) * 60 + Integer.parseInt(sTime.nextToken());
			int finishTime = Integer.parseInt(fTime.nextToken()) * 60 + Integer.parseInt(fTime.nextToken());

			int playTime = finishTime - startTime; // 음악이 재생된 시간
			String title = st.nextToken(); // "HELLO"
			String code = st.nextToken(); // "CDEFGAB"			
			
			/* #코드 변환 */
			for(int j=0; j<code.length(); j++) {
				if(code.charAt(j) == '#') { // B#이들어온 경우 ---> b로 변환
					sb.append((char)(sb.charAt(sb.length()-1)+32));
					sb.deleteCharAt(sb.length()-2); // 
				} else {
					sb.append(code.charAt(j));
				}
			}
			
			String newCode = sb.toString();
			sb.replace(0, sb.length(), "");
			
			for(int j=0; j<playTime; j++) {
				sb.append(newCode.charAt(j%newCode.length()));
			}			
			
			musicList.add(new Music(title, sb.toString()));
			sb.replace(0, sb.length(), "");
		}
		
		Collections.sort(musicList);
		
		/** 로직 구현 */
		for(int i=0; i<musicList.size(); i++) {
			if(musicList.get(i).getCode().contains(mCode)) {
				answer = musicList.get(i).getTitle();
				break;
			}
		}
		
		System.out.println(answer);
		return answer;
	}

}
