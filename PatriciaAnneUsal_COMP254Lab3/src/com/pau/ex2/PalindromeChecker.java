import java.util.Scanner;

public static boolean PalindromeChecker(String s) {
    if (s.length() <= 1) {
        return true;
    }

    char first = s.charAt(0);
    char last = s.charAt(s.length() - 1);

    if (first != last) {
        return false;
    }

    String middle = s.substring(1, s.length() - 1);
    return PalindromeChecker(middle);
}


public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter a word: ");
    String s = scanner.nextLine().toLowerCase();    //normalize the word

    if (PalindromeChecker(s)) {
        System.out.println(s + " is a palindrome.");
    }
    else {
        System.out.println(s + " is not a palindrome.");
    }
    scanner.close();
}