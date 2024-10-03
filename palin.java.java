public class LongestPalindromicSubstring {

    // Function to check if a substring is a palindrome
    private static boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    // Function to find the longest palindromic substring
    public static String longestPalindromicSubstring(String s) {
        int maxLength = 0;
        String longestPalindrome = "";
        int n = s.length();

        // Check all substrings
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                // Check if the substring s[i:j+1] is a palindrome
                if (isPalindrome(s, i, j)) {
                    int currentLength = j - i + 1;
                    if (currentLength > maxLength) {
                        maxLength = currentLength;
                        longestPalindrome = s.substring(i, j + 1); // Using substring method here
                    }
                }
            }
        }

        return longestPalindrome;
    }

    public static void main(String[] args) {
        String input = "babad";
        String result = longestPalindromicSubstring(input);
        System.out.println("Longest Palindromic Substring: " + result);
    }
}
