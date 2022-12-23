package us.nilesh.icare;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

public class SettingsFragment extends PreferenceFragmentCompat {
    public ListPreference lpBT;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
        loadSetting();
    }
    public void loadSetting(){
        SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(getContext());
        String signature =sp.getString("setting_name","");
        String theme=sp.getString("setting_theme","Light");
        String ringtone=sp.getString("setting_ring","Women-English");
        String pin=sp.getString("setting_pin","");
//        ................
        Toast.makeText(SettingsFragment.this.getActivity(),signature,Toast.LENGTH_SHORT).show();
        Toast.makeText(SettingsFragment.this.getActivity(),theme,Toast.LENGTH_SHORT).show();
        Toast.makeText(SettingsFragment.this.getActivity(),ringtone,Toast.LENGTH_SHORT).show();
        Toast.makeText(SettingsFragment.this.getActivity(),pin,Toast.LENGTH_SHORT).show();
        Log.i("name", signature);
        Log.i("name", theme);
        Log.i("name", ringtone);
        Log.i("name", pin);


        lpBT=(ListPreference)findPreference("bt_device");
    }
}