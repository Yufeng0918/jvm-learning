package jvm.learning.wk12;

import java.util.ArrayList;
import java.util.List;

public class Demo3 {

    public static void main(String[] args) {
        long counter = 0;
        List<Object> list = new ArrayList<>();
        while (true) {
            list.add(new Object());
            System.out.println("create object " + (++counter));
        }
    }
}
