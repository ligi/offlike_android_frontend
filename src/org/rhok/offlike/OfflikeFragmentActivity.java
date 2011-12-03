package org.rhok.offlike;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.Menu;
import android.support.v4.view.MenuItem;
import android.util.Log;


public class OfflikeFragmentActivity extends FragmentActivity {

	public final static int REQUEST_ID_SCAN=0;

	private PendingLikesSQLHelper pending_likes_sql;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		pending_likes_sql=new PendingLikesSQLHelper(this);
	}
	
	public void addPendingLike(String title,String url) {
	    SQLiteDatabase db = pending_likes_sql.getWritableDatabase();
	    ContentValues values = new ContentValues();
	    values.put(PendingLikesSQLHelper.TIME, System.currentTimeMillis());
	    values.put(PendingLikesSQLHelper.TITLE, title);
	    values.put(PendingLikesSQLHelper.URL, url);
	    db.insert(PendingLikesSQLHelper.TABLE, null, values);
	 }
	
	public void delPendingByURL(String url) {
	    SQLiteDatabase db = pending_likes_sql.getReadableDatabase();
	    db.delete(PendingLikesSQLHelper.TABLE, "url=?", new String[] { url });
	}

	 @Override
	 public boolean onOptionsItemSelected(MenuItem item) {
		 switch (item.getItemId()) {
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
	         
		case R.id.menu_fake:
			Intent i=new Intent(this,FakeTrigger.class);
			this.startActivity(i);
			break;
			
		case R.id.menu_settings:
			Intent settings_intent=new Intent(this,OfflikePreferencesActivity.class);
			this.startActivity(settings_intent);
			break;
		 }	
	  return super.onOptionsItemSelected(item);
	 }

	 @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.getMenuInflater().inflate(R.menu.menu_omnipresent, menu);
		return super.onCreateOptionsMenu(menu);
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
	
	public boolean isGPSwaitEnabled() {
		return PreferenceManager.getDefaultSharedPreferences(this.getBaseContext()).getBoolean(OfflikePreferencesActivity.KEY_GPS_WAIT,false);
	}
}
