package week6;

import java.util.Arrays;

public class 실패율 {
	
	class Stage implements Comparable<Stage>{
		int no;
		double notClearPlayer; // double연산은 double끼리 해야한다
		double clearPlayer; // 스테이지에 도달한 플레이어 
		
		public Stage(int no) {
			this.no = no;
			this.notClearPlayer = 0;
			this.clearPlayer = 0;
		}

		@Override
		public int compareTo(Stage o) {
			
			// 실패율
	        // 스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수 / 스테이지에 도달한 플레이어 수
			double mFail = this.notClearPlayer / this.clearPlayer;
			double oFail = o.notClearPlayer / o.clearPlayer;
			
//			if(mFail == oFail) {
//				return Integer.compare(this.no, o.no);
//			}
//			
//			return Double.compare(oFail, mFail); // 내림차순
			
			// Double.compare 하면 안된다... 왜??
			// double값에 의한 비교는 부동소수점에 의해 계산되므로 계산 결과가 부정확할 수 있음
			// Math.abs(c-1.0) <= 0.000001 이러한 형태를 사용해야 함
			
			if(mFail > oFail) {
				return -1;
			} else if (mFail < oFail) {
				return 1;
			} else {
				return Integer.compare(this.no, o.no);
			}
		}
	}
	
	public int[] solution(int N, int[] stages) {
		//전체 스테이지의 개수 N, 게임을 이용하는 사용자가 현재 멈춰있는 스테이지의 번호가 담긴 배열 stages
        int[] answer = new int[N];
        
        
        // 스테이지 생성
        Stage[] mStages = new Stage[N];
        for(int i=0;i<N;i++) {
        	mStages[i] = new Stage(i+1);
        }
        
        for(int stage : stages) {
        	// stage는 현재 멈춰있는 스테이지 번호이므로
        	// 그 번호 이전은 clear한 것이고 그 번호와 같으면 스테이지에 도달했으나 아직 클리어하지 못한 플레이어다 
        	
        	for (int i = 0; i < stage; i++) {
        		if(i == N) break;
        		if(i >= stage-1) {
        			mStages[i].clearPlayer++;
        			mStages[i].notClearPlayer++;
        		} else if( i < stage-1) {
        			mStages[i].clearPlayer++;
        		}
			}
        }

        Arrays.sort(mStages);
        
        for (int i = 0; i < mStages.length; i++) {
			answer[i] = mStages[i].no;
		}
        
        // 실패율이 높은 스테이지부터 내림차순으로 스테이지의 번호가 담겨있는 배열을 return
        return answer;
    }

	public static void main(String[] args) {
		실패율 m = new 실패율();
		
//		int N = 5;
		int N = 4;
		
//		int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};
		int[] stages = {4,4,4,4,4};
		
		System.out.println(Arrays.toString(m.solution(N, stages)));
	}

}

/*
import java.util.*;
class Solution {
    static class Stage {
		int stage;
		double fail;

		Stage(int s, double f) {
			this.stage = s;
			this.fail = f;
		}
	}
    public int[] solution(int N, int[] stages) {
       int[] answer = new int[N];
		int size = stages.length;
		Stage[] s = new Stage[N];

		for (int i = 0; i < N; i++) {
			int currentStageCnt = 0;
			int challenger = 0;
			for (int j = 0; j < size; j++) {
				if (i + 1 == stages[j]) {
					currentStageCnt++;
				}
				if (i + 1 <= stages[j]) {
					challenger++;
				}
			}
			//스테이지에 도달한 유저가 없는 경우 해당 스테이지의 실패율은 0
			double fail=0;
			if(currentStageCnt == 0) fail = 0;
			else fail = (double) currentStageCnt / (double) challenger;
			s[i] = new Stage(i + 1, fail);
		}

		Arrays.sort(s, new Comparator<Stage>() {
			@Override
			public int compare(Stage o1, Stage o2) {
				if (o1.fail > o2.fail) {
					return -1;
				} else if (o1.fail < o2.fail) {
					return 1;
				} else {
					//만약 실패율이 같은 스테이지가 있다면 작은 번호의 스테이지가 먼저 오도록 
					if (o1.stage > o2.stage) {
						return 1;
					} else
						return -1;
				}
			}
		});

		for (int i = 0; i < N; i++) {
			answer[i] = s[i].stage;
		}

		return answer;
    }
}
 */