package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class Menus implements Comparable<Menus>{
	String menu;
	int cnt;
	
	public Menus(String menu, int cnt) {
		super();
		this.menu = menu;
		this.cnt = cnt;
	}

	@Override
	public int compareTo(Menus o) {
		return o.cnt-this.cnt;
	}
	
}

public class 메뉴리뉴얼 {
	boolean[] check = new boolean[11];// 메뉴 구성
	PriorityQueue<Menus> pq = new PriorityQueue<Menus>();
	Queue<String> q =new LinkedList<>();//이미 가지고 있는 코스 메뉴인지 확인용
	
	public String[] solution(String[] orders, int[] course) {
		for (int i = 0; i < course.length; i++) {
			check[course[i]] = true;
		}
		
		for (int i = 0; i < orders.length; i++) {//String정렬 wx xw같은 중복 때문에
			char[] arr=orders[i].toCharArray();
			Arrays.sort(arr);
			orders[i]=new String(arr);//이런것두 되넹
		}
		
		for (int i = 0; i < orders.length; i++) {//모든 주문을 부분집합돌림
			sub(new StringBuilder(), orders, 0,orders[i].length(),orders[i]);
		}
		
		ArrayList<String> al=new ArrayList<String>();
		int[] max=new int[11];//최대 반복횟수 저장할 배열
		
		while(!pq.isEmpty()) {
			Menus m=pq.poll();
			
			if(max[m.menu.length()]<=m.cnt) {
				al.add(m.menu);
				max[m.menu.length()]=m.cnt;
			}
		}
		
		Collections.sort(al);
		String[] answer=new String[al.size()];
		
		int idx=0;
		for(String s:al) {
			answer[idx++]=s;
		}
		
		System.out.println(Arrays.toString(answer));
		return answer;
	}
	
	//부분집합
	private void sub(StringBuilder sb, String[] orders, int count, int n, String menu) {
		if(count==n) {
			if(sb.length()<2||q.contains(sb.toString())||!check[sb.length()]) {
				//메뉴 길이가 2가 안됨 ,코스에 포함되어있지 않음, 이미 코스에 있음
				return;
			}
			
			int num=0;//주문수
			for (int i = 0; i < orders.length; i++) {
				int cnt=0;
				for (int j = 0; j < sb.length(); j++) {
					if(orders[i].contains(String.valueOf(sb.charAt(j)))) {
						cnt++;
					}
				}
				if(cnt==sb.length()) {
					num++;
				}
			}
			if(num>=2) {//단품메뉴 조합 가능
				pq.add(new Menus(sb.toString(),num));
				q.add(sb.toString());
			}
			return;
		}
		//부분집합
		StringBuilder sb1=new StringBuilder(sb);
		sub(sb1.append(String.valueOf(menu.charAt(count))),orders,count+1,n,menu);
		sub(sb,orders,count+1,n,menu);
	}
//	public static void main(String[] args) {
//		메뉴리뉴얼 me=new 메뉴리뉴얼();
//		me.solution(new String[]{"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"}, new int[]{2,3,5});
//	}
}
