import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B13908 {
	
	static int N,M;
	static boolean visited[];
	static int solve;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer stz=new StringTokenizer(br.readLine());
		N=Integer.parseInt(stz.nextToken());
		M=Integer.parseInt(stz.nextToken());
		
		
		visited=new boolean[10];
		stz=new StringTokenizer(br.readLine());
		
		for(int i=0;i<M;i++) {
			visited[Integer.parseInt(stz.nextToken())]=true;
		}
		solve=0;
		perM(0,0);
		System.out.println(solve);
	}
	
	
	//1. 모든 숫자를 체크하면서 memory에 있는 숫자가 없으면 짜른다. -> 시간이 될까?
	//2. memory에 있는 숫자로 할 수 있는 모든 숫자를 체크한다. -> 답이 정확이 나올까?
	
	//다 잘못된 생각이였고 사용해야할 숫자 배열을 만들어서 돌리는 것이 답이였다.
	public static void perM(int depth,int count) {
		
		if(depth==N) {
			
			if(count!=M) {
				return;
			}
			solve++;
			return;
		}
		
		
		for(int i=0;i<=9;i++) {
			if(visited[i]) {
				visited[i]=false;
				perM(depth+1,count+1);
				visited[i]=true;
				continue;
			}
			perM(depth+1,count);
			
		}
		
	}

}
