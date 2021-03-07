package algorithm.programmers.kakaoBlind2021;

public class 신규_아이디_추천 {

    public String solution(String new_id) {
        String id = new_id;

        id = id.toLowerCase();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < id.length(); i++) {
            char c = id.charAt(i);
            if (('a'<=c&&c<='z') || ('A'<=c&&c<='Z') || ('0'<=c&&c<='9') || c=='-' || c=='_' || c=='.'){
                sb.append(c);
            }
        }

        id = sb.toString();
        sb.setLength(0);
        boolean isDot = false;
        for (int i = 0; i < id.length(); i++) {
            if (id.charAt(i) == '.') {
                if (!isDot) sb.append(id.charAt(i));
                isDot = true;
            } else {
                sb.append(id.charAt(i));
                isDot = false;
            }
        }
        id = sb.toString();

        while (!id.isEmpty() && id.charAt(0)=='.') id = removeStartDot(id);
        while (!id.isEmpty() && id.charAt(id.length()-1)=='.') id = removeEndDot(id);

        if (id.isEmpty()) {
            id = "a";
        }

        if (id.length() >= 16) {
            id = id.substring(0, 15);
            while (!id.isEmpty() && id.charAt(id.length()-1)=='.') id = removeEndDot(id);
        }

        if (id.isEmpty()) {
            id = "a";
        }

        char end = id.charAt(id.length()-1);
        while (id.length()<=2){
            id+=end;
        }

        return id;
    }

    private String removeEndDot(String id) {
        return id.substring(0, id.length()-1);
    }

    private String removeStartDot(String id) {
        return id.substring(1);
    }

    public static void main(String[] args) throws Exception {
        신규_아이디_추천 c = new 신규_아이디_추천();
//        String new_id = "...!@BaT#*..y.abcdefghijklm";
//        String new_id = "=.=";
//        String new_id = "abcdefghijklmn.p";
        String new_id = "z-+.^.";
        System.out.println(c.solution(new_id));
    }
}
