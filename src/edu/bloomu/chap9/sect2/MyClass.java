package edu.bloomu.chap9.sect2;

/**
 * @author Nathaniel Bladen
 */
public class MyClass {
    private final String str;


    public MyClass(String str){
        this.str = str;
    }

    @Override
    public String toString(){
        return str;
    }

    /**
    @Override
    public int compareTo(Object o){
        MyClass m = (MyClass) o;
        int len1 = str.length();
        int len2 = m.toString().length();
        return len1 - len2;
    }
    */
}
