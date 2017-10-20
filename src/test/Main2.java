package test;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by matei on 01.07.2017.
 */
public class Main2 {
    public static void main(String[] args) {
        Main2 m = new Main2();
        int result  = m.solution(new int[]{1, 4, -1, 3, 2});
        System.out.println(result);
        result  = m.solution(new int[]{3, 4, 1, 5, 7, 2, 8, 6, 9, 0});
        System.out.println(result);
        result  = m.solution(new int[]{-1, 1, 1, 1, 1});
        System.out.println(result);
    }

    int solution(int[] A) {
        ArrayList<Integer> a = new ArrayList<Integer>();
        HashSet<Integer> set = new HashSet<Integer>();
        a.add(A[0]);
        set.add(0);
        int index = A[0];

        while (true) {
//            System.out.println(index);
            if(index >= A.length || index < 0 || set.contains(index))
                break;
            a.add(A[index]);
            set.add(index);
            index = A[index];
        }

        return a.size();
    }
}
