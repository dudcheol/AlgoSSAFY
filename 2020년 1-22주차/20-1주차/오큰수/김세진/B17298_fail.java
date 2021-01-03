import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B17298_fail {

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int N=Integer.parseInt(br.readLine());
		int[] arr=new int[N];
		
		StringTokenizer stz=new StringTokenizer(br.readLine());
		
		for(int i=0;i<N;i++) {
			arr[i]=Integer.parseInt(stz.nextToken());
		}
		StringBuilder sb=new StringBuilder();
		
		int value=-1;
		for(int i=0;i<N-1;i++) {
			
			value=-1;
			for(int j=i+1;j<N;j++) {
				if(arr[i]<arr[j]) {
					value=arr[j];
					break;
				}
			}
			
			if(value>0) {
				sb.append(value).append(" ");
			}else {
				sb.append(-1).append(" ");
			}
		}
		sb.append(-1).append(" ");
		
		System.out.println(sb.toString());
	}

}
