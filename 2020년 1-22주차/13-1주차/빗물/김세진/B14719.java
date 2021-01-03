package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B14719 {

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer stz=new StringTokenizer(br.readLine());
		
		int H=Integer.parseInt(stz.nextToken());
		int W=Integer.parseInt(stz.nextToken());
		
		int[] arr=new int[W];

		
		stz=new StringTokenizer(br.readLine());
		for(int i=0;i<W;i++) {
			arr[i]=Integer.parseInt(stz.nextToken());
		}
		
		int count=0;
		
		for(int i=1;i<W-1;i++) {
			int leftMax=0;
			int rightMax=0;
			
			//왼쪽
			for(int j=i-1;j>-1;j--) {
				if(leftMax<arr[j]) {
					leftMax=arr[j];
				}
			}
			
			//오른쪽
			for(int j=i+1;j<W;j++) {
				if(rightMax<arr[j]) {
					rightMax=arr[j];
				}
			}
			
			
			if(arr[i]<leftMax && arr[i]<rightMax) {
				count+=Math.min(leftMax, rightMax)-arr[i];
			}
		}
		System.out.println(count);
	}

}
