package org.rhok.offlike;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.MenuItem;


public class OfflikeFragmentActivity extends FragmentActivity {

	PendingLikesSQLHelper pending_likes_sql;

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
	
	public void del_pending() {
	    SQLiteDatabase db = pending_likes_sql.getReadableDatabase();
	    db.delete(PendingLikesSQLHelper.TABLE, null, null);
	}
	
	 public Cursor getPendingLikes() {
	
		 SQLiteDatabase db = pending_likes_sql.getReadableDatabase();
		 Cursor cursor = db.query(PendingLikesSQLHelper.TABLE, null, null, null, null, null, null);
			
		 startManagingCursor(cursor);
		
		return cursor;
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
		 }	
	  return super.onOptionsItemSelected(item);
	 }

}
