package org.rhok.offlike;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class OfflikeActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        
        TextView tv=new TextView(this);
        if (getIntent().getData()!=null)
        	tv.setText(getIntent().getData().toString());
        else
        	tv.setText("no URL");
        
        this.setContentView(tv);
        
    }
}
