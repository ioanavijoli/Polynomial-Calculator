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

public class SubtractionTest {
    @ParameterizedTest
    @MethodSource("provideInput")
    public void subtractionTest(Polynomial polynomial1, Polynomial polynomial2, String expectedResult){
        assertEquals(expectedResult, Operations.subtract(polynomial1, polynomial2).polynomialToString());
    }
    private static List<Arguments> provideInput() throws WrongFormatException {
        List <Arguments> argumentsList = new ArrayList<>();
        Polynomial polynomial1 = new Polynomial();
        polynomial1.isPolynomial("2+4X^2+X^7-14");
        Polynomial polynomial2 = new Polynomial();
        polynomial2.isPolynomial("-12+X");
        String result1 = "X^7+4X^2-X";
        argumentsList.add(Arguments.of(polynomial1, polynomial2,result1));

        Polynomial polynomial3 = new Polynomial();
        polynomial3.isPolynomial("13X^4+8X^3-2/4X+5");
        Polynomial polynomial4 = new Polynomial();
        polynomial4.isPolynomial("13X^4+8X^3-2/4X+5");
        String result2 = "0";
        argumentsList.add(Arguments.of(polynomial3, polynomial4,result2));

        Polynomial polynomial5 = new Polynomial();
        polynomial5.isPolynomial("6/2X+2*5");
        Polynomial polynomial6 = new Polynomial();
        polynomial6.isPolynomial("X^4-2X^9");
        String  result3 = "2X^9-X^4+3X+10";
        argumentsList.add(Arguments.of(polynomial5, polynomial6,result3));

        Polynomial polynomial7 = new Polynomial();
        polynomial7.isPolynomial("0*X^2+2X");
        Polynomial polynomial8 = new Polynomial();
        polynomial8.isPolynomial("-1");
        String  result4 = "2X+1";
        argumentsList.add(Arguments.of(polynomial7, polynomial8,result4));

        return argumentsList;
    }
}
