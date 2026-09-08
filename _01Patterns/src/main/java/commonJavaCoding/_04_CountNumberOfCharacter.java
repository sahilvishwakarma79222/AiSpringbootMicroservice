package commonJavaCoding;

import java.util.HashMap;
import java.util.Map;

public class _04_CountNumberOfCharacter {

    public static HashMap<Character,Integer> getCharacterCount(String str){
        HashMap<Character,Integer> map=new HashMap<>();
        char[] ch = str.toCharArray();
        for(char c:ch){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        return map;
    }

    public static void main(String[] args){
        HashMap<Character, Integer> map = getCharacterCount("AAABBBCCCCDDDDDE");
        System.out.println(map);
    }
}
