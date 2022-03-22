package Application;

import Exceptions.ImpossibleToDivideException;
import Exceptions.WrongFormatException;
import Model.Operations;
import Model.Polynomial;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DivisionTest {
    @ParameterizedTest
    @MethodSource("provideInput")
    public void divideTest(Polynomial polynomial1, Polynomial polynomial2, String expectedResult){
        try {
            assertEquals(expectedResult, Operations.division(polynomial1, polynomial2).polynomialToString());
        } catch (ImpossibleToDivideException e) {
            System.out.println("Impossible to divide");
        }
    }
    @Test
    public void divideWrongTest() throws WrongFormatException {
        Polynomial polynomial1 = new Polynomial();
        polynomial1.isPolynomial("2X^3+3X+1");
        Polynomial polynomial2 = new Polynomial();
        polynomial2.isPolynomial("0");
        assertThrows(ImpossibleToDivideException.class, ()->{Operations.division(polynomial1,polynomial2);});}
    private static List<Arguments> provideInput() throws WrongFormatException, ImpossibleToDivideException{
        List <Arguments> argumentsList = new ArrayList<>();
        Polynomial polynomial1 = new Polynomial();
        polynomial1.isPolynomial("X^6+2X^4+6X-9");
        Polynomial polynomial2 = new Polynomial();
        polynomial2.isPolynomial("X^3+3");
        String result1 = "X^3+2X-3";
        argumentsList.add(Arguments.of(polynomial1, polynomial2,result1));

        Polynomial polynomial3 = new Polynomial();
        polynomial3.isPolynomial("2X^3+3X^2-X+5");
        Polynomial polynomial4 = new Polynomial();
        polynomial4.isPolynomial("1-X+X^2");
        String result2 = "2X+5";
        argumentsList.add(Arguments.of(polynomial3, polynomial4,result2));

        Polynomial polynomial5 = new Polynomial();
        polynomial5.isPolynomial("X^2+5");
        Polynomial polynomial6 = new Polynomial();
        polynomial6.isPolynomial("3X^4+X^3+2X");
        String  result3 = "0";
        argumentsList.add(Arguments.of(polynomial5, polynomial6,result3));

        Polynomial polynomial7 = new Polynomial();
        polynomial7.isPolynomial("X^2+7X^3+1");
        Polynomial polynomial8 = new Polynomial();
        polynomial8.isPolynomial("X^2+7X^3+1");
        String  result4 = "1";
        argumentsList.add(Arguments.of(polynomial7, polynomial8,result4));

        return argumentsList;
    }
}
