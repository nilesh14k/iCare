package us.nilesh.icare;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.fragment.NavHostFragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static android.content.Context.LOCATION_SERVICE;

public class MapsFragment extends Fragment {
    private GoogleMap mMap;
    private LocationListener locationListener;
    private LocationManager locationManager;
    private final long MIN_TIME = 1000;
    private final long MIN_DIST =5;
    private LatLng latLng;
    public int counter;
    public Button button;
    public TextView textView2;
    public CardView cardViewCancel;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;

            // Add a marker in Sydney and move the camera
//        LatLng CU = new LatLng(30.771763, 76.576339);
//        mMap.addMarker(new MarkerOptions().position(CU).title("Marker in CU"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(CU));

            locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(final Location location) {
                    final MediaPlayer ring= MediaPlayer.create(getActivity().getApplicationContext(),R.raw.women_english);
//                    ring.setVolume(20,20);
                    ring.start();
//                    button.findViewById(R.id.cancelBtn).setOnClickListener(new View.OnClickListener() {
                    cardViewCancel.findViewById(R.id.cancel_View).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            counter=16;
                            Log.d("locationChanged", "yes");
                            ring.stop();
                            android.os.Process.killProcess(android.os.Process.myPid());
                            System.exit(1);
                        }
                    });
                    CountDownTimer Timer = new CountDownTimer(15000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            if (counter == 16) {
                                textView2.setText("0");
                            } else {
                                textView2.setText(String.valueOf(counter));
                                counter++;
                            }
                        }

                        @Override
                        public void onFinish() {
                            if (counter==15) {
                                try {
                                    ring.stop();
                                    latLng = new LatLng(location.getLatitude(), location.getLongitude());
                                    mMap.addMarker(new MarkerOptions().position(latLng).title("My Position"));
                                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                                    float zoomLevel = 16f;
                                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel));
//                                    String phoneNumber =getString(ThirdFragment.CHOOSE_CONTACTS);
                                    String phoneNumber ="8427619131";
//                                    String phoneNumber2 ="";
                                    String myLatitude = String.valueOf(location.getLatitude());
                                    String myLongitude = String.valueOf(location.getLongitude());

                                    String message = "http://maps.google.com/maps?q="+myLatitude+","+myLongitude;
                                    SmsManager smsManager = SmsManager.getDefault();
                                    smsManager.sendTextMessage(phoneNumber, null, message,null,null);
//                                    smsManager.sendTextMessage(ip, null, message,null,null);
                                    android.os.Process.killProcess(android.os.Process.myPid());
                                    System.exit(1);
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            else
                            {
                                textView2.setText("0");
                            }
                        }
                    }.start();
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            };
            locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
            try{
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DIST, locationListener);
            }
            catch (SecurityException e){
                e.printStackTrace();
            }
        }
    };

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_maps, container, false);
        textView2=(TextView) view.findViewById(R.id.countText);
//        button = (Button) view.findViewById(R.id.cancelBtn);
        cardViewCancel=(CardView) view.findViewById(R.id.cancel_View);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        WindowManager.LayoutParams layout = getActivity().getWindow().getAttributes();
        layout.screenBrightness = 1F;
        getActivity().getWindow().setAttributes(layout);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(callback);
    }
}