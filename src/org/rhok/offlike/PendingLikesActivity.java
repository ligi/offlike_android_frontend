package org.rhok.offlike;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.webkit.WebView;

public class PendingLikesActivity extends OfflikeFragmentActivity {

	private WebView wv;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // guide the user to the market if he has no barcode scanner
        if (!OfflikeAppHelper.isBarCodeAppInstalled(this))
        	new AlertDialog.Builder(this).setMessage("Barcode Scanner not installed - APP makes way more sense with it - do you want to install?")
        	.setPositiveButton(android.R.string.ok, new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					Uri uri = Uri.parse("market://search?q=pname:com.google.zxing.client.android");
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
				}
        	})
        	.setNegativeButton(android.R.string.cancel, new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
        		
        	}).setTitle("Hint").show();
        
        setContentView(R.layout.list_and_webview);
        wv=(WebView)this.findViewById(R.id.webview);
        wv.loadUrl("http://offlike.herokuapp.com/like/asdfads?campaign_name=asdfa");
        wv.setOnTouchListener(new OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			PendingLikesActivity.this.delPendingByURL(((WebView)v).getUrl());
			PendingLikesActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.list_fragment, new PendingLikesListFragment(PendingLikesActivity.this,wv)).commit();
			return false;
		}
        	
        });
		
        this.getSupportFragmentManager().beginTransaction().add(R.id.list_fragment, new PendingLikesListFragment(this,wv)).commit();
		
    }
    
}
