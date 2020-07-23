package jvm.learning.wk12.oom;

public class Demo2 {

    public static long counter = 2;

    public static void main(String[] args) {
            work();
    }

    public static void work() {
        System.out.println("counter invoke " + (++counter));
        work();
    }
}
