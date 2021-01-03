import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 시험감독 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] stu = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			stu[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		long count = 0;
		for (int i = 0; i < stu.length; i++) {
			int num = stu[i] - B;
			count++;
			if(num<=0)
				continue;
			count+=num/C;
			num-=(num/C)*C;
			if(num>0)
				count++;
		}
		System.out.println(count);
	}
}
