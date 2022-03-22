package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Monomial{
    private int grade;
    private float coefficient;
    public Monomial(int grade, float coefficient) {
        this.grade = grade;
        this.coefficient = coefficient;
    }
    public static float evaluateExpression(String expression){
        String[] numbers = expression.split("[*/]");
        String operators = "";
        Pattern pattern = Pattern.compile ("[*/]");
        Matcher matcher = pattern.matcher(expression);
        while(matcher.find())
            operators = operators + matcher.group();
        float ans = Float.parseFloat(numbers[0]);
        for(int i=1; i< numbers.length;i++){
            if(operators.charAt(i-1)=='*')
                ans = ans*Float.parseFloat(numbers[i]);
            else
            if(operators.charAt(i-1)=='/')
                ans = ans/Float.parseFloat(numbers[i]);
        }
        return ans;
    }
    public Number coefficientToInteger(){
        if(coefficient - (int)coefficient == 0)
            return (int) coefficient;
        String format = String.format("%.2f", coefficient);
        coefficient = Float.valueOf(format);
        return coefficient;
    }

    public String monomialToString(){
        String monomial = "";
        String myCoefficient = coefficientToInteger().toString();
        if (coefficient == 0)
            return "0";
        else if (coefficient > 0)
            myCoefficient = "+" + myCoefficient;
        if (grade == 0)
            monomial = myCoefficient;
        else if (grade == 1)
            monomial =  myCoefficient + "X";
        else
            monomial =  myCoefficient + "X^" + grade;
        if(monomial.startsWith("+1X"))
            monomial = monomial.replace("+1X", "+X");
        if(monomial.startsWith("-1X"))
            monomial = monomial.replace("-1X", "-X");
        return monomial;
    }
    public Polynomial monomialToPolynomial(){
        List<Monomial> oneMonomial = new ArrayList<>(1);
        oneMonomial.add(this);
        Polynomial polynomial = new Polynomial(oneMonomial);
        return polynomial;
    }
    public Monomial monomialDivision(Monomial secondMonomial){

        Monomial result = new Monomial(grade - secondMonomial.grade, coefficient/secondMonomial.coefficient);
        return result;
    }
    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public float getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(float coefficient) {
        this.coefficient = coefficient;
    }


}
