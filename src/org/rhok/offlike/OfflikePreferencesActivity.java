package org.rhok.offlike;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;

public class OfflikePreferencesActivity extends PreferenceActivity {

	public final static String KEY_GPS_WAIT="GPSWAIT";

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPreferenceScreen(createPreferenceHierarchy());
    }
	
    private PreferenceScreen createPreferenceHierarchy() {
        // Root
        PreferenceScreen root = getPreferenceManager().createPreferenceScreen(this);
        
        root.setPersistent(true);
        
        /* Gameplay section */
        PreferenceCategory inlinePrefCat = new PreferenceCategory(this);
        inlinePrefCat.setTitle("location");
        root.addPreference(inlinePrefCat);
        
        CheckBoxPreference nextScreenCheckBoxPref = new CheckBoxPreference(this);
        nextScreenCheckBoxPref.setKey(KEY_GPS_WAIT);
        nextScreenCheckBoxPref.setTitle("wait for GPS");
        nextScreenCheckBoxPref.setSummary("wait for GPS on start");
        inlinePrefCat.addPreference(nextScreenCheckBoxPref);
        return root;	    
    }
}
