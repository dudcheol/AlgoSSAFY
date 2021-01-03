package vacation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 감소하는수 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int count = 0;
		
		for (long i = 1; count != n; i++) {
			int len=(int)Math.log10(i);//자릿수 구해주기
			long prev=i/(long)Math.pow(10,len);//첫번째 자리
			long na=i%(long)Math.pow(10,len);//첫번째 빼고 나머지수
			boolean flag=true;
			long cur=0;
			
			while(--len>=0) {
				cur=na/(long)Math.pow(10,len);
				if(prev<=cur) {//다음수가 같거나 클경우는 감소하는 수가 아님
					flag=false;
					break;
				}
				prev=cur;
				na%=(long)Math.pow(10,len);
			}
			if(flag) {//감소하는 수일경우
				count++;
			}
			else {
				i+=(long)Math.pow(10,len+1)-na-1;
			}
			if(count==n) {
				System.out.println(i);
				return;
			}else if(i==9876543210L) {//마지막수 나오면 끝
				System.out.println(-1);
				return;
			}
		}
		System.out.println(0);
		return;
		
	}

}
