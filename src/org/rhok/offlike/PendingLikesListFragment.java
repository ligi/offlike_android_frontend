package org.rhok.offlike;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.TextView;

public class PendingLikesListFragment extends ListFragment {
	
	private Cursor cursor;	

	private OfflikeFragmentActivity activity;
	private WebView webview;
	
	public PendingLikesListFragment() {
	}
	
	public PendingLikesListFragment(Cursor c, OfflikeFragmentActivity activity,WebView webview) {
		cursor=c;
		this.activity=activity;
		this.webview=webview;

	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	    String[] columns = new String[] { PendingLikesSQLHelper.TITLE,PendingLikesSQLHelper.URL};
	    int[] to = new int[] { R.id.title, R.id.url };
	     
        SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(activity, R.layout.pending_likes, cursor, columns, to);
		
		this.setListAdapter(mAdapter);
	
		this.getListView().setCacheColorHint(0);
        
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		webview.loadUrl(""+((TextView)(v.findViewById(R.id.url))).getText());
		Log.i("offclick",""+((TextView)(v.findViewById(R.id.url))).getText());
	}

}
