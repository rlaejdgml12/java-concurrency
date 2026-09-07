package practice._2026_09_07;

public class StringPractice {

    static boolean isPalindrome(String s){
        int left = 0;
        int right = s.length() - 1;

        while (left < right){
            if(s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("level"));   // true
        System.out.println(isPalindrome("banana"));  // false
        System.out.println(isPalindrome("abba"));    // true

        String raw = "A man, a plan, a canal: Panama";
        String cleaned = raw.toLowerCase().replaceAll("[^a-z0-9]", "");
        System.out.println(cleaned);              // amanaplanacanalpanama
        System.out.println(isPalindrome(cleaned)); // true

    }

}
