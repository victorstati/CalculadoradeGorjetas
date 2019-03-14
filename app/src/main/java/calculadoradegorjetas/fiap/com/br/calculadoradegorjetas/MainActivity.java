package calculadoradegorjetas.fiap.com.br.calculadoradegorjetas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private NumberFormat percentFormat = NumberFormat.getPercentInstance();

    private double billAmount = 0.0;

    private double percent = 0.15;

    private EditText amountEditText;

    private TextView amountTextView;

    private TextView percentTextView;

    private SeekBar percentSeekBar;

    private TextView tipTextView;

    private TextView totalTextView;

    private class SeekBarChangeListener implements SeekBar.OnSeekBarChangeListener{
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
            percent = progress/100d;
            double tip = billAmount * percent;
            double total = billAmount + tip;

            percentTextView.setText(percentFormat.format(percent));

            tipTextView.setText(currencyFormat.format(tip));
            totalTextView.setText(currencyFormat.format(total));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }

    private TextWatcher amountEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //converter para texto
            try {
                billAmount = Double.parseDouble(charSequence.toString()) / 100;

                double tip = billAmount * percent;

                double total = billAmount + tip;

                //formatar texto
                amountTextView.setText(currencyFormat.format(billAmount));

                tipTextView.setText(currencyFormat.format(tip));

                totalTextView.setText(currencyFormat.format(total));

            }catch(NumberFormatException e){
                amountTextView.setText(currencyFormat.format(0));
                tipTextView.setText(currencyFormat.format(0));
                totalTextView.setText(currencyFormat.format(0));
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //inflando a view
        setContentView(R.layout.activity_main);

        amountEditText = findViewById(R.id.amountEditText);
        amountTextView = findViewById(R.id.amountTextView);
        percentTextView = findViewById(R.id.percentTextView);
        percentSeekBar = findViewById(R.id.percentSeekBar);
        tipTextView = findViewById(R.id.tipTextView);
        totalTextView = findViewById(R.id.totalTextView);

        amountEditText.addTextChangedListener(amountEditTextWatcher);
        percentSeekBar.setOnSeekBarChangeListener(new SeekBarChangeListener());
    }
}
