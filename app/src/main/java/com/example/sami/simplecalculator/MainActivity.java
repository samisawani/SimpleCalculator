package com.example.sami.simplecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {
    EditText operandsEditText;
    private String operationArea;
    private String[] numbersArray;
    private boolean flag = false;
    TextView resultAreaTextView;
    private String result;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        operationArea = "";
        result = "";
        Button oneButton = findViewById(R.id.oneButton);
        Button twoButton = findViewById(R.id.twoButton);
        Button threeButton = findViewById(R.id.threeButton);
        Button fourButton = findViewById(R.id.fourButton);
        Button fiveButton = findViewById(R.id.fiveButton);
        Button sixButton = findViewById(R.id.sixButton);
        Button sevenButton = findViewById(R.id.sevenButton);
        Button eightButton = findViewById(R.id.eightButton);
        Button nineButton = findViewById(R.id.nineButton);
        Button zeroButton = findViewById(R.id.zeroButton);
        Button dotButton = findViewById(R.id.dot);


        Button multiplicationButton = findViewById(R.id.multiplicationButton);
        Button divisionButton = findViewById(R.id.divisionButton);
        Button summationButton = findViewById(R.id.summationButton);
        Button subtractionButton = findViewById(R.id.subtractionButton);
        Button deleteButton = findViewById(R.id.deleteButton);
        Button equalsButton = findViewById(R.id.equalsButton);


        operandsEditText = findViewById(R.id.operandsArea);
        resultAreaTextView = findViewById(R.id.resultArea);


        oneButton.setOnClickListener(numbersOnClickListener);
        twoButton.setOnClickListener(numbersOnClickListener);
        threeButton.setOnClickListener(numbersOnClickListener);
        fourButton.setOnClickListener(numbersOnClickListener);
        fiveButton.setOnClickListener(numbersOnClickListener);
        sixButton.setOnClickListener(numbersOnClickListener);
        sevenButton.setOnClickListener(numbersOnClickListener);
        eightButton.setOnClickListener(numbersOnClickListener);
        nineButton.setOnClickListener(numbersOnClickListener);
        dotButton.setOnClickListener(numbersOnClickListener);
        zeroButton.setOnClickListener(numbersOnClickListener);
        deleteButton.setOnClickListener(deleteListener);
        deleteButton.setOnLongClickListener(gg);


        subtractionButton.setOnClickListener(operationsListener);
        multiplicationButton.setOnClickListener(operationsListener);
        summationButton.setOnClickListener(operationsListener);
        divisionButton.setOnClickListener(operationsListener);

        equalsButton.setOnClickListener(equalsListener);




    }

    View.OnClickListener numbersOnClickListener =   new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button b = (Button) view;
            String pressedNumberOrDot = b.getText().toString();
            boolean thereIs_a_dot = false;
            if (operationArea.length() == 0) {
                operationArea += pressedNumberOrDot;
                operandsEditText.setText(operationArea);
            } else {
                operationArea = operandsEditText.getText().toString();
                numbersArray = operationArea.split("[×]|[-]|[÷]|[+]");
                int indexOfLastNumberInTheNumbersArray = numbersArray.length - 1;
                String temp[] = operationArea.split("[1]|[2]|[3]|[4]|[5]|[6]|[7]|[8]|[9]|[0]");
                int indexOfLastNumberInTheTempArray = numbersArray.length - 1;

                if (operationArea.charAt(operationArea.length() - 1) == '1' |
                        operationArea.charAt(operationArea.length() - 1) == '2' |
                        operationArea.charAt(operationArea.length() - 1) == '3' |
                        operationArea.charAt(operationArea.length() - 1) == '4' |
                        operationArea.charAt(operationArea.length() - 1) == '5' |
                        operationArea.charAt(operationArea.length() - 1) == '6' |
                        operationArea.charAt(operationArea.length() - 1) == '7' |
                        operationArea.charAt(operationArea.length() - 1) == '8' |
                        operationArea.charAt(operationArea.length() - 1) == '9' |
                        operationArea.charAt(operationArea.length() - 1) == '0') {
                    for (int i = 0; i < numbersArray[indexOfLastNumberInTheNumbersArray].length(); i++) {
                        if (numbersArray[indexOfLastNumberInTheNumbersArray].charAt(i) == '.')
                            thereIs_a_dot = true;
                    }
                } else {

                    for (int i = 0; i < temp[indexOfLastNumberInTheTempArray].length(); i++) {
                        if (temp[indexOfLastNumberInTheTempArray].charAt(i) == '.')
                            thereIs_a_dot = true;
                    }
                }


                if (thereIs_a_dot && pressedNumberOrDot.equals(".")) {

                } else {
                    operationArea += pressedNumberOrDot;
                    operandsEditText.setText(operationArea);
                }


            }


        }


    };


    View.OnClickListener deleteListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String temp = deleteLastIndexOfString(operandsEditText.getText().toString());
            operationArea = temp;
            operandsEditText.setText(operationArea);


        }
    };

    private String deleteLastIndexOfString(String s) {
        String temp = "";
        for (int i = 0; i < s.length() - 1; i++) {
            if (i == 0) temp = String.valueOf(s.charAt(i));
            else
                temp += s.charAt(i);
        }
        return temp;

    }

    View.OnClickListener operationsListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button b = (Button) view;
            String operationPressed = b.getText().toString();


            if (operationArea.length() == 0) {
                if (operationPressed.equals("-")) {
                    operationArea = "-";
                    operandsEditText.setText(operationArea);
                } else {/*do nothing and keep it empty*/
                    ;
                }
            } else {

                switch (operationPressed) {

                    case "+":
                        if (operationArea.charAt(operationArea.length() - 1) == '-' && operationArea.length() == 1) {

                        } else if (String.valueOf(operationArea.charAt(operationArea.length() - 1)).equals(".")
                                || String.valueOf(operationArea.charAt(operationArea.length() - 1)).equals("+")) {
                            //do nothing
                        } else if (String.valueOf(operationArea.charAt(operationArea.length() - 1)).equals("-") ||
                                String.valueOf(operationArea.charAt(operationArea.length() - 1)).equals("×")
                                || String.valueOf(operationArea.charAt(operationArea.length() - 1)).equals("÷")) {
                            String temp = operationArea.substring(0, operationArea.length() - 1);
                            operationArea = temp;
                            operationArea += "+";
                            operandsEditText.setText(operationArea);
                        } else {
                            operationArea += "+";
                            operandsEditText.setText(operationArea);
                        }


                        break;

                    case "-":
                        if (operationArea.charAt(operationArea.length() - 1) == '-' && operationArea.length() == 1) {

                        } else if (operationArea.charAt(operationArea.length() - 1) == '.'
                                || operationArea.charAt(operationArea.length() - 1) == '-') {
                            //do nothing
                        } else if (String.valueOf(operationArea.charAt(operationArea.length() - 1)).equals(".")
                                || String.valueOf(operationArea.charAt(operationArea.length() - 1)).equals("-")) {
                            //do nothing
                        } else if (String.valueOf(operationArea.charAt(operationArea.length() - 1)).equals("+") ||
                                String.valueOf(operationArea.charAt(operationArea.length() - 1)).equals("×")
                                || String.valueOf(operationArea.charAt(operationArea.length() - 1)).equals("÷")) {
                            String temp = operationArea.substring(0, operationArea.length() - 1);
                            operationArea = temp;
                            operationArea += "-";
                            operandsEditText.setText(operationArea);
                        } else {
                            operationArea += "-";
                            operandsEditText.setText(operationArea);
                        }

                        break;

                    case "×":
                        if (operationArea.charAt(operationArea.length() - 1) == '-' && operationArea.length() == 1) {

                        } else if (String.valueOf(operationArea.charAt(operationArea.length() - 1)).equals(".")
                                || String.valueOf(operationArea.charAt(operationArea.length() - 1)).equals("×")) {
                            //do nothing
                        } else if (String.valueOf(operationArea.charAt(operationArea.length() - 1)).equals("-") ||
                                String.valueOf(operationArea.charAt(operationArea.length() - 1)).equals("+")
                                || String.valueOf(operationArea.charAt(operationArea.length() - 1)).equals("÷")) {
                            String temp = operationArea.substring(0, operationArea.length() - 1);
                            operationArea = temp;
                            operationArea += "×";
                            operandsEditText.setText(operationArea);
                        } else {
                            operationArea += "×";
                            operandsEditText.setText(operationArea);
                        }

                        break;

                    case "÷":
                        if (operationArea.charAt(operationArea.length() - 1) == '-' && operationArea.length() == 1) {

                        } else if (operationArea.charAt(operationArea.length() - 1) == '.'
                                || operationArea.charAt(operationArea.length() - 1) == '÷') {
                            //do nothing
                        } else if (String.valueOf(operationArea.charAt(operationArea.length() - 1)).equals(".")
                                || String.valueOf(operationArea.charAt(operationArea.length() - 1)).equals("÷")) {
                            //do nothing
                        } else if (String.valueOf(operationArea.charAt(operationArea.length() - 1)).equals("-") ||
                                String.valueOf(operationArea.charAt(operationArea.length() - 1)).equals("×")
                                || String.valueOf(operationArea.charAt(operationArea.length() - 1)).equals("+")) {
                            String temp = operationArea.substring(0, operationArea.length() - 1);
                            operationArea = temp;
                            operationArea += "÷";
                            operandsEditText.setText(operationArea);
                        } else {
                            operationArea += "÷";
                            operandsEditText.setText(operationArea);
                        }

                        break;


                }
            }
        }

    };
    View.OnLongClickListener gg= new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
            operationArea="";operandsEditText.setText(operationArea);
            result="";resultAreaTextView.setText(result);
            return false;
        }
    };

    View.OnClickListener equalsListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (operationArea.equals("")) {
                //do nothing
            } else {
                if ((operationArea.charAt(operationArea.length() - 1) == '+' |
                        operationArea.charAt(operationArea.length() - 1) == '-' |
                        operationArea.charAt(operationArea.length() - 1) == '×' |
                        operationArea.charAt(operationArea.length() - 1) == '÷')) {


                    flag = true;
                    resultAreaTextView.setText(String.valueOf(eval(operationArea.substring(0, operationArea.length() - 1))));


                } else {
                    flag = true;
                    resultAreaTextView.setText(String.valueOf(eval(operationArea)));
                }

            }
        }
    };




    public double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (; ; ) {
                    if (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (; ; ) {
                    if (eat('×')) x *= parseFactor(); // multiplication
                    else if (eat('÷')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if (Character.isDigit(ch) || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }
}
