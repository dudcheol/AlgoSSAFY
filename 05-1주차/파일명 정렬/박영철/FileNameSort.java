package week5;

import java.util.Arrays;

public class FileNameSort {

    class MyFile {
        String head;
        int number;
        String fileName;

        public MyFile(String head, int number, String fileName) {
            this.head = head;
            this.number = number;
            this.fileName = fileName;
        }
    }

    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        MyFile[] myFiles = new MyFile[files.length];

        // HEAD, NUMBER, TAIL 분리하기
        int fileIdx = 0;
        for (String file : files) {
            //HEAD는 숫자가 아닌 문자로 이루어져 있으며, 최소한 한 글자 이상이다.
            String head = "";
            int num = 0;
            int idx = 0;

            for (int i = 0; i < file.length(); i++) {
                char cur = file.charAt(i);
                // 아직 head를 발견하지 못했으므로 head 찾기
                if (Character.isDigit(cur)) {
                    // 순회하다가 숫자 발견시 그 전부분은 head
                    head = file.substring(0, i).toLowerCase();
                    idx = i;
                    break;
                }
            }

            // NUMBER는 한 글자에서 최대 다섯 글자 사이의 연속된 숫자로 이루어져 있으며,
            // 앞쪽에 0이 올 수 있다. 0부터 99999 사이의 숫자로, 00000이나 0101 등도 가능하다.
            StringBuilder sb = new StringBuilder();
            for (int j = idx; j < file.length(); j++) {
                // 5회까지 숫자를 찾다가 숫자가 아닌것을 발견하면 종료
                char curj = file.charAt(j);
                if (Character.isDigit(curj)) {
                    sb.append(curj);
                } else break;
                if (sb.length() == 5) break;
            }
            num = Integer.parseInt(sb.toString());

            // TAIL은 그 나머지 부분으로, 여기에는 숫자가 다시 나타날 수도 있으며, 아무 글자도 없을 수 있다.

            myFiles[fileIdx++] = new MyFile(head, num, file);
        }

        /* 주의 PriorityQueue는 stalbe하지 않으므로 같은 값일 때 정렬이 뒤바뀌지 않게 하기 위해선 사용하지 않도록 한다 */
        Arrays.sort(myFiles, (o1, o2) -> {
            // 파일명은 우선 HEAD 부분을 기준으로 사전 순으로 정렬한다. 이때, 문자열 비교 시 대소문자 구분을 하지 않는다.
            int res = o1.head.compareTo(o2.head);
            if (res == 0) {
                //파일명의 HEAD 부분이 대소문자 차이 외에는 같을 경우, NUMBER의 숫자 순으로 정렬한다.
                //두 파일의 HEAD 부분과, NUMBER의 숫자도 같을 경우, 원래 입력에 주어진 순서를 유지한다.
                return o1.number - o2.number;
            }
            return res;
        });

        for (int i = 0; i < files.length; i++) {
            answer[i] = myFiles[i].fileName;
        }

        return answer;
    }

    public static void main(String[] args) {
        FileNameSort fileNameSort = new FileNameSort();

        String[] files = {"img111111.png", "img11112.png", "img0101.png", "img1.png", "IMG01.GIF", "img2.JPG"};
        String[] files2 = {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"};
        String[] files3 = {"muzi1.txt", "MUZI1.txt", "muzi001.txt", "muzi1.TXT"};
        String[] files4 = {"F15", "F11", "F03", "F1", "F 0"};

        System.out.println(Arrays.toString(fileNameSort.solution(files)));
        System.out.println(Arrays.toString(fileNameSort.solution(files2)));
        System.out.println(Arrays.toString(fileNameSort.solution(files3)));
        System.out.println(Arrays.toString(fileNameSort.solution(files4)));
    }
}
