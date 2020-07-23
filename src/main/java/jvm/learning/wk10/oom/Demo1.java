package jvm.learning.wk10.oom;

import java.util.ArrayList;
import java.util.List;

public class Demo1 {

    public static void main(String[] args) throws Exception {
        List<Data> datas = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            datas.add(new Data());
        }
        Thread.sleep(1 * 60 *60 * 10000);
    }


    static class Data {
    }
}
