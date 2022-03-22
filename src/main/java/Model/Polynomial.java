package Model;

import Exceptions.WrongFormatException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Character.isDigit;

public class Polynomial {

    private List<Monomial> monomialList = new ArrayList<>();
    private static final int gradeMaxOfPolynomial = 100;

    public List<Monomial> getMonomialList() {
        return monomialList;
    }

    public void setMonomialList(List<Monomial> monomialList) {
        this.monomialList = monomialList;
    }
    public Polynomial(List<Monomial> monomialList) {
        this.monomialList = monomialList;
    }

    public Polynomial() {
    }

    public String constantAdjust(String polynomial) {
        if (isDigit(polynomial.charAt(0)))
            polynomial = "+" + polynomial;
        polynomial = polynomial + "+";
        int ok = 1;
        while (ok == 1) {
            Pattern pattern1 = Pattern.compile("[+-]([0-9][*/])*?[0-9]+(\\.[0-9]+)?[+-]");
            Matcher matcher1 = pattern1.matcher(polynomial);
            if (matcher1.find()) {
                int pozStart = matcher1.start();
                int pozEnd = matcher1.end();
                polynomial = polynomial.replace(polynomial.substring(pozStart, pozEnd), polynomial.substring(pozStart, pozEnd - 1) + "*X^0" + polynomial.charAt(pozEnd - 1));
            } else {
                ok = 0;
            }
        }
        polynomial = polynomial.substring(0, polynomial.length()-1);
        return polynomial;
    }

    public String polynomialAdjust(String polynomial) {
        polynomial = constantAdjust(polynomial);
        if (polynomial.charAt(0) == 'X')
            polynomial = "+1" + polynomial;
        if (polynomial.contains("X+"))
            polynomial = polynomial.replace("X+", "X^1+");
        if (polynomial.contains("X-"))
            polynomial = polynomial.replace("X-", "X^1-");
        Pattern end = Pattern.compile("X$");
        Matcher mat = end.matcher(polynomial);
        StringBuffer sb = new StringBuffer();
        if (mat.find()) {
            mat.appendReplacement(sb, "X^1");
            polynomial = sb.toString();
        }
        if (polynomial.contains("-"))
            polynomial = polynomial.replace("-", "+-");
        if (polynomial.contains("-X"))
            polynomial = polynomial.replace("-X", "-1X");
        if (polynomial.contains("+X"))
            polynomial = polynomial.replace("+X", "+1X");
        if (polynomial.contains("*X"))
            polynomial = polynomial.replace("*X", "X");
        return polynomial;
    }

    public void removeFloatingExponent(String polynomial) throws WrongFormatException {
        for (int i = 0; i < polynomial.length(); i++)
            if (polynomial.charAt(i) == '^') {
                for (int j = i + 1; j < polynomial.length(); j++) {
                    if (isDigit(polynomial.charAt(j)) == false && polynomial.charAt(j) != '+' && polynomial.charAt(j) != '-')
                        throw new WrongFormatException();
                    else if (polynomial.charAt(j) == '+' || polynomial.charAt(j) == '-')
                        break;
                }
            }
    }

    public void isPolynomial(String polynomial) throws WrongFormatException {

        String invalidAtTheEnd = "-+*/";
        Pattern notValid = Pattern.compile("/X+|\\+\\+|\\+-|-\\+|--|\\*/|/0([-+X*/]|$)");
        if (notValid.matcher(polynomial).find() == true)
            throw new WrongFormatException();
        if (polynomial.isEmpty() || invalidAtTheEnd.contains(Character.toString(polynomial.charAt(polynomial.length() - 1))))
            throw new WrongFormatException();

        removeFloatingExponent(polynomial);

        Pattern pattern = Pattern.compile("^([-+]?([0-9]*\\.?[0-9]+\\*?/?)?(X(\\^[0-9]+)?)?)+");
        Matcher matcher = pattern.matcher(polynomial);
        if (matcher.matches()) {
            polynomial = polynomialAdjust(polynomial);
            splitPolynomial(polynomial);
            groupMonomials();
            return;
        }
        throw new WrongFormatException();
    }

    public void splitPolynomial(String polynomial) {
        float coefficient ;
        int grade;
        String[] positiveTerms = polynomial.split("\\+");
        for (int i = 1; i < positiveTerms.length; i++) {
            String[] terms = positiveTerms[i].split("X\\^");
            if (terms[0].contains("*") || terms[0].contains("/"))
                coefficient = Monomial.evaluateExpression(terms[0]);
            else
                coefficient = Float.parseFloat(terms[0]);
            grade = Integer.parseInt(terms[1]);
            Monomial monomial = new Monomial(grade, coefficient);
            monomialList.add(monomial);
        }
    }

    public void groupMonomials() {
        float[] coef = new float[gradeMaxOfPolynomial + 1];
        Arrays.fill(coef, 0.0f);
        for (int i = gradeMaxOfPolynomial; i >= 0; i--)
            for (Monomial monomial : this.monomialList)
                if (monomial.getGrade() == i)
                    coef[i] = coef[i] + monomial.getCoefficient();
        List<Monomial> newMonomialList = new ArrayList<>();
        for (int i = gradeMaxOfPolynomial; i >= 0; i--)
            if (coef[i] != 0)
                newMonomialList.add(new Monomial(i, coef[i]));
        this.monomialList = newMonomialList;
    }

    public String polynomialToString() {
        String polynomialText = "";
        if(monomialList.size() == 0)
            polynomialText = "0";
        if(monomialList.size() == 1) {
            polynomialText = monomialList.get(0).monomialToString();
            if(polynomialText.charAt(0)=='+')
                 polynomialText = polynomialText.substring(1);
            return polynomialText;
        }
        else{
            for (Monomial monomial : monomialList) {
                if(monomial.monomialToString()!="0")
                    polynomialText = polynomialText + monomial.monomialToString();
            }
        }
        if(polynomialText == "")
            return "0";
        if (polynomialText.charAt(0) == '+') {
            polynomialText = polynomialText.substring(1);
       }
        return polynomialText;
    }
}