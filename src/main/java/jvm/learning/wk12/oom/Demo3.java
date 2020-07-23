package jvm.learning.wk12.oom;

import java.util.ArrayList;
import java.util.List;

public class Demo3 {

    public static void main(String[] args) {
        long counter = 0;
//        List<Object> list = new ArrayList<>();
        List<byte[]> list = new ArrayList<>();
        while (true) {
            list.add(new byte[128 * 1024]);
            System.out.println("create object " + (++counter));
        }
    }
}
