package commonJavaCoding;

public class _01_StringReverse {

    public static String reverserString(String name){
        char[] ch=name.toCharArray();
        int low=0;int high=ch.length-1;
        while(low<=high){
            char temp=ch[low];
            ch[low]=ch[high];
            ch[high]=temp;
            low++;
            high--;
        }
        return new String(ch);

    }

    public static void main(String[] args) {
        String rev = reverserString("sahil");
        System.out.println(rev);
    }


}
