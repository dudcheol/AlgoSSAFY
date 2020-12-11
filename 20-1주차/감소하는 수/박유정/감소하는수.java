package vacation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 감소하는수 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		if(n<=9) {
			System.out.println(n);
			return;
		}
		int count = 9;
		
		for (long i = 10; count != n; i++) {
			int len=(int)Math.log10(i);
			long prev=i/(long)Math.pow(10,len);
			long na=i%(long)Math.pow(10,len);
			boolean flag=true;
			long cur=0;
			
			while(--len>=0) {
				cur=na/(long)Math.pow(10,len);
				if(prev<=cur) {
					flag=false;
					break;
				}
				prev=cur;
				na%=(long)Math.pow(10,len);
			}
			if(flag) {
				count++;
			}
			else {
				i+=(long)Math.pow(10,len+1)-na-1;
			}
			if(count==n) {
				System.out.println(i);
				break;
			}else if(i==9876543210L) {
				System.out.println(-1);
				return;
			}
		}
	}

}
