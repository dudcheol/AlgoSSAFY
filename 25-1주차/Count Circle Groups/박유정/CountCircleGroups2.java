package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CountCircleGroups {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		
		for (int t = 0; t <test; t++) {
			int n = Integer.parseInt(br.readLine());
			ArrayList<int[]> al=new ArrayList<int[]>();
			int answer=n;
			
			int[] parent=new int[n];
			for (int i = 0; i < parent.length; i++) {
				parent[i]=i;
			}
			
			for (int i = 0; i < n; i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int x=Integer.parseInt(st.nextToken());
				int y=Integer.parseInt(st.nextToken());
				int r=Integer.parseInt(st.nextToken());
				
				for(int j=0;j<al.size();j++) {
					int[] arr=al.get(j);
					int dx=arr[0]-x;
					int dy=arr[1]-y;
					int dr=r+arr[2];
					
					if(dx*dx+dy*dy<=dr*dr) {//두점사이의 거리가 두 반지름을 더한것 보다 작거나 같으면 겹침.
						if(union(parent,i,arr[3])) {
							answer--;
						}
					}
				}
				al.add(new int[] {x,y,r,i});
			}

			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}

	private static boolean union(int[] parent, int x, int y) {
		int ax=find(x,parent);
		int bx=find(y,parent);
		
		if(ax!=bx) { 
			parent[ax]=bx;
			return true;
		}
		
		return false;
	}

	private static int find(int x, int[] parent) {
		if(parent[x]==x)
			return x;
		return parent[x]=find(parent[x],parent);
	}

}
