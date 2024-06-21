package br.com.renan.verificadornumerosprimos.view;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import br.com.renan.verificadornumerosprimos.R;
import br.com.renan.verificadornumerosprimos.controller.PrimeNumberController;
import br.com.renan.verificadornumerosprimos.model.ResponseFormat;

public class MainActivity extends AppCompatActivity {

    private PrimeNumberController primeController;

    private EditText main_input;
    private TextView response_number;
    private TextView response_description;
    private TextView response_divisor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        primeController = new PrimeNumberController();

        main_input = findViewById(R.id.etValorInput);
        response_number = findViewById(R.id.tvResultNumber);
        response_description = findViewById(R.id.tvResultDecription);
        response_divisor = findViewById(R.id.tvDivisor);

        Button verify_button = findViewById(R.id.btnVerificar);
        verify_button.setOnClickListener(OP -> verify());
    }

    private void verify() {
        try {
            int value = Integer.parseInt(main_input.getText().toString());
            int divisor = primeController.isPrime(value);
            ResponseFormat format = (divisor == -1) ? new ValidPrimeMessage() : new InvalidPrimeMessage();
            buildResponse(format, value, divisor);
        } catch (NumberFormatException e) {
            response_description.setText(getString(R.string.erro_input));
            response_number.setText("");
            response_divisor.setText("");
        }
    }

    private void buildResponse(ResponseFormat format, int value, int divisor) {
        response_number.setTextColor(format.getColor());
        response_description.setText(format.getDescription());
        response_number.setText(String.valueOf(value));
        response_divisor.setText(format.getDivisorMessage(divisor));
    }

    // FORMATOS DE MENSAGEM

    private class ValidPrimeMessage implements ResponseFormat {

        @Override
        public int getColor() {
            return Color.GREEN;
        }

        @Override
        public String getDescription() {
            return getString(R.string.resposta_positiva);
        }

        @Override
        public String getDivisorMessage(int divisor) {
            return "";
        }
    }

    private class InvalidPrimeMessage implements ResponseFormat {

        @Override
        public int getColor() {
            return Color.RED;
        }

        @Override
        public String getDescription() {
            return getString(R.string.resposta_negativa);
        }

        @Override
        public String getDivisorMessage(int divisor) {
            return getString(R.string.divisor) + " " + divisor;
        }
    }
}


