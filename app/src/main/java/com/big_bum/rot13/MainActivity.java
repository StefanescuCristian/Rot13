package com.big_bum.rot13;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.androidquery.AQuery;


public class MainActivity extends Activity {
	
private AQuery aq;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), history.class);
                startActivityForResult(myIntent, 0);
            }
        });
        
        aq = new AQuery(this);
        aq.id(R.id.buttonCod).clicked(this, "clickButton");
        
    }
	
	
	public void clickButton(View button){
		String text = getText();
		String temp = Rot13(text);
		TextView output = (TextView)findViewById(R.id.insert_Text);
        output.setText(temp);
	}

	
	public String Rot13(String text) {
		String s = getText();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(baos));
	        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if       (c >= 'a' && c <= 'm') c += 13;
            else if  (c >= 'A' && c <= 'M') c += 13;
            else if  (c >= 'n' && c <= 'z') c -= 13;
            else if  (c >= 'N' && c <= 'Z') c -= 13;
            System.out.print(c);
	        }
	    String ret = baos.toString();
	    return ret;		
	}


	public String getText(){
    	String r = aq.id(R.id.insert_Text).getText().toString();
    	return r;
}
	
}
