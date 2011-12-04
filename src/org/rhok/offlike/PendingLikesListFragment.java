package org.rhok.offlike;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.TextView;

public class PendingLikesListFragment extends ListFragment {
	
	private OfflikeFragmentActivity activity;
	private WebView webview;
	
	public PendingLikesListFragment() {
		
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		
		super.onSaveInstanceState(outState);
	}

	public PendingLikesListFragment(OfflikeFragmentActivity activity,WebView webview) {
		this.setRetainInstance(true);
		this.activity=activity;
		this.webview=webview;

		
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Cursor pending_likes_c=this.getPendingLikes();
		
		if (pending_likes_c.getCount()>0) {
			int url_id=pending_likes_c.getColumnIndex(PendingLikesSQLHelper.URL);
			pending_likes_c.moveToLast();
			this.webview.loadUrl(pending_likes_c.getString(url_id));
		}
		else
			this.webview.loadUrl("http://no-content.net/");
		
		try {
		    String[] columns = new String[] { PendingLikesSQLHelper.TITLE,PendingLikesSQLHelper.URL};
		    int[] to = new int[] { R.id.title, R.id.url };	
		     
	        SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(activity, R.layout.pending_likes, getPendingLikes(), columns, to);
			
			this.setListAdapter(mAdapter);
		
			this.getListView().setCacheColorHint(0);
		} catch (Exception e) {
			OfflikeAppHelper.goHome(this.getActivity());
		}
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		webview.loadUrl(""+((TextView)(v.findViewById(R.id.url))).getText());
		Log.i("offclick",""+((TextView)(v.findViewById(R.id.url))).getText());
	}

	 public Cursor getPendingLikes() {
		 PendingLikesSQLHelper pending_likes_sql=new PendingLikesSQLHelper(this.getActivity());
		 SQLiteDatabase db = pending_likes_sql.getReadableDatabase();
		 Cursor cursor = db.query(PendingLikesSQLHelper.TABLE, null, null, null, null, null, null);
			
		 this.getActivity().startManagingCursor(cursor);
	
		return cursor;
	 }
}
