package org.rhok.offlike;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.Menu;
import android.support.v4.view.MenuItem;
import android.util.Log;
import android.widget.TextView;

public class OfflikeActivity extends OfflikeFragmentActivity {
	
	public final static int REQUEST_ID_SCAN=0;
	
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.getMenuInflater().inflate(R.menu.default_mainview, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_fake:
			Intent i=new Intent(this,FakeTrigger.class);
			this.startActivity(i);
			break;
			
		case R.id.menu_pending:
			Intent i_pend=new Intent(this,PendingLikesActivity.class);
			this.startActivity(i_pend);
			break;
			
        case android.R.id.home:
            // app icon in action bar clicked; go home
            Intent intent = new Intent(this, OfflikeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
            
        case R.id.menu_scan:
            Intent scan_intent = new Intent("com.google.zxing.client.android.SCAN");
            scan_intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(scan_intent, REQUEST_ID_SCAN);
            break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
	    if (requestCode == REQUEST_ID_SCAN) {
	        if (resultCode == RESULT_OK) {
	            String contents = intent.getStringExtra("SCAN_RESULT");
	            //String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
	            Log.i("","contents" + contents);
	            Intent i=new Intent(this,OfflikeActivity.class);
	            i.setData(Uri.parse(contents));
	            this.startActivity(i);
	            // Handle successful scan
	        } else if (resultCode == RESULT_CANCELED) {
	            // Handle cancel
	        }
	    }
	}

}
