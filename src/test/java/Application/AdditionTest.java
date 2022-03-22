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

public class AdditionTest {
    @ParameterizedTest
    @MethodSource("provideInput")
   public void addTest(Polynomial polynomial1, Polynomial polynomial2, String expectedResult){
        assertEquals(expectedResult, Operations.add(polynomial1, polynomial2).polynomialToString());
    }
    private static List<Arguments> provideInput() throws WrongFormatException {
        List <Arguments> argumentsList = new ArrayList<>();
        Polynomial polynomial1 = new Polynomial();
        polynomial1.isPolynomial("X^8+2*X^5-6/2");
        Polynomial polynomial2 = new Polynomial();
        polynomial2.isPolynomial("-3X^5-1");
        String result1 = "X^8-X^5-4";
        argumentsList.add(Arguments.of(polynomial1, polynomial2,result1));

        Polynomial polynomial3 = new Polynomial();
        polynomial3.isPolynomial("2X^3+3X^2-X+5");
        Polynomial polynomial4 = new Polynomial();
        polynomial4.isPolynomial("1-X+X^2");
        String result2 = "2X^3+4X^2-2X+6";
        argumentsList.add(Arguments.of(polynomial3, polynomial4,result2));

        Polynomial polynomial5 = new Polynomial();
        polynomial5.isPolynomial("4/2X+0");
        Polynomial polynomial6 = new Polynomial();
        polynomial6.isPolynomial("X^4-2*3X^9");
        String  result3 = "-6X^9+X^4+2X";
        argumentsList.add(Arguments.of(polynomial5, polynomial6,result3));

        Polynomial polynomial7 = new Polynomial();
        polynomial7.isPolynomial("12/2X");
        Polynomial polynomial8 = new Polynomial();
        polynomial8.isPolynomial("0");
        String  result4 = "6X";
        argumentsList.add(Arguments.of(polynomial7, polynomial8,result4));

        return argumentsList;
    }
}
