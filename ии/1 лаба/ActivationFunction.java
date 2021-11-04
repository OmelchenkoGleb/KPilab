import static java.lang.Math.exp;
import static java.lang.Math.pow;

public interface ActivationFunction {
    double ActivationFunction(Double x);
    double ActivationFunctionDx();
}
