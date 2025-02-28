import java.util.function.DoubleToIntFunction;
import java.util.function.IntSupplier;

public class PrimitiveLambdas {
    public static void main(String[] args) {
        IntSupplier supplier = () -> 10;
        supplier.getAsInt();

        System.out.println("supplier = " + supplier.getAsInt());

        DoubleToIntFunction function = value -> (int)Math.floor(value);
        function.applyAsInt(Math.PI);
        System.out.println("PI = " + function.applyAsInt(Math.PI));
    }
}
