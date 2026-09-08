package commonJavaCoding;

import java.util.HashMap;

public class _03_CountWordsInString {


    public static HashMap<String,Integer> getWordCount(String str){
        String[] words = str.trim().split("\\s+");
        HashMap<String,Integer> map=new HashMap<>();
        for(String s:words){
            map.put(s,map.getOrDefault(s,0)+1);
        }
        return map;
    }


    public static void main(String[] args) {
        HashMap<String, Integer> res = getWordCount("hello i am sahil vishwakarma and i am a java developer sahil vishwakarma");
        System.out.println(res);
    }


}
