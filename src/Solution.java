import java.util.Arrays;

public class Solution {


    int recursion(int[] A, int lastIndex) {
        if(lastIndex == 1) {
            if(A[0] == A[1])
                return 1;
            else
                return 0;
        }

        int result = recursion(A, lastIndex - 1);

        for (int i = 0; i < lastIndex; i++) {
            if(A[i] == A[lastIndex])
                result += 1;
        }

        return result;
    }


    int memo(int[] A) {
        if(A.length < 2)
            return 0;

        int m[] = new int[A.length];
        m[0] = 0;
        if(A[0] == A[1])
            m[1] = 1;
        else
            m[1] = 0;


        for (int i = 2; i < A.length; i++) {
            int counter = 0;
            for (int j = 0; j < i; j++) {
                if(A[i] == A[j])
                    counter += 1;
            }
            m[i] = m[i - 1] + counter;
        }

        return m[A.length - 1];
    }

//        int solution2(int[] A) {
//            if(A.length < 2)
//                return 0;
//            return recursion(A, A.length - 1);
//        }

    int solution(int[] A) {
        return memo(A);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int input[] = new int[]{3, 5, 6, 3, 3, 5, 6, 1, 1, 3, 4, -1, 10, -1, 3, 5};
        int r = s.solution(input);
        System.out.println(r);
//        r = s.solution(input);
//        System.out.println(r);
        input = new int[]{3};
//        r = s.solution2(input);
//        System.out.println(r);
        r = s.solution(input);
        System.out.println(r);
    }

}
