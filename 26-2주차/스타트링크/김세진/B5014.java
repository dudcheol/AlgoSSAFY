import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B5014 {

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz=new StringTokenizer(br.readLine());
		
		int F=Integer.parseInt(stz.nextToken());
		int S=Integer.parseInt(stz.nextToken());
		int G=Integer.parseInt(stz.nextToken());
		int U=Integer.parseInt(stz.nextToken());
		int D=Integer.parseInt(stz.nextToken());
		
		
		//0이상이면 갔던 곳으로 하자
		int[] map=new int[F+1];
		
		//현재 위치를 넣는 tower 큐
		Queue<Integer> tower=new LinkedList<Integer>();
		
		tower.add(S);
		
		//틀린부분
		map[S]=1;
		//
		boolean flag=false;
		
		
		while(!tower.isEmpty()) {
			int index=tower.poll();
			
			if(index==G) {
				flag=true;
				break;
			}
			
			
			if(index+U<=F && map[index+U]==0) {
				map[index+U]=map[index]+1;
				tower.add(index+U);
				
			}
			
			if(index-D>0 && map[index-D]==0) {
				map[index-D]=map[index]+1;
				tower.add(index-D);
			}
		}
		
		if(flag) {
			System.out.println(map[G]-1);
		}else {
			System.out.println("use the stairs");
		}
	}


}
