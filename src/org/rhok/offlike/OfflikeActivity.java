package org.rhok.offlike;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.Menu;
import android.support.v4.view.MenuItem;
import android.widget.TextView;

public class OfflikeActivity extends OfflikeFragmentActivity {
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        
        TextView tv=new TextView(this);
        if (getIntent().getData()!=null) {
        	tv.setText(getIntent().getData().toString());
        	
        	this.addPendingLike(Uri.parse(getIntent().getData().toString()).getQueryParameter("campaign_name")
        			 , getIntent().getData().toString());
        }
        else {
        	tv.setText("no URL");
        }
       // this.setContentView(tv);
        Intent next=new Intent(this,PendingLikesActivity.class);
        this.startActivity(next);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.getMenuInflater().inflate(R.menu.default_mainview, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

			
		case R.id.menu_pending:
			Intent i_pend=new Intent(this,PendingLikesActivity.class);
			this.startActivity(i_pend);
			break;
			
        
		}
		return super.onOptionsItemSelected(item);
	}

}
