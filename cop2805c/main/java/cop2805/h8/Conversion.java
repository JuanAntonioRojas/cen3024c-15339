package cop2805.h8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Conversion extends AppCompatActivity {

    private TextView xchg_rate;
    private EditText qttyUsd;
    private EditText qttyYen;
    private Button fromUsd;
    private Button toUsd;
    private Button returnButton; //  Will take us back to Main

    private ImageView blankImage;
    private TextView selCurrency;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversion);

        //  Capture what was sent from Main
        Intent intent = getIntent();

        //  Updating the blank flag with the selected image sent.
        int imageResId = intent.getIntExtra("Selected_Image", 0);
        blankImage = findViewById(R.id.blank_flag);
        blankImage.setImageResource(imageResId);

        //  Updating the Currency type from the radio button selection.
        String curr = intent.getStringExtra("Selected_Currency");
        selCurrency = findViewById(R.id.currency);
        selCurrency.setText(curr);



        //  Get the rate of exchange from the MainActivity
        String exchangeRate = intent.getStringExtra("Put_Xchg_Rate");
        xchg_rate = findViewById(R.id.xchg_rate);  //  Set the new rate on the xchg_rate TextView
        xchg_rate.setText(exchangeRate);


        //  Vars where to place the results from the calculation methods
        fromUsd = findViewById(R.id.from_usd);
        toUsd = findViewById(R.id.to_usd);


        returnButton = findViewById(R.id.button);  //  Initialize the go-back button


        //  Now we use the xchg_rate to multiply it by the quantity of dollars or yens
        //    when we click on either button "fromUsd" or "fromYen"

        //  Convert from USD to YEN
        fromUsd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //  The value from qttyUsd, defaulted to 1 at create
                    double usdAmount = Double.parseDouble(qttyUsd.getText().toString());

                    //  Get the exchange rate from xchgRate: convert the string passed (intent) into a double
                    double exchangeRate = Double.parseDouble(xchg_rate.getText().toString());

                    //  Calculate the result for USD to YEN
                    double yenAmount = usdAmount * exchangeRate;

                    //  Convert the double back to a string and set this result in qttyYen
                    qttyYen.setText(String.format("%.2f", yenAmount));
                } catch (NumberFormatException e) {
                    // Show an error message if input is not valid
                    Toast.makeText(Conversion.this, "Wrong type of input", Toast.LENGTH_SHORT).show();
                }
            }
        });


        //  Convert from YEN to USD
        toUsd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //  The value from qttyYen, defaulted to ""
                    double yenAmount = Double.parseDouble(qttyUsd.getText().toString());

                    //  Get the exchange rate from xchgRate: convert the string passed (intent) into a double
                    double exchangeRate = Double.parseDouble(xchg_rate.getText().toString());

                    //  Calculate the result for YEN to USD
                    double usdAmount = yenAmount * exchangeRate;

                    //  Convert the double back to a string and set this result in qttyUsd
                    qttyUsd.setText(String.format("%.2f", usdAmount));
                } catch (NumberFormatException e) {
                    // Show an error message if input is not valid
                    Toast.makeText(Conversion.this, "Please enter a valid input", Toast.LENGTH_SHORT).show();
                }
            }
        });



        // Set OnClickListener for the return button
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent(Conversion.this, MainActivity.class);
                startActivity(returnIntent);
                finish(); // Optionally call finish() if you want to remove the Conversion activity from the back stack
            }
        });
    }
}
