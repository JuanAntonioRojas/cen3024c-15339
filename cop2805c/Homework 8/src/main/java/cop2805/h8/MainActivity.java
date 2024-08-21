package cop2805.h8;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView canadian;
    private RadioButton canada;
    private TextView xchg_cad;

    private ImageView japanese;
    private RadioButton japan;
    private TextView xchg_yen;

    private ImageView european;
    private RadioButton europe;
    private TextView xchg_euro;

    private int selectedImageResId; //  Variable to store the selected image resource ID
    private String selectCurrency;  //  Variable to store the selected currency text

    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        canadian = findViewById(R.id.canadian);
        canada = findViewById(R.id.canada);
        xchg_cad = findViewById(R.id.xchg_cad);

        japan = findViewById(R.id.japan);
        japanese = findViewById(R.id.japanese);
        xchg_yen = findViewById(R.id.xchg_yen);

        european = findViewById(R.id.european);
        europe = findViewById(R.id.europe);
        xchg_euro = findViewById(R.id.xchg_euro);

        btnNext = findViewById(R.id.btnNext);



        //  Canada
        canadian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCanada();
            }
        });
        canada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCanada();
            }
        });


        //  Japan
        japanese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectJapan();
            }
        });
        japan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectJapan();
            }
        });


        //  Europe
        european.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectEurope();
            }
        });
        europe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectEurope();
            }
        });



        //  Summarize & xfer the data to the conversion activity, when clicking "NEXT"
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String exchangeRate = xchg_euro.getText().toString();
                Intent intent = new Intent(MainActivity.this, Conversion.class);
                intent.putExtra("Put_Xchg_Rate", exchangeRate);
                intent.putExtra("Selected_Image", selectedImageResId);   // Pass the selected image resource ID
                intent.putExtra("Selected_Currency", selectCurrency); // Pass the selected currency text
                startActivity(intent);
            }
        });
    }


    //  Here we define all the activities that result from clicking a flag, or a radio button, event

    private void selectCanada() {
        canada.setChecked(true);                      //  Make sure the radio-button is checked
        xchg_cad.setTextColor(Color.RED);             //  Change xhg rate color to red
        xchg_cad.setTypeface(null, Typeface.BOLD); //  Make text bold
        selectedImageResId = R.drawable.cad;          //  Store the image resource ID
        selectCurrency = canada.getText().toString(); //  Store the selected currency text
    }

    private void selectJapan() {
        japan.setChecked(true);                      //  Make sure the radio-button is checked
        xchg_yen.setTextColor(Color.RED);            // Change xhg rate color to red
        xchg_yen.setTypeface(null, Typeface.BOLD); // Make text bold
        selectedImageResId = R.drawable.yen;         // Store the image resource ID
        selectCurrency = japan.getText().toString(); // Store the selected currency text
    }

    private void selectEurope() {
        europe.setChecked(true);                      //  Make sure the radio-button is checked
        xchg_euro.setTextColor(Color.RED);            // Change xhg rate color to red
        xchg_euro.setTypeface(null, Typeface.BOLD); // Make text bold
        selectedImageResId = R.drawable.euro;         // Store the image resource ID
        selectCurrency = europe.getText().toString(); // Store the selected currency text
    }
}
