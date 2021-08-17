import java.util.function.DoubleUnaryOperator;

public class IntegralCalculator {
    public double calculate(double a, double b, int n, DoubleUnaryOperator f) {
        double h = (b-a)/n;
        double sum = 0;
        for (int i = 0; i < n; i++) {
            double x = a + i*h;
            double y = f.applyAsDouble(x);
            sum += y*h;
        }
        return sum;
    }
}
