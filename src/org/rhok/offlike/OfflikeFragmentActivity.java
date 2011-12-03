package org.rhok.offlike;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

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

}
