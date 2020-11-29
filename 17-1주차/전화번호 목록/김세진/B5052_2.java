package string_handling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B5052_2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int t=Integer.parseInt(br.readLine());
		
		while(t-->0) {
			int n=Integer.parseInt(br.readLine());
			String[] arr=new String[n];
			
			
			for(int i=0;i<n;i++) {
				arr[i]=br.readLine();
			}
			
			Arrays.parallelSort(arr);
			
			boolean swt=true;
			
			for(int i=1;i<n;i++) {
				if(swt) {
					swt=!arr[i].startsWith(arr[i-1]);
					
				}
			}
			if(swt) {
				System.out.println("YES");
			}else {
				System.out.println("NO");
			}		
		}
	}
	

}
