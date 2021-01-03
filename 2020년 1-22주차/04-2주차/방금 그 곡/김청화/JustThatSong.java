package programmers;

import java.util.ArrayList;

public class JustThatSong {
	
	// 악보에 사용되는 음 12개
	// 문자열 m 은 1 ~ 1439
	// musicinfos는 100개 이하의 곡 정보
	
	public static void main(String[] args) {
			
		//String m = "ABCDEFG";
		//String[] musicinfos = { "12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF" };
		//String m = "ABC";
		//String[] musicinfos = { "12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF" };
		
		//String m = "CC#BCC#BCC#BCC#B";
		//String[] musicinfos = { "03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B" };
		
		String m = "A#";
		String[] musicinfos = { "03:00,03:30,FOO,CC#B", "13:00,13:02,HAPPY,F#A#" };
		
		String answer = solution(m , musicinfos);

		System.out.println(answer);
	}

	public static String solution(String m, String[] musicinfos) {
		String[] songinfo = new String[4];
		String answer = ""; 
		int runtime;
		String runcode = "";
		boolean isIn = false;
		
		ArrayList<Song> song = new ArrayList<Song>();
		ArrayList<Song> resultsong = new ArrayList<Song>();	
		
		// 방송된 곡의 정보를 담고있는 배열 -> 노래 정보 저장
		for (int i = 0; i < musicinfos.length; i++) {
			songinfo = musicinfos[i].split(",");
			int startH = Integer.parseInt(songinfo[0].split(":")[0]);
			int startM = Integer.parseInt(songinfo[0].split(":")[1]);
			int endH = Integer.parseInt(songinfo[1].split(":")[0]);
			int endM = Integer.parseInt(songinfo[1].split(":")[1]);
		
			int start = startH*60 + startM;
			int end = endH*60 + endM;
			
			runtime = end - start; // 곡 재생시간
			
			song.add(new Song(start, runtime, songinfo[2], songinfo[3])); 
		}
		
		// 1. 재생시간 동안의 코드
		for (int i = 0; i < song.size(); i++) {
			song.get(i).code = song.get(i).code.replaceAll("C#", "H");
			song.get(i).code = song.get(i).code.replaceAll("D#", "I");
			song.get(i).code = song.get(i).code.replaceAll("F#", "J");
			song.get(i).code = song.get(i).code.replaceAll("G#", "K");
			song.get(i).code = song.get(i).code.replaceAll("A#", "L");			

			int repeat = song.get(i).runtime / song.get(i).code.length();//반복할 횟수를 몫으로
            int remain = song.get(i).runtime % song.get(i).code.length();//반복후에 몇 번째 멜로디까지 붙일건지

            // 멜로디를 반복 횟수만큼 추가
            for (int j = 0; j < repeat; j++) {
            	runcode += song.get(i).code;
            }
            runcode += song.get(i).code.substring(0, remain);
            
            song.get(i).code = runcode;
            runcode = "";
		}
		
		// 2. 주어진 코드로 음악을 찾는 과정
		m = m.replaceAll("C#", "H");
		m = m.replaceAll("D#", "I");
		m = m.replaceAll("F#", "J");
		m = m.replaceAll("G#", "K");
		m = m.replaceAll("A#", "L");
		
		for (int i = 0; i < song.size(); i++) {		
			System.out.println(m + " // " + song.get(i).code);
			if(song.get(i).code.contains(m)) {
				isIn = true;
				resultsong.add(song.get(i));
			}
		}
		
		// 조건이 일치하는 음악이 여러 개일 경우 라디오에서 재생된 시간이 제일 긴 음악 반환
		int index = 0;
		for (int i = 0; i < resultsong.size(); i++) {
			// 재생 시간이 같으면 먼저 시작한 곡 반환
			if(resultsong.get(index).runtime == resultsong.get(i).runtime) {
				if(resultsong.get(index).start > resultsong.get(i).start)
					index = i;
			} else if (resultsong.get(index).runtime < resultsong.get(i).runtime) { // 조건이 맞는다면 재생시간이 더 긴 곡 반환
				index = i;
			}
		}
		
		if(!isIn)
			return "(None)";		
		answer = resultsong.get(index).name;
		return answer;
	}
	
	public static class Song {
		int start; // 시작시간
		int runtime; // 재생시간
		String name;
		String code;
		
		
		public Song(int start, int runtime, String name, String code) {
			this.start = start;
			this.runtime = runtime;
			this.name = name;
			this.code = code;
		}
	}
}
