package id.co.latihan.soalnomorsatu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txt_output;
    Button btn_reserve, btn_undoredo;
    EditText et_inputan;
    List<String> temp = new ArrayList<>();
    int count;
    int pivot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        temp.add("");
        count = 0;

        txt_output = findViewById(R.id.txtoutput);
        btn_reserve = findViewById(R.id.btn_reserve);
        btn_undoredo = findViewById(R.id.btn_undoredo);
        et_inputan = findViewById(R.id.et_inputan);

        btn_reserve.setOnClickListener(this);
        btn_undoredo.setOnClickListener(this);

    }
    private static final long DEFAULT_QUALIFICATION_SPAN = 200;
    private long doubleClickQualificationSpanInMillis;
    private long timestampLastClick;

    public void DoubleClickListener() {
        doubleClickQualificationSpanInMillis = DEFAULT_QUALIFICATION_SPAN;
        timestampLastClick = 0;
    }

    public void DoubleClickListener(long doubleClickQualificationSpanInMillis) {
        this.doubleClickQualificationSpanInMillis = doubleClickQualificationSpanInMillis;
        timestampLastClick = 0;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_reserve)
        {
            if (!et_inputan.getText().toString().isEmpty()) {
                StringBuffer c = new StringBuffer(et_inputan.getText().toString());
                txt_output.setText(c.reverse());
                Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_LONG).show();
            } else {
                et_inputan.setError("Enter NAME");
            }
        }

        if(view.getId() == R.id.btn_undoredo)
        {

            if((SystemClock.elapsedRealtime() - timestampLastClick) < doubleClickQualificationSpanInMillis) {
                if(pivot<count)
                {
                    et_inputan.setText(temp.get(pivot+1));
                    pivot++;
                }
                else {
                    et_inputan.setText(temp.get(count));
                    pivot++;
                }
            }
            else
            {
                temp.add(et_inputan.getText().toString());
                count++;
                int tempCount = count-1;
                if(tempCount < 0)
                {
                    et_inputan.setText(temp.get(0));
                    pivot = 0;
                }
                else
                {
                    et_inputan.setText(temp.get(tempCount));
                    pivot = tempCount;
                }
            }
        }
    }
}