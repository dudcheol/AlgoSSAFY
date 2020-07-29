import java.util.Arrays;

class SteppingStone {
	public static int solution(int[] stones, int k) {
		//실패코드
		int answer = 0;

		int min = Integer.MAX_VALUE;
		int max = -1;

		for (int i = 0; i < stones.length; i++) {
			max = Math.max(max, stones[i]);
        	min = Math.min(min, stones[i]);
		}

		while (min <= max) {
			boolean check=true;
			int count = 0;
			int avg = (min + max) / 2;
			
			for (int i = 0; i < stones.length; i++) {
				if (stones[i]-avg <= 0) {
					count++;
					if (count==k) {
						check=false;
						break;
					}		
				}else{
                    count = 0;
                }
			
				if (check) {					
					min = avg + 1;
				} else {

					max = avg - 1;
				}
			}			
		}
		return min;
	}


	public static void main(String[] args) {
		int[] arr = { 2, 4, 5, 3, 2, 1, 4, 2, 5, 1 };
		int answer = solution(arr, 3);
		System.out.println(answer);
	}
}