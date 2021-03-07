package programmers;

public class 신규아이디추천 {
	public String solution(String new_id) {
		// 1단계
		new_id = new_id.toLowerCase();
		// 2단계
//		new_id = new_id.replaceAll("[~!@#$%^&*()=+[{]}:?,<>/]", ""); 이거 왜안됨?
		new_id = new_id.replaceAll("[^0-9a-z_.-]","");
		// 3단계 
		for (int i = 0; i < 10; i++) {// 문자길이가 최대 1000이므로 최대 10번까지만 돌면된다
			new_id = new_id.replace("..", ".");
		}
		// 4단계
		if (new_id.charAt(0) == '.')
			new_id = new_id.substring(1);
		if (new_id.length() != 0 && new_id.charAt(new_id.length() - 1) == '.')
			new_id = new_id.substring(0, new_id.length() - 1);
		// 5단계
		if (new_id.length() == 0)
			new_id = "a";
		// 6단계
		if (new_id.length() >= 16) {
			new_id = new_id.substring(0, 15);
			if (new_id.charAt(14) == '.') {
				new_id = new_id.substring(0, 14);
			}
		}
		// 7단계
		if (new_id.length() <= 2) {
			StringBuilder sb = new StringBuilder(new_id);
			while (sb.length() < 3) {
				sb.append(sb.charAt(sb.length() - 1));
			}
			new_id = sb.toString();
		}

		System.out.println(new_id);

		return new_id;
	}

	public static void main(String[] args) {
		신규아이디추천 ne = new 신규아이디추천();
		ne.solution("=.=");
	}
}
