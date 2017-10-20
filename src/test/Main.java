package test;

public class Main {

    public static void main(String[] args) {
        Main m = new Main();
        int result  = m.solution(new int[]{4, 5, 1, 1, 1, 1, 4, 3,1});
        System.out.println(result);
        result  = m.solution(new int[]{1, 2, 3, 0, 6});
        System.out.println(result);
        result  = m.solution(new int[]{1, 1, 1, 1, 1});
        System.out.println(result);
    }

    int solution(int[] A) {
        int sum = 0;
        for(int a : A)
            sum += a;
//        System.out.println(sum);
        int s1 = 0;
        int s3 = 0;
        int j = 0;
        for(int i = 0; i < A.length / 2; i++) {
            s1 += A[i];

            while(s3 < s1) {
                s3 += A[A.length - 1 - j];
                j += 1;
            }
            j -= 1;
//            System.out.println("" + s1 + " " + s3);
            if(s1 != s3 && s1 > (sum - s1 - s3 - A[i + 1] - A[A.length - 2 - j]))
                return 0;


            if(s1 == s3 && s1 == (sum - s1 - s3 - A[i + 1] - A[A.length - 2 - j]))
                return 1;
        }

        return 0;
    }
}
