package Application;

import Exceptions.WrongFormatException;
import Model.Operations;
import Model.Polynomial;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class DerivativeTest {
    @ParameterizedTest
    @MethodSource("provideInput")
    public void derivativeTest(Polynomial polynomial1, String expectedResult){
        assertEquals(expectedResult, Operations.derivative(polynomial1).polynomialToString());
    }
    private static List<Arguments> provideInput() throws WrongFormatException {
        List <Arguments> argumentsList = new ArrayList<>();
        Polynomial polynomial1 = new Polynomial();
        polynomial1.isPolynomial("X^8+2*X^5-6/2");
        String result1 = "8X^7+10X^4";

        Polynomial polynomial2 = new Polynomial();
        polynomial2.isPolynomial("-4X^5+X^4-2X^3-1");
        String result2 = "-20X^4+4X^3-6X^2";


        Polynomial polynomial3 = new Polynomial();
        polynomial3.isPolynomial("2X^3+14+2*3X^3-X+5");
        String result3 = "24X^2-1";

        Polynomial polynomial4 = new Polynomial();
        polynomial4.isPolynomial("-X+5");
        String  result4 = "-1";

        Polynomial polynomial5 = new Polynomial();
        polynomial5.isPolynomial("23");
        String result5 ="0";

        Polynomial polynomial6 = new Polynomial();
        polynomial6.isPolynomial("14X");
        String result6 ="14";

        argumentsList.add(Arguments.of(polynomial1,result1));
        argumentsList.add(Arguments.of(polynomial2, result2));
        argumentsList.add(Arguments.of(polynomial3, result3));
        argumentsList.add(Arguments.of(polynomial4, result4));
        argumentsList.add(Arguments.of(polynomial5, result5));
        argumentsList.add(Arguments.of(polynomial6, result6));

        return argumentsList;
    }
}
