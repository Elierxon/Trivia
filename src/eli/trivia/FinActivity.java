package eli.trivia;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinActivity extends Activity {
	
	public static final String PREFS_NAME = "MyPrefsFiletrivia";
	
	TextView ppunt;
	TextView ppuntmax;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fin);
        
        Typeface tf1= Typeface.createFromAsset(getAssets(), "GoodDog.otf");
        TextView mTextView3 =(TextView) findViewById(R.id.TextView01);
        mTextView3.setTypeface(tf1);        
        
        Typeface tf= Typeface.createFromAsset(getAssets(), "Lobster_1.3.otf");
        TextView mTextView =(TextView) findViewById(R.id.TextView02);
        mTextView.setTypeface(tf);
        TextView mTextView2 =(TextView) findViewById(R.id.TextView04);
        mTextView2.setTypeface(tf);
        
        
        Bundle bundle = this.getIntent().getExtras();
        int puntuacionactual = bundle.getInt("puntuacion");
        
        ppunt = (TextView) findViewById(R.id.TextView03);
        ppuntmax = (TextView) findViewById(R.id.TextView05);
        
        //guardar puntuacion
        SharedPreferences sharedPref = getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);       
        int oldHighScore = sharedPref.getInt("saved_high_score", 0);       
        if(puntuacionactual>oldHighScore){
	        SharedPreferences.Editor editor = sharedPref.edit();
	        editor.putInt("saved_high_score", puntuacionactual);
	        editor.commit();
        }
        
        ppunt.setText(puntuacionactual + "puntos");
        ppuntmax.setText(oldHighScore + "puntos");
        
        
        final Button replay = (Button) findViewById(R.id.Button01);
        replay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), PreguntaActivity.class);
                startActivityForResult(myIntent, 1);
                //final TextView txv = (TextView) findViewById(R.id.tv3);
                //txv.setText("cambiano");
            }

        });
        
        final Button salir = (Button) findViewById(R.id.Button02);
        salir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), MainActivity.class);
                startActivityForResult(myIntent, 1);
                //final TextView txv = (TextView) findViewById(R.id.tv3);
                //txv.setText("cambiano");
            }

        });

        
	}
	
	/*public boolean onTouchEvent(MotionEvent event) {
		Intent myIntent = new Intent(view.getContext(), PreguntaActivity.class);
        startActivityForResult(myIntent, 1);
        return super.onTouchEvent(event);
    }*/
	

}
