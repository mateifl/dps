package test;

import java.util.HashSet;
import java.util.Set;



public class Main3 {

    public static void main(String[] args) {
        Main3 m = new Main3();
        int result  = m.solution(10000);
        System.out.println(result);
//        result  = m.solution(new int[]{3, 4, 1, 5, 7, 2, 8, 6, 9, 0});
//        System.out.println(result);
//        result  = m.solution(new int[]{-1, 1, 1, 1, 1});
//        System.out.println(result);
    }

    int solution(int N) {
        String nString = "" + N;

        Set<String> s1 = new HashSet<>();
        s1.add(nString.substring(0, 1));

        Set<String> s2 = new HashSet<>();

        for(int i = 1; i < nString.length(); i++) {
            for(String s : s1) {
                if(nString.charAt(i) != '0')
                    s2.add("" + nString.charAt(i) + s);
                for(int j = 0; j < s.length(); j++){
                    s2.add( s.substring(0, j + 1) + nString.charAt(i) + s.substring(j + 1));
                }
            }

            s1 = s2;
            s2 = new HashSet<>();
        }

        return s1.size();
    }
}
