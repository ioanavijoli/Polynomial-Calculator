package Controller;

import Exceptions.ImpossibleToDivideException;
import Exceptions.WrongFormatException;
import Model.Operations;
import Model.Polynomial;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PolynomialCalculatorController {
    @FXML
    private Label resultLabel;
    @FXML
    private Label resultLabel1;
    private TextField selectedField;
    @FXML
    private TextField first_polynomial;
    @FXML
    private TextField second_polynomial;
    @FXML
    private Text wrong_format1;
    @FXML
    private Text wrong_format2;
    @FXML
    private TextField resultText;
    @FXML
    private TextField resultText1;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;
    @FXML
    private Button button9;
    @FXML
    private Button button0;
    @FXML
    private Button button_add;
    @FXML
    private Button button_subtract;
    @FXML
    private Button button_divide;
    @FXML
    private Button button_multiplicate;
    @FXML
    private Button button_power;
    @FXML
    private Button button_point;
    @FXML
    private Button buttonx;
    @FXML
    private Button button_del;
    @FXML
    private Button exit;

    public void initialize() {
        selectedField = first_polynomial;
        resultLabel.setText("Result = ");
        resultLabel1.setVisible(false);
        resultText1.setVisible(false);

    }

    @FXML
    public void onMouseClick1() {
        selectedField = first_polynomial;
    }

    @FXML
    public void onMouseClick2() {
        selectedField = second_polynomial;
    }

    public void deleteText() {
        if (selectedField.getText().length() > 0)
            selectedField.setText(selectedField.getText().substring(0, selectedField.getText().length() - 1));
    }

    @FXML
    public void setText() {
        button0.setOnMouseClicked(e -> {
            selectedField.setText(selectedField.getText() + "0");
        });
        button1.setOnMouseClicked(e -> {
            selectedField.setText(selectedField.getText() + "1");
        });
        button2.setOnMouseClicked(e -> {
            selectedField.setText(selectedField.getText() + "2");
        });
        button3.setOnMouseClicked(e -> {
            selectedField.setText(selectedField.getText() + "3");
        });
        button4.setOnMouseClicked(e -> {
            selectedField.setText(selectedField.getText() + "4");
        });
        button5.setOnMouseClicked(e -> {
            selectedField.setText(selectedField.getText() + "5");
        });
        button6.setOnMouseClicked(e -> {
            selectedField.setText(selectedField.getText() + "6");
        });
        button7.setOnMouseClicked(e -> {
            selectedField.setText(selectedField.getText() + "7");
        });
        button8.setOnMouseClicked(e -> {
            selectedField.setText(selectedField.getText() + "8");
        });
        button9.setOnMouseClicked(e -> {
            selectedField.setText(selectedField.getText() + "9");
        });
        button_add.setOnMouseClicked(e -> {
            selectedField.setText(selectedField.getText() + "+");
        });
        button_subtract.setOnMouseClicked(e -> {
            selectedField.setText(selectedField.getText() + "-");
        });
        button_divide.setOnMouseClicked(e -> {
            selectedField.setText(selectedField.getText() + "/");
        });
        button_multiplicate.setOnMouseClicked(e -> {
            selectedField.setText(selectedField.getText() + "*");
        });
        button_power.setOnMouseClicked(e -> {
            selectedField.setText(selectedField.getText() + "^");
        });
        button_point.setOnMouseClicked(e -> {
            selectedField.setText(selectedField.getText() + ".");
        });
        buttonx.setOnMouseClicked(e -> {
            selectedField.setText(selectedField.getText() + "X");
        });
        button_del.setOnMouseClicked(e -> {
            deleteText();
        });
    }

    @FXML
    public void onClearClick() {
        initialize();
        first_polynomial.setText("");
        second_polynomial.setText("");
        resultText.setText("");
        wrong_format1.setVisible(false);
        wrong_format2.setVisible(false);
    }

    public void setLabelTextForDerivativeOrIntegration(String operation) {
        if (operation == "derivative") {
            resultLabel.setText("Derivative P1:");
            resultLabel1.setText("Derivative P2:");
        } else {
            resultLabel.setText("Integration P1:");
            resultLabel1.setText("Integration P2:");
        }
    }

    public void selectedOperation(String operation, Polynomial firstPolynomial, Polynomial secondPolynomial, int selectedPolynomial) {
        initialize();
        switch (operation) {
            case "add":
                resultText.setText(Operations.add(firstPolynomial, secondPolynomial).polynomialToString());
                break;
            case "subtract":
                resultText.setText(Operations.subtract(firstPolynomial,secondPolynomial).polynomialToString());
                break;
            case "multiplication":
                resultText.setText(Operations.multiplication(firstPolynomial,secondPolynomial).polynomialToString());
                break;
            case "divide":
                try {
                    resultText.setText(Operations.division(firstPolynomial,secondPolynomial).polynomialToString());
                } catch (ImpossibleToDivideException e) {
                    wrong_format2.setVisible(true);
                    wrong_format2.setText("Impossible to divide");
                }
                break;
            case "derivative": {
                if (selectedPolynomial == 1) {
                    setLabelTextForDerivativeOrIntegration("derivative");
                    resultText.setText(Operations.derivative(firstPolynomial).polynomialToString());
                } else {
                    setLabelTextForDerivativeOrIntegration("derivative");
                    resultLabel1.setVisible(true);
                    resultText1.setVisible(true);
                    resultText1.setText(Operations.derivative(secondPolynomial).polynomialToString());
                }
                break;
            }
            case "integration": {
                if (selectedPolynomial == 1) {
                    setLabelTextForDerivativeOrIntegration("integration");
                    resultText.setText(Operations.integration(firstPolynomial).polynomialToString());
                } else {
                    setLabelTextForDerivativeOrIntegration("integration");
                    resultLabel1.setVisible(true);
                    resultText1.setVisible(true);
                    resultText1.setText(Operations.integration(secondPolynomial).polynomialToString());
                }
                break;
            }

        }
    }
    public void executeOperation(String operation) {
        Polynomial firstPolynomial = new Polynomial();
        Polynomial secondPolynomial = new Polynomial();
        try {
            wrong_format1.setVisible(false);
            firstPolynomial.isPolynomial(first_polynomial.getText());

        } catch (WrongFormatException e) {
            wrong_format1.setVisible(true);
        }
        try {
            wrong_format2.setVisible(false);
            secondPolynomial.isPolynomial(second_polynomial.getText());
            selectedOperation(operation, firstPolynomial, secondPolynomial, 0);

        } catch (WrongFormatException e) {
            wrong_format2.setVisible(true);
        }
    }

    public void derivativeOrIntegration(String operation) {
        Polynomial firstPolynomial = new Polynomial();
        Polynomial secondPolynomial = new Polynomial();
        try {
            firstPolynomial.isPolynomial(first_polynomial.getText());
            wrong_format1.setVisible(false);
            selectedOperation(operation, firstPolynomial, secondPolynomial, 1);
        } catch (WrongFormatException e) {
            wrong_format1.setVisible(true);
        }
        try {
            secondPolynomial.isPolynomial(second_polynomial.getText());
            wrong_format2.setVisible(false);
            selectedOperation(operation, firstPolynomial, secondPolynomial, 2);
        } catch (WrongFormatException e) {
            wrong_format2.setVisible(true);
        }
    }

    @FXML
    public void onAddClick() {
        executeOperation("add");
    }

    @FXML
    public void onSubtractClick() {
        executeOperation("subtract");
    }

    @FXML
    public void onMultiplicationClick() {
        executeOperation("multiplication");
    }
    @FXML
    public void onDivideClick() {
        executeOperation("divide");
    }
    @FXML
    public void onDerivativeClick() {
        derivativeOrIntegration("derivative");
    }

    @FXML
    public void onIntegrationClick() {
        derivativeOrIntegration("integration");
    }
    @FXML
    private void onExitClick(){
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }
}
