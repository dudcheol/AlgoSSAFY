import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class B7662_timeover {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());

		while (TC-- > 0) {
			int T = Integer.parseInt(br.readLine());

			ArrayList<Integer> arr = new ArrayList<Integer>();

			for (int i = 0; i < T; i++) {
				String[] input = br.readLine().split(" ");

				// in
				if (input[0].equals("I")) {
					arr.add(Integer.parseInt(input[1]));
				}

				// delete
				else {
					// 최대값

					if (arr.isEmpty()) {
						continue;
					}

					if (input[1].equals("1")) {
						arr.remove(arr.size() - 1);
					}
					// 최솟값
					else {
						arr.remove(0);
					}
				}

				Collections.sort(arr);
			}
			
			if(!arr.isEmpty()) {
				System.out.println(arr.get(arr.size()-1)+" "+arr.get(0));
			}else {
				System.out.println("EMPTY");
			}
		}
	}

}
