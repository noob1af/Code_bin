package com.app.simplecalculator;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "CalculatorActivity";

    private Calculate mCalculator;

    private EditText mOperandOneEditText;
    private EditText mOperandTwoEditText;

    private TextView mResultTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCalculator = new Calculate();
        mOperandOneEditText = (EditText) findViewById(R.id.edit_text_one);
        mOperandTwoEditText = (EditText) findViewById(R.id.edit_text_two);
        mResultTextView = (TextView) findViewById(R.id.result);
    }



    /**
     * OnClick method that is called when the add {@link Button} is pressed.
     */
    public void onAdd(View view) {
        compute(Calculate.Operator.ADD);
    }

    /**
     * OnClick method that is called when the substract {@link Button} is pressed.
     */
    public void onSub(View view) {
        compute(Calculate.Operator.SUB);
    }

    /**
     * OnClick method that is called when the divide {@link Button} is pressed.
     */
    @SuppressLint("SetTextI18n")
    public void onDiv(View view) {
        try {
            compute(Calculate.Operator.DIV);
        } catch (IllegalArgumentException iae) {
            Log.e(TAG, "IllegalArgumentException", iae);
            mResultTextView.setText(R.string.showError);
        }
    }

    /**
     * OnClick method that is called when the multiply {@link Button} is pressed.
     */
    public void onMul(View view) {
        compute(Calculate.Operator.MUL);
    }


    @SuppressLint("SetTextI18n")
    private void compute(Calculate.Operator operator) {
        double operandOne;
        double operandTwo;
        try {
            operandOne = getOperand(mOperandOneEditText);
            operandTwo = getOperand(mOperandTwoEditText);
        } catch (NumberFormatException nfe) {
            Log.e(TAG, "NumberFormatException", nfe);
            mResultTextView.setText(R.string.showError);
            return;
        }

        String result;
        switch (operator) {
            case ADD:
                result = String.valueOf(mCalculator.add(operandOne, operandTwo));
                break;
            case SUB:
                result = String.valueOf(mCalculator.sub(operandOne, operandTwo));
                break;
            case DIV:
                result = String.valueOf(mCalculator.div(operandOne, operandTwo));
                break;
            case MUL:
                result = String.valueOf(mCalculator.mul(operandOne, operandTwo));
                break;
            default:
                result = getString(R.string.showError);
                break;
        }
        mResultTextView.setText(result);
    }

    /**
     * @return the operand value which was entered in an {@link EditText} as a double
     */
    private static Double getOperand(EditText operandEditText) {
        String operandText = getOperandText(operandEditText);
        return Double.valueOf(operandText);
    }

    /**
     * @return the operand text which was entered in an {@link EditText}.
     */
    private static String getOperandText(EditText operandEditText) {
        return operandEditText.getText().toString();
    }



}
