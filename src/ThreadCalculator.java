import java.util.function.DoubleUnaryOperator;

public class ThreadCalculator extends Thread {
    private IntegralCalculator calculator = new IntegralCalculator();
    private Main main;
    private double a;
    private double b;
    private int n;
    private DoubleUnaryOperator f;

    public ThreadCalculator(Main main, double a, double b, int n, DoubleUnaryOperator f) {
        this.main = main;
        this.a = a;
        this.b = b;
        this.n = n;
        this.f = f;
    }

    public double calculate(double a, double b, int n, DoubleUnaryOperator f) {
        return calculator.calculate(a, b, n, f);
    }

    @Override
    public void run() {
        double v = calculate(a, b, n, f);
        main.sendResult(v);
    }
}
