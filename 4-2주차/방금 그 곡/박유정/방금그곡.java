import java.util.PriorityQueue;

class Pair implements Comparable<Pair> {
	String name;
	int time;
	int order;

	public Pair(String name, int time, int order) {
		this.name = name;
		this.time = time;
		this.order = order;
	}

	@Override
	public int compareTo(Pair p) {
		if (this.time == p.time) {
			return this.order - p.order;
		}
		return p.time - this.time;
	}
}

public class 방금그곡 {
	public String solution(String m, String[] musicinfos) {
		String answer = "(None)";
		int start = 0, end = 0;
		String name = "", mel = "";
		PriorityQueue<Pair> q = new PriorityQueue<>();

		int len = 0;// m의 길이
		for (int i = 0; i < m.length(); i++) {// m의 멜로디 갯수 구하기
			if (i + 1 < m.length() && m.charAt(i + 1) == '#') {
				continue;
			}
			len++;
		}

		for (int i = 0; i < musicinfos.length; i++) {
			String[] music = musicinfos[i].split(",");

			String[] arr = music[0].split(":");
			start = Integer.parseInt(arr[0]) * 60 + Integer.parseInt(arr[1]);// 시작시간

			arr = music[1].split(":");
			end = Integer.parseInt(arr[0]) * 60 + Integer.parseInt(arr[1]);// 끝나는시간

			int time = end - start;// 재생시간

			name = music[2];// 이름
			mel = music[3];// 곡

			StringBuilder sb = new StringBuilder();
			int count = 0;// 멜로디 수 세기
			int index = 0;// mel인덱스
			int j = 0;// 초세기

			while (j <= time) {
				sb.append(mel.charAt(index % mel.length()));// 하나씩 붙임

				if (mel.charAt((index + 1) % mel.length()) == '#') {// 다음이#이면 같이붙임
					// 다음 번에 #이 있으면
					sb.append("#");
					index++;
				}
				count++;// 멜로디수 하나 증가
				j++;// 1초씩증가

				if (count == len) {
					// 길이 완성
					if (sb.toString().equals(m)) {// 멜로디가 같으면
						q.add(new Pair(name, time, i));
						break;
					}
					sb.delete(0, 1);// 하나뺌

					if (sb.length() != 0 && sb.charAt(0) == '#')// #도있으면 같이뺌
						sb.delete(0, 1);

					count--;// 멜로디수 하나뺌

				}
				index++;

			}
		}
		if (!q.isEmpty())
			answer = q.poll().name;
		return answer;
	}
}
