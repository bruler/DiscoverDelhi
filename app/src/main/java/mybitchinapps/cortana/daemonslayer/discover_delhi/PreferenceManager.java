package mybitchinapps.cortana.daemonslayer.discover_delhi;


import android.content.Context;
import android.content.SharedPreferences;


public class PreferenceManager {
	SharedPreferences pref;
	SharedPreferences.Editor editor;
	Context context;


	int PRIVATE_MODE=0;

	private static final String PREF_NAME = "app-welcome";

	private static final String IS_FIRST_LAUNCH="IsFirstTimeLaunch";

	public PreferenceManager(Context context) {
		this.context = context;
		pref = this.context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = pref.edit();
	}

	public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_LAUNCH, isFirstTime);
        editor.commit();
    }
 
    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_LAUNCH, true);
    }
}