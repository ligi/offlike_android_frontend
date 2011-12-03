package org.rhok.offlike;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class FakeTrigger extends OfflikeFragmentActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	LinearLayout lin=new LinearLayout(this);
        lin.setOrientation(LinearLayout.VERTICAL);
        
        for ( int i=0;i<5;i++) {
        	Button btn=new Button(this);
        	btn.setText("campaigna " + i);
        	btn.setTag(i);
        	btn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent intent= new Intent();
					Activity a=FakeTrigger.this;
					intent.setAction(Intent.ACTION_VIEW);
					intent.setData(Uri.parse("http://offlike.herokuapp.com/like/id"+(Integer)v.getTag() + "?campaign_name=testfoobar"+(Integer)v.getTag()));
					a.startActivity(intent);
					if ((Integer)v.getTag()==4)
						del_pending();
				}
        		
        	});
        	lin.addView(btn);
        }
        this.setContentView(lin);
    }
}