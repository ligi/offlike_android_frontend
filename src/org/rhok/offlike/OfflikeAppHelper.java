package org.rhok.offlike;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.provider.Settings;

/**
 * Helper functions used in Offlike
 * 
 * @author ligi
 *
 */
public class OfflikeAppHelper {
	
	public static void turnGPSOn(Activity act){
	    String provider = Settings.Secure.getString(act.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

	    if(!provider.contains("gps")){ //if gps is disabled
	        final Intent poke = new Intent();
	        poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider"); 
	        poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
	        poke.setData(Uri.parse("3")); 
	        act.sendBroadcast(poke);
	    }
	}

	 public static boolean isIntentAvailable(Context context, String action) {
	     final PackageManager packageManager = context.getPackageManager();
	     final Intent intent = new Intent(action);
	     List<ResolveInfo> list =
	             packageManager.queryIntentActivities(intent,
	                     PackageManager.MATCH_DEFAULT_ONLY);
	     return list.size() > 0;
	 }
	 
	 public static boolean isBarCodeAppInstalled(Context ctx) {
		 return OfflikeAppHelper.isIntentAvailable(ctx, "com.google.zxing.client.android.SCAN");
	 }
	 
	public static void goHome(Context ctx) {
		 Intent intent = new Intent(ctx, OfflikeActivity.class);
		 intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		 ctx.startActivity(intent);
	}
}
