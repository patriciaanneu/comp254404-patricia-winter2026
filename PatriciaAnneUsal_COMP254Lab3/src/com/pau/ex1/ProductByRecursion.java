public class ProductByRecursion {

    public static int recursiveProduct(int m, int n) {
        //base case - 0 * 0 = 0
        if (n == 0) {
            return 0;
        }
        //base case - 1 * m = m
        if (n == 1) {
            return m;
        }

        return m + recursiveProduct(m, n - 1);
    }
}

public static void main(String[] args) {

    int m = 12, n = 120;

    int result = ProductByRecursion.recursiveProduct(m, n);
    System.out.printf(m + " x " + n + " = " + result);
}