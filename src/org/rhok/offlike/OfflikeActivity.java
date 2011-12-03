package org.rhok.offlike;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.view.Menu;
import android.support.v4.view.MenuItem;
import android.widget.TextView;

public class OfflikeActivity extends OfflikeFragmentActivity implements LocationListener {
	
	public static Location last_location=null;
	private ProgressDialog pd;
	
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
        
        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000L, 5.0f, this);
		
		pd=ProgressDialog.show(this, "waiting","waiting for location",true);
		pd.setCancelable(true);
		final WaitForLocationAsync wa=new WaitForLocationAsync();
		
		pd.setOnCancelListener(new OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				dialog.dismiss();
				wa.cancel(true);
				startNext();
			}
			
		});
		
		wa.execute();
    }

    public void startNext() {
        Intent next=new Intent(this,PendingLikesActivity.class);
        this.startActivity(next);
    }
    
    class WaitForLocationAsync extends AsyncTask<Void,Void,Void> {
    	private boolean running=true;
    	
		@Override
		protected Void doInBackground(Void... params) {
			turnGPSOn();
			while ((running)&&(last_location==null))
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
			return null;
		}

		@Override
		protected void onCancelled() {
			running=false;
			super.onCancelled();
		}

		@Override
		protected void onPostExecute(Void result) {

			pd.dismiss();
			startNext();
			
			super.onPostExecute(result);
		}
    	
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

	@Override
	public void onLocationChanged(Location location) {
		if ((location.getAccuracy()>0.0)&&(location.getAccuracy()<50.0))
			last_location=location;
	}

	@Override
	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

	private void turnGPSOn(){
	    String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

	    if(!provider.contains("gps")){ //if gps is disabled
	        final Intent poke = new Intent();
	        poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider"); 
	        poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
	        poke.setData(Uri.parse("3")); 
	        sendBroadcast(poke);
	    }
	}

}
