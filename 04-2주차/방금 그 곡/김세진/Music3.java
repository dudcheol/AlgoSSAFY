package de;

public class Music3 {

	public static void main(String[] args) {
		System.out.println(solution("A#", new String[] { "13:00,13:02,HAPPY,B#A#"}));
		System.out.println(solution("ABCDEFG", new String[] { "12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF" }));
		System.out.println(solution("AAAAAA", new String[] { "04:00,04:20,HELLO,A"}));
		

	}

	static public String solution(String m, String[] musicinfos) {
		String answer = "(None)";
		int len = musicinfos.length;
		String[] sTime = new String[len];
		String[] eTime = new String[len];
		String[] mName = new String[len];
		String[] music = new String[len];
		int[] time = new int[len];

		// #->소문자 변경
		m = m.replace("C#", "c").replace("D#", "d").replace("F#", "f").replace("A#", "a").replace("G#", "g");
		
		// 분야별로 나누기
		for (int i = 0; i < len; i++) {
			String[] input = musicinfos[i].split(",");
			sTime[i] = input[0];
			eTime[i] = input[1];
			mName[i] = input[2];
			music[i] = input[3].replace("C#", "c").replace("D#", "d").replace("F#", "f").replace("A#", "a")
					.replace("G#", "g");
			
		}

		// 시간 계산
		for (int i = 0; i < len; i++) {
			time[i] = calTime(sTime[i], eTime[i]);
		}
		int max=Integer.MIN_VALUE;
		// 악보 풀세팅
		for (int i = 0; i < len; i++) {
			StringBuilder sb = new StringBuilder();

			for (int j = 0; j <= time[i]; j++) {
				sb.append(music[i].charAt(j % music[i].length()));
			}
			String k = sb.toString();
			
			if (k.contains(m)) {
			
				if(max<time[i]) {
					max=time[i];
					answer=mName[i];
				}
				
			}

		}
	    
	    return answer;
	    
	}
	public static int calTime(String sTime,String eTime) {
		String[] arr=sTime.split(":");
		String[] arr2=eTime.split(":");
		
		int sMin=Integer.parseInt(arr[0])*60+Integer.parseInt(arr[1]);
		int eMin=Integer.parseInt(arr2[0])*60+Integer.parseInt(arr2[1]);
		
		return eMin-sMin;
	}
}
