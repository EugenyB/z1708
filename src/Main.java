public class Main {

    double total = 0;
    int finished = 0;

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private void run() {
        double a = 0;
        double b = Math.PI;
        int n = 1000_000_000;
        long start = System.currentTimeMillis();

        int nThreads = 50;
        double delta = (b - a) / nThreads;
        for (int i = 0; i < nThreads; i++) {
            new ThreadCalculator(this, a + i * delta, a + (i+1)*delta, n / nThreads, Math::sin).start();
        }

        try {
            synchronized (this) {
                while (finished < nThreads) {
                    wait();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long finish = System.currentTimeMillis();
        System.out.println("total = " + total);
        System.out.println(finish-start);
//        IntegralCalculator calc = new IntegralCalculator();
//        long start = System.currentTimeMillis();
//        double v = calc.calculate(a, b, n, Math::sin);
//        long finish = System.currentTimeMillis();
//        System.out.println("v = " + v);
//        System.out.println(finish-start);
    }

    public synchronized void sendResult(double v) {
        total += v;
        finished++;
        notify();
    }
}
