package eli.trivia;

import javax.xml.datatype.DatatypeConstants.Field;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class PreguntaActivity extends Activity {
	
	//dapregunta dp = new dapregunta();
	int xpreg[] = {R.array.Cuestion1,R.array.Cuestion2,R.array.Cuestion3,
				R.array.Cuestion4,R.array.Cuestion5,R.array.Cuestion6};
	int puntuacion = 0;
	int sinerrores = 0;
	int np = 0;//numero pregunta actual
	int nerr = 0;
	int totalpreguntas = 6;
	/*int[] cresp= {1,3,2,4,2,2};*///repuesta correcta
	TextView pregpan;
	TextView panel;
	ImageView marcador1,marcador2,marcador3;
	ImageView[] marcador = {marcador1,marcador2,marcador3};
	
	TextView ppunt;
	TextView masppunt;
	
	Button bpreg1;
    Button bpreg2;
    Button bpreg3;
    Button bpreg4;
    Button bcontinue;
	
	Resources res;
	TypedArray cuestion1;
	/*TypedArray cuestion2 = res.obtainTypedArray(R.array.Cuestion2);
	TypedArray cuestion3 = res.obtainTypedArray(R.array.Cuestion3);
	TypedArray cuestion4 = res.obtainTypedArray(R.array.Cuestion4);
	TypedArray cuestion5 = res.obtainTypedArray(R.array.Cuestion5);
	TypedArray cuestion6 = res.obtainTypedArray(R.array.Cuestion6);*/
	//String cues = cuestion.getString(0);
	//TypedArray[] cuestiones = {cuestion1,cuestion2,cuestion3,cuestion4,cuestion5,cuestion6};
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pregunta);
        
        PreguntasSQLiteHelper usdbh =
            new PreguntasSQLiteHelper(this, "DBUsuarios", null, 1);
 
        SQLiteDatabase db = usdbh.getWritableDatabase();
        if(db != null)
        {
            
                /*int codigo = i;
                String nombre = "Usuario" + i;
 
                //Insertamos los datos en la tabla Usuarios
                db.execSQL("INSERT INTO Usuarios (codigo, nombre) " +
                           "VALUES (" + codigo + ", '" + nombre +"')");*/          
 
            //Cerramos la base de datos
            db.close();
        }
        
        res = getResources();
        cuestion1 = res.obtainTypedArray(R.array.Cuestion1);
    	/*TypedArray cuestion2 = res.obtainTypedArray(R.array.Cuestion2);
    	TypedArray cuestion3 = res.obtainTypedArray(R.array.Cuestion3);
    	TypedArray cuestion4 = res.obtainTypedArray(R.array.Cuestion4);
    	TypedArray cuestion5 = res.obtainTypedArray(R.array.Cuestion5);
    	TypedArray cuestion6 = res.obtainTypedArray(R.array.Cuestion6);*/
        
        
        marcador[0] = (ImageView) findViewById(R.id.imageView1p);
        marcador[1] = (ImageView) findViewById(R.id.imageView2p);
        marcador[2] = (ImageView) findViewById(R.id.imageView3p);
        /*marcador[3] = (ImageView) findViewById(R.id.imageView4p);
        marcador[4] = (ImageView) findViewById(R.id.imageView5p);
        marcador[5] = (ImageView) findViewById(R.id.imageView6p);*/
        
        marcador[0].setImageResource(R.drawable.iconnoerror);
        marcador[1].setImageResource(R.drawable.iconnoerror);
        marcador[2].setImageResource(R.drawable.iconnoerror);
        /*marcador[3].setImageResource(R.drawable.ic_ans3);
        marcador[4].setImageResource(R.drawable.ic_ans3);
        marcador[5].setImageResource(R.drawable.ic_ans3);*/
        
        pregpan = (TextView) findViewById(R.id.textView1);
        pregpan.setText(cuestion1.getString(0));
        
        panel = (TextView) findViewById(R.id.textView2p);
        panel.setText("");
        
        ppunt = (TextView) findViewById(R.id.textView2);
        
        masppunt = (TextView) findViewById(R.id.textView3);
        masppunt.setText("");
        
        
        Typeface tf= Typeface.createFromAsset(getAssets(), "DIGITALDREAM.ttf");
        panel.setTypeface(tf);
        ppunt.setTypeface(tf);
        masppunt.setTypeface(tf);
        
        bpreg1 = (Button) findViewById(R.id.button1p);
        bpreg2 = (Button) findViewById(R.id.button2p);
        bpreg3 = (Button) findViewById(R.id.button3p);
        bpreg4 = (Button) findViewById(R.id.button4p);
        bpreg1.setText(cuestion1.getString(1));
        bpreg2.setText(cuestion1.getString(2));
        bpreg3.setText(cuestion1.getString(3));
        bpreg4.setText(cuestion1.getString(4));
        
        bcontinue = (Button) findViewById(R.id.button5p);
        
        
        
        bpreg1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	compruebarespuesta(1);
            	//cambiapregunta();
            }
        });
        bpreg2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	compruebarespuesta(2);
            	//cambiapregunta();
            }
        });
        bpreg3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	compruebarespuesta(3);
            	//cambiapregunta();
            }
        });
        bpreg4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	compruebarespuesta(4);
            	//cambiapregunta();
            }
        });
        
        
        bcontinue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	if(nerr>=3){
            		Bundle bundle = new Bundle();
            		bundle.putInt("puntuacion", puntuacion);
            		Intent myIntent = new Intent(view.getContext(), FinActivity.class);
            		myIntent.putExtras(bundle);
                    startActivityForResult(myIntent, 1);
            	}else{
            		cambiapregunta();
            	}
            }
        });
        
        panel.setVisibility(View.INVISIBLE);
        bcontinue.setVisibility(View.INVISIBLE);
                
    }
	
	private void compruebarespuesta(int nb){
		panel.setVisibility(View.VISIBLE);
        bcontinue.setVisibility(View.VISIBLE);
        masppunt.setVisibility(View.VISIBLE);
        bpreg1.setEnabled(false);
        bpreg2.setEnabled(false);
        bpreg3.setEnabled(false);
        bpreg4.setEnabled(false);
        
		if(nb==cuestion1.getInt(5,0)){
			sinerrores++;
			//marcador[np].setImageResource(R.drawable.ic_ans);
			panel.setText("Correcto");
			puntuacion += 1*sinerrores;
			masppunt.setText("Sumas " + 1*sinerrores + "puntos");
			ppunt.setText(puntuacion + "puntos");
		}else{
			marcador[nerr].setImageResource(R.drawable.iconfallo);
			nerr++;
			//marcador[np].setImageResource(R.drawable.ic_ans2);
			panel.setText("Error");
			sinerrores = 0;
			masppunt.setText("Sumas " + 1*sinerrores + "puntos");
		}
	}
	
	private void cambiapregunta(){
		np++;
		panel.setVisibility(View.INVISIBLE);
        bcontinue.setVisibility(View.INVISIBLE);
        masppunt.setVisibility(View.INVISIBLE);
        bpreg1.setEnabled(true);
        bpreg2.setEnabled(true);
        bpreg3.setEnabled(true);
        bpreg4.setEnabled(true);
		
		/*int array[] = {0,1,2,3,4,5,6,7,8,9,10};
		int value[];
		String fieldName = "Cuestion" + Integer.toString(array[np]);
		 java.lang.reflect.Field field = R.id.class.getField(fieldName);
		 value[np] = field.getInt(null);*/
		cuestion1 = res.obtainTypedArray(xpreg[np%totalpreguntas]);
        //cuestion1 = dp.damepregunta(np);
		pregpan.setText(cuestion1.getString(0));
        
        /*final Button bpreg1 = (Button) findViewById(R.id.button1p);
        final Button bpreg2 = (Button) findViewById(R.id.button2p);
        final Button bpreg3 = (Button) findViewById(R.id.button3p);
        final Button bpreg4 = (Button) findViewById(R.id.button4p);*/
        bpreg1.setText(cuestion1.getString(1));
        bpreg2.setText(cuestion1.getString(2));
        bpreg3.setText(cuestion1.getString(3));
        bpreg4.setText(cuestion1.getString(4));		
	}


}
