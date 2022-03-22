package Model;

import Exceptions.ImpossibleToDivideException;

import java.util.ArrayList;
import java.util.List;

public class Operations {
    public static Polynomial add(Polynomial polynomial1, Polynomial polynomial2) {
        List<Monomial> newMonomialList = new ArrayList<Monomial>();
        int i = 0, j = 0;
        while (i < polynomial1.getMonomialList().size() && j < polynomial2.getMonomialList().size()) {
            if (polynomial1.getMonomialList().get(i).getGrade() > polynomial2.getMonomialList().get(j).getGrade()) {
                newMonomialList.add(new Monomial(polynomial1.getMonomialList().get(i).getGrade(), polynomial1.getMonomialList().get(i).getCoefficient()));
                i++;
            } else if (polynomial1.getMonomialList().get(i).getGrade() < polynomial2.getMonomialList().get(j).getGrade()) {
                newMonomialList.add(new Monomial(polynomial2.getMonomialList().get(j).getGrade(), polynomial2.getMonomialList().get(j).getCoefficient()));
                j++;
            } else {
                newMonomialList.add(new Monomial(polynomial1.getMonomialList().get(i).getGrade(), polynomial1.getMonomialList().get(i).getCoefficient() + polynomial2.getMonomialList().get(j).getCoefficient()));
                i++;
                j++;
            }
        }
        while (i < polynomial1.getMonomialList().size()) {
            newMonomialList.add(new Monomial(polynomial1.getMonomialList().get(i).getGrade(), polynomial1.getMonomialList().get(i).getCoefficient()));
            i++;
        }
        while (j < polynomial2.getMonomialList().size()) {
            newMonomialList.add(new Monomial(polynomial2.getMonomialList().get(j).getGrade(), polynomial2.getMonomialList().get(j).getCoefficient()));
            j++;
        }
        Polynomial result = new Polynomial(newMonomialList);
        return result;
    }

    public static Polynomial subtract(Polynomial polynomial1, Polynomial polynomial2) {
        for (Monomial monomial : polynomial2.getMonomialList())
            monomial.setCoefficient(-1.f * monomial.getCoefficient());
        return add(polynomial1, polynomial2);
    }

    public static Polynomial multiplication(Polynomial polynomial1, Polynomial polynomial2) {
        Polynomial result = new Polynomial();
        for (Monomial monomial1 : polynomial1.getMonomialList())
            for (Monomial monomial2 : polynomial2.getMonomialList())
                result.getMonomialList().add(new Monomial(monomial1.getGrade() + monomial2.getGrade(), monomial1.getCoefficient() * monomial2.getCoefficient()));
        result.groupMonomials();
        return result;
    }

    public static Polynomial division(Polynomial polynomial1, Polynomial polynomial2) throws ImpossibleToDivideException {
        if (polynomial2.getMonomialList().size() == 0)
            throw new ImpossibleToDivideException();
        List<Monomial> list = new ArrayList<>(1);
        list.add(new Monomial(0,0));
        Polynomial quotient = new Polynomial(list);
        if (polynomial1.getMonomialList().size() == 0)
            return quotient;
        Polynomial remainder = polynomial1;
        Monomial temp;
        Monomial leadingTermOfPolynomial2 = polynomial2.getMonomialList().get(0);
        int i = 0;
        while (remainder.polynomialToString() != "0" &&
                remainder.getMonomialList().get(i).getGrade() >= leadingTermOfPolynomial2.getGrade()) {
            temp = remainder.getMonomialList().get(i).monomialDivision(leadingTermOfPolynomial2);
            quotient = add(quotient, temp.monomialToPolynomial());
            remainder = subtract(remainder, multiplication(temp.monomialToPolynomial(), polynomial2));
            i++;
        }
        return quotient;
    }

    public static Polynomial derivative(Polynomial polynomial) {
        for (Monomial monomial : polynomial.getMonomialList()) {
            if (monomial.getGrade() == 0)
                monomial.setCoefficient(0);
            else {
                monomial.setCoefficient(monomial.getCoefficient() * monomial.getGrade());
                monomial.setGrade(monomial.getGrade() - 1);
            }
        }
        return new Polynomial(polynomial.getMonomialList());
    }

    public static Polynomial integration(Polynomial polynomial) {
        for (Monomial monomial : polynomial.getMonomialList()) {
            monomial.setCoefficient(monomial.getCoefficient() * (1.0f / (monomial.getGrade() + 1)));
            monomial.setGrade(monomial.getGrade() + 1);
        }
        return new Polynomial(polynomial.getMonomialList());
    }
}
