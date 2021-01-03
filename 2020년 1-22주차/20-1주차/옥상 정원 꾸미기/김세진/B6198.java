import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B6198 {

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int N=Integer.parseInt(br.readLine());
		long[] arr=new long[N];
		
		for(int i=0;i<N;i++) {
			arr[i]=Long.parseLong(br.readLine());
		}
		long sum=0;
		for(int i=0;i<N-1;i++) {
			for(int j=i+1;j<N;j++) {
				if(arr[i]<=arr[j]) {
					break;
				}
				sum+=1;
			}
		}
		System.out.println(sum);
	}

}
