import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class B7662 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());

		while (TC-- > 0) {
			long T = Integer.parseInt(br.readLine());

			TreeMap<Long, Long> map = new TreeMap<Long, Long>();

			for (int i = 0; i < T; i++) {

				String[] input = br.readLine().split(" ");

				Long value = Long.parseLong(input[1]);
//				input
				if (input[0].equals("I")) {
					if (map.containsKey(value)) {
						map.put(value, map.get(value) + 1);
					} else {
						map.put(value, (long) 1);
					}

				}

				// delete
				else {
					if (map.isEmpty()) {
						continue;
					}
					long keyValue = 0;
					
					if (value == -1) {
						keyValue = map.firstKey();
					} 
					
					else {
						keyValue = map.lastKey();
					}
					
					
					long next = map.get(keyValue) - 1;

					if (next == 0) {
						map.remove(keyValue);
					} else {
						map.put(keyValue, next);
					}

				}
			}
			
			if(map.isEmpty()) {
				System.out.println("EMPTY");
			}else {
				System.out.println(map.lastKey()+" "+map.firstKey());
			}

		}
	}

}
