package eli.trivia;

import eli.trivia.PreguntaActivity;

import eli.trivia.*;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Typeface tf= Typeface.createFromAsset(getAssets(), "SEASRN__.ttf");
        TextView mTextView =(TextView) findViewById(R.id.TextView01);
          mTextView.setTypeface(tf);
        
        
        final Button juga = (Button) findViewById(R.id.button2m);
        juga.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), PreguntaActivity.class);
                startActivityForResult(myIntent, 1);
                //final TextView txv = (TextView) findViewById(R.id.tv3);
                //txv.setText("cambiano");
            }

        });
        
        final Button howbutton = (Button) findViewById(R.id.button1m);
        howbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), HowtoActivity.class);
                startActivityForResult(myIntent, 1);
            }

        });
        
        final Button puntbutton = (Button) findViewById(R.id.button3m);
        puntbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), PuntuacionesActivity.class);
                startActivityForResult(myIntent, 1);
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
