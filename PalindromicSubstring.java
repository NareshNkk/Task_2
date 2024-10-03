import java.util.Scanner;

public class PalindromicSubstring {
    private static boolean checkPalin(String s, int startIn, int endIn) {
        while (startIn < endIn) {
            if (s.charAt(startIn) != s.charAt(endIn)) {
                return false;
            }
            startIn++;
            endIn--;
        }
        return true;
    }

    public static String findPalin(String s) {
        int longLen = 0;
        String longSubstr = "";
        int length = s.length();
        for (int start = 0; start < length; start++) {
            for (int end = start; end < length; end++) {
                if (checkPalin(s, start, end)) {
                    int currLen = end - start + 1;
                    if (currLen > longLen) {
                        longLen = currLen;
                        longSubstr = s;
                        longSubstr = extSubstr(s, start, end);
                    }
                }
            }
        }
        return longSubstr;
    }

    private static String extSubstr(String s, int start, int end) {
        StringBuilder result = new StringBuilder();
        for (int i = start; i <= end; i++) {
            result.append(s.charAt(i));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner Scan = new Scanner(System.in);
        System.out.print("Enter a string:");
        String Input = Scan.nextLine();
        String longestPalindrome = findPalin(Input);
        System.out.println("Palindromic Substring: " + longestPalindrome);
        Scan.close();
    }
}
