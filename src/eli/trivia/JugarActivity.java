package eli.trivia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class JugarActivity extends Activity{
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jugar);
        
        final Button pregun = (Button) findViewById(R.id.button1j);
        pregun.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), PreguntaActivity.class);
                startActivityForResult(myIntent, 1);
                //final TextView txv = (TextView) findViewById(R.id.tv3);
                //txv.setText("cambiano");
            }

        });
	}

}
