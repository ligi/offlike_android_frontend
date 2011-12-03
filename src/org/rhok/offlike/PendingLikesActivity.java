package org.rhok.offlike;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.webkit.WebView;

public class PendingLikesActivity extends OfflikeFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_and_webview);
        final WebView wv=(WebView)this.findViewById(R.id.webview);
        wv.loadUrl("http://offlike.herokuapp.com/like/asdfads?campaign_name=asdfa");
        wv.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				PendingLikesActivity.this.delPendingByURL(((WebView)v).getUrl());
				PendingLikesActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.list_fragment, new PendingLikesListFragment(PendingLikesActivity.this.getPendingLikes(),PendingLikesActivity.this,wv)).commit();
				return false;
			}
        	
        });
        this.getSupportFragmentManager().beginTransaction().add(R.id.list_fragment, new PendingLikesListFragment(this.getPendingLikes(),this,wv)).commit();
    }
}
