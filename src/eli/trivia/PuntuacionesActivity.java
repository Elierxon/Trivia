package eli.trivia;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class PuntuacionesActivity extends Activity{
	
	public static final String PREFS_NAME = "MyPrefsFiletrivia";
	
	TextView ppuntmax;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puntuaciones);
        
        Typeface tf= Typeface.createFromAsset(getAssets(), "Lobster_1.3.otf");
        TextView mTextView =(TextView) findViewById(R.id.textView1);
          mTextView.setTypeface(tf);
        
        
        
        ppuntmax = (TextView) findViewById(R.id.textView2);
        
        
        SharedPreferences sharedPref = getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);       
        int oldHighScore = sharedPref.getInt("saved_high_score", 0);       
        ppuntmax.setText(oldHighScore + "puntos");
        
        
	}

}
