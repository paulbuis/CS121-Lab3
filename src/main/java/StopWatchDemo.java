import cs121.StopWatch;

public class StopWatchDemo {
    static void demo1() {
        StopWatch timer = new StopWatch();
        System.out.printf("new StopWatch\n");
        timer.start();
        System.out.printf("started StopWatch\n");
        timer.stop();
        System.out.printf("stopped StopWatch, elapsed time=%f seconds\n",
                timer.elapsed());
    }

    static void demo2() {

        StopWatch timer = new StopWatch();
        System.out.printf("new StopWatch, elapsed time=%f seconds\n",
                timer.elapsed());
        timer.start();
        System.out.printf("started StopWatch, elapsed time=%f seconds\n",
                timer.elapsed());
        timer.start(); // should do nothing!
        timer.stop();
        timer.stop(); // should do nothing!
        System.out.printf("stopped StopWatch, elapsed time=%f\n",
                timer.elapsed());
    }

    static void demo3() {
        try {
            StopWatch timer = new StopWatch();
            System.out.printf("new StopWatch\n");
            System.out.printf("starting StopWatch\n");
            timer.start();
            Thread.sleep(999);
            timer.stop();
            System.out.printf("stopped StopWatch, elapsed time=%f seconds\n",
                    timer.elapsed());
        } catch (InterruptedException ie) {
            System.err.println("sleep interrupted!");

        }
    }



    public static void main(String[] args) {
        System.out.println("demo1");
        demo1();
        System.out.println("\ndemo2");
        demo2();
        System.out.println("\ndemo3");
        demo3();

    }
}
