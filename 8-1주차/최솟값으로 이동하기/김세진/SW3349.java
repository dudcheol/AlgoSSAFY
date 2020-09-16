import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class SW3349 {
	static class Node{
		int x;
		int y;
		public Node(int x, int y) {
			
			this.x = x;
			this.y = y;
		}
		
	}
	
	
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int t=Integer.parseInt(br.readLine());
		
		for(int TC=1;TC<=t;TC++) {
			String[] input=br.readLine().split(" ");
			int W=Integer.parseInt(input[0]);
			int H=Integer.parseInt(input[1]);
			//목적지
			int N=Integer.parseInt(input[2]);
			//cost는 항상 1 동일
			//시작점은 x1,y1
			//i번쨰 이동지점은 xi,yi
			//N에 도착하기 위해 드는 비용의 최소값을 구하라
			ArrayList<Node> list=new ArrayList<Node>(); 
			
			for(int i=0;i<N;i++) {
				input=br.readLine().split(" ");
				
				list.add(new Node(Integer.parseInt(input[0]),Integer.parseInt(input[1])));
			}
			
			//결과값을 위한 변수
			int count=0;
			for(int i=0;i<N-1;i++) {
				//앞에꺼
				int fX=list.get(i+1).x;
				int fY=list.get(i+1).y;
				
				//현재
				int rX=list.get(i).x;
				int xY=list.get(i).y;
				
				//현재값
				int Ncount=0;
				
				while(true) {
					//도착하면
					if(fX==rX && fY==xY){
						//더해주고 끝
						count+=Ncount;
						break;
					}
					
					//현재 위치가 더 크면 1씩 줄여줌
					if(fX<rX&&fY<xY) {
						rX--;
						xY--;
					}
					//현재 위치가 더 작으면 1씩 늘려줌
					else if(fX>rX&&fY>xY) {
						rX++;
						xY++;
					}
					//X만 더 크면 X만 줄여줌
					else if(fX<rX) {
						rX--;
					}
					//Y만 더 크면 Y만 줄여줌
					else if(fY<xY) {
						xY--;
					}
					//X만 더 작으면 X만 늘려줌
					else if(fX>rX) {
						rX++;
					}
					//Y만 더 작으면 Y만 들려줌
					else if(fY>xY) {
						xY++;
					}
					//모든 과정 중에는 현재 count를 늘려준다.
					Ncount++;
					
				}
				
			}
			
			
			System.out.println("#"+TC+" "+count);
			
			
		}
	}
		
		
		
}
