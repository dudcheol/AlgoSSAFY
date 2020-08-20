package week4;

import java.util.ArrayList;

/**
 * 9:40
 * 12:13
 * ...하...
 */
public class JustNowSong {

    public String solution(String m, String[] musicinfos) {
        String answer = "";
        int maxPlayTime = 0;

        // 악보 파싱
        ArrayList<String> akbo = new ArrayList<>();
        for (int i = 0; i < m.length(); i++) {
            if (i + 1 < m.length() && m.charAt(i + 1) == '#') {
                akbo.add(m.substring(i, i + 2));
                i++;
            } else
                akbo.add(String.valueOf(m.charAt(i)));
        }

        // musicinfos 파싱하면서 정답구하기
        for (int i = 0; i < musicinfos.length; i++) {
            String[] musicinfo = musicinfos[i].split(",");

            String[] start = musicinfo[0].split(":");
            String[] end = musicinfo[1].split(":");
            String title = musicinfo[2];
            String melody = musicinfo[3];

            int sTime = Integer.parseInt(start[0]) * 60 + Integer.parseInt(start[1]);
            int eTime = Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1]);
            int playTime = eTime - sTime;

            // melody 파싱
            ArrayList<String> mels = new ArrayList<>();
            for (int j = 0; j < melody.length(); j++) {
                if (j + 1 < melody.length() && melody.charAt(j + 1) == '#') {
                    mels.add(melody.substring(j, j + 2));
                    j++;
                } else {
                    mels.add(String.valueOf(melody.charAt(j)));
                }
            }

            // mels를 이용해서 재생시간동안 재생했을 때 완성되는 악보 만들기
            int melSize = mels.size();
            int restPlaySize = playTime - melSize; // restPlaySize만큼만 더 재생하면 된다

            int k = 0;
            if (restPlaySize > 0) {
                // restPlaySize가 양수이면 restPlaySize만큼 더 재생
                while (k < restPlaySize) {
                    mels.add(mels.get(k++ % melSize));
                }
            } else if (restPlaySize < 0) {
                // restPlaySize가 음수이면 재생된 시간보다 멜로디의 크기가 큰 것이므로
                // 멜로디의 크기를 줄여서 재생시간과 맞춰야 함
                for (int j = playTime; j < mels.size(); j++) {
                    mels.remove(j);
                    j--;
                }
            }

            // 네오가 기억한 악보(akbo)와 재생 후 완성된 악보(mels)를 비교
            // 재생후 완성된 악보보다 네오가 기억한 악보가 더 크다면 해당 곡은 일치하는지 알 수 없음
            for (int l = 0; l < mels.size() - akbo.size() + 1; l++) {
                boolean find = true;
                int lStart = l;
                for (int n = 0; n < akbo.size(); n++) {
                    if (!mels.get(lStart++).equals(akbo.get(n))) {
                        find = false;
                        break;
                    }
                }
                if (find) {
                    // 조건이 일치하는 음악이 여러 개일 때에는 라디오에서 재생된 시간이 제일 긴 음악 제목을 반환한다.
                    // 재생된 시간도 같을 경우 먼저 입력된 음악 제목을 반환한다.
                    if (maxPlayTime < mels.size()) {
                        answer = title;
                        maxPlayTime = mels.size();
                    }
                }
            }
        }

        return answer.length() == 0 ? "(None)" : answer;
    }

    public static void main(String[] args) {
        JustNowSong justNowSong = new JustNowSong();

//        String m = "ABCDEFG";
//        String m = "CC#BCC#BCC#BCC#B";
        String m = "ABCD";
//        String[] musicinfos = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};
//        String[] musicinfos = {"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"};
        String[] musicinfos = {"03:00,03:03,FOO,ABCDE", "04:00,04:08,BAR,CC#BCC#BCC#B"};

        System.out.println(justNowSong.solution(m, musicinfos));
    }
}
