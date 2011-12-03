package org.rhok.offlike;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.ArrayAdapter;

public class PendingLikesListFragment extends ListFragment {

	
	private Cursor cursor;	
	private OfflikeFragmentActivity activity;
	
	public PendingLikesListFragment() {
		
	}
	

	public PendingLikesListFragment(Cursor c, OfflikeFragmentActivity activity) {
		cursor=c;
		this.activity=activity;
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		String[] menu_items=new String[] { "foo" , "bar"};
		
	    String[] columns = new String[] { PendingLikesSQLHelper.TITLE,PendingLikesSQLHelper.URL};
	    int[] to = new int[] { R.id.title, R.id.url };
	     
        SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(activity, R.layout.pending_likes, cursor, columns, to);
		
		this.setListAdapter(mAdapter);
		/*
		this.setListAdapter(new ArrayAdapter<String>(this.getActivity(),
				R.layout.list_item, menu_items));
		 */
		
		this.getListView().setCacheColorHint(0);
        
	}

}
