import java.util.*;

public class 셔틀버스 {
	public String solution(int n, int t, int m, String[] timetable) {
		int ans=0;
		String arr[][] = new String[timetable.length][2];
		PriorityQueue<Integer> q = new PriorityQueue<>();//직원들 넣을 큐
		
		for (int j = 0; j < timetable.length; j++) {//시간을 분리
			arr[j] = timetable[j].split(":");
			int time=Integer.parseInt(arr[j][0])*60+Integer.parseInt(arr[j][1]);
			q.add(time);
		}
		
		int time=9*60;//셔틀첫차
		boolean flag=false;
		//마지막 셔틀일 때 가장 늦게탈 수있는 시간
		for (int i = 1; i <= n; i++) {
			if(time>=1339)//23시 59분
				q.clear();
			
			int count=0;
			while(!q.isEmpty()) {
				int crew=q.peek();
					
				if(crew==1339){//늦었으니 집에돌아가
					q.clear();
					break;}
				
				if(crew<=time) {//셔틀시간표보다 일찍왔으면 타
					if(i==n&&count==m-1){//막차 마지막 자리
						ans=crew-1;//마지막 애보다 1분 일찍오기
						flag=true;
						break;
					}
					q.poll();
					count++;
				}
				else break;//셔틀 시간보다 다음이면 중단
				
				if(count==m)//버스꽉찼으면 중단
					break;
			}
			if(!flag&&i==n) //막차 자리 널널
				ans=time;
			
			time+=t;//다음 셔틀시간
		}
		String hour=ans/60+"";
		if(hour.length()==1)//한자리일경우 앞에 0붙이기
			hour="0"+hour;
		String min=ans%60+"";
		if(min.length()==1)
			min="0"+min;
		return hour+":"+min;
	}

}
