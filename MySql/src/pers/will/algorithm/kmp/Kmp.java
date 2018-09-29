package pers.will.algorithm.kmp;

import java.util.ArrayList;
import java.util.List;

// pers.个人名.项目名.模块名
public class Kmp {
    int[] calc_max_match_lengths(String pattern){
        int[] max_match_lengths = new int[pattern.length()];
        int max_length = 0;
        for (int i = 1; i < pattern.length(); i++){
            while(max_length > 0 && pattern.charAt(max_length) != pattern.charAt(i)){
                max_length = max_match_lengths[max_length - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(max_length)){
                max_length++;
            }
            max_match_lengths[i] = max_length;
        }
        return max_match_lengths;
    }

    List<Integer> search(String text, String pattern){
        List<Integer> positions = new ArrayList<>();
        int[] max_match_lengths = calc_max_match_lengths(pattern);
        int count = 0;
        for (int i = 0; i < text.length(); i++){
            while (count > 0 && pattern.charAt(count) != text.charAt(i)){
                count = max_match_lengths[count - 1];
            }
            if (pattern.charAt(count) == text.charAt(i)){
                count++;
            }
            if (count == pattern.length()){
                positions.add(i - pattern.length() + 1);
                count = max_match_lengths[count - 1];
            }
        }
        return positions;
    }

    public static void main(String[] args){
        Kmp test = new Kmp();
        System.out.println(test.search("abbaabbaaba", "abbaaba"));
    }
}
