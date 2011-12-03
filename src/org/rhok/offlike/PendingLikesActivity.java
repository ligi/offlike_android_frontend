package org.rhok.offlike;

import android.os.Bundle;
import android.webkit.WebView;

public class PendingLikesActivity extends OfflikeFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_and_webview);
        WebView wv=(WebView)this.findViewById(R.id.webview);
        wv.loadUrl("http://offlike.herokuapp.com/like/asdfads?campaign_name=asdfa");

        this.getSupportFragmentManager().beginTransaction().add(R.id.list_fragment, new PendingLikesListFragment(this.getPendingLikes(),this,wv)).commit();
    }
}
