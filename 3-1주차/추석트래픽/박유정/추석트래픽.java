
public class 추석트래픽 {
	static double start, end;

	public int solution(String[] lines) {
		double sche[][] = new double[lines.length][2];
		for (int i = 0; i < lines.length; i++) {
			lines[i] = lines[i].replace("2016-09-15 ", "");
			String[] arr = lines[i].split(" ");
			String[] time_end = (arr[0].split(":"));// :로나누기

			double time = Double.parseDouble(arr[1].replace("s", "")) * 1000;// s자르기
			double endtime = Integer.parseInt(time_end[0]) * 60 * 60 * 1000 + Integer.parseInt(time_end[1]) * 60 * 1000
					+ Double.parseDouble(time_end[2]) * 1000;//숫지로만들기
			sche[i][0] = endtime - time + 1;//시작시간
			sche[i][1] = endtime;//끝나는시간
		}
		int max = 0;

		for (int i = 0; i < sche.length; i++) {
			start = sche[i][1];//끝나는 시간 기준
			end = start + 999;//1초뒤
			int count = 0;
			for (int j = i ;j < sche.length; j++) {

				if (check(sche[j][0], sche[j][1])) {//범위안에 있는지 체크
					
					count++;
				}
			}
			if (max < count)
				max = count;
		}

		return max;
	}

	public boolean check(double a, double b) {
		if (b >= start && b <= end)//오른쪽끝만 걸쳐있는경우
			return true;
		if (a >= start && a <= end)//왼쪽끝만 걸쳐있는경우
			return true;
		if (a >= start && b <= end)//안에있을경우
			return true;
        if (a <= start && b >= end)//중간에 걸쳐있을경우
			return true;
		return false;
	}

	public static void main(String[] args) {
		추석트래픽 s = new 추석트래픽();
		String arr[] = { "2016-09-15 20:59:57.421 0.351s", "2016-09-15 20:59:58.233 1.181s",
				"2016-09-15 20:59:58.299 0.8s", "2016-09-15 20:59:58.688 1.041s", "2016-09-15 20:59:59.591 1.412s",
				"2016-09-15 21:00:00.464 1.466s", "2016-09-15 21:00:00.741 1.581s", "2016-09-15 21:00:00.748 2.31s",
				"2016-09-15 21:00:00.966 0.381s", "2016-09-15 21:00:02.066 2.62s" };
		int m = s.solution(arr);
		System.out.println(m);
	}
}
