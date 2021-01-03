package Graph;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1717 {

	static int[] arr;
	static int N,M;
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz=new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(stz.nextToken());
		M=Integer.parseInt(stz.nextToken());
		
		arr=new int[N+1];
		init();
		
		for(int i=0;i<M;i++) {
			stz=new StringTokenizer(br.readLine());
			
			int swt=Integer.parseInt(stz.nextToken());
			
			int a=Integer.parseInt(stz.nextToken());
			int b=Integer.parseInt(stz.nextToken());
			
			if(swt==0) {
				union(a,b);
			}else {
				if(find(a)==find(b)) {
					System.out.println("YES");
				}else {
					System.out.println("NO");
				}
			}
		}
		
			
	}
	
	public static void init() {
		for(int i=1;i<=N;i++) {
			arr[i]=i;
		}
	}
	
	
	public static void union(int a,int b) {
		int x=find(a);
		int y=find(b);
		
		arr[y]=x;
	}
	
	public static int find(int a) {
		if(arr[a]==a) {
			return a;
		}
		
		return arr[a]=find(arr[a]);
	}

}
