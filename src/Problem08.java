public class Problem08 {
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            count += expandAroundCenter(s, i, i);

            count += expandAroundCenter(s, i, i + 1);
        }

        return count;
    }

    private int expandAroundCenter(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
        return count;
    }
}