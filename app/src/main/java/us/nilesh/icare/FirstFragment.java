package us.nilesh.icare;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import Adapter.MyAdapter;
import Model.ListItem;

public class FirstFragment extends Fragment {
    private RecyclerView recyclerview;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;
    View view;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        view= inflater.inflate(R.layout.fragment_first, container, false);
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerView1);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /**inflating layout**/
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        listItems=new ArrayList<>();
        for (int i=0;i<10;i++){
            ListItem item=new ListItem("Item"+(i+1),"Description");
            listItems.add(item);
        }
        adapter=new MyAdapter(this.getActivity(),listItems);
        recyclerview.setAdapter(adapter);

        FloatingActionButton fab = view.findViewById(R.id.floatingActionButtonContact);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                askPassword();
            }
        });

        /**code for testing sending location**/
//        view.findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                askPassword();
//            }
//        });

        FloatingActionButton fab_setting = view.findViewById(R.id.floatingActionButtonSetting);
        fab_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                askPassword();
            }
        });

        FloatingActionButton fab_exit = view.findViewById(R.id.floatingActionButtonExit);
        fab_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onExit();
            }
        });

        statusCheck();

        /**code for enabling bluetooth service.**/
        final TextView textViewBlu=view.findViewById(R.id.textViewBluetooth);
        final CardView cardViewBlu=view.findViewById(R.id.cardViewBluetooth);
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth
        } else if (mBluetoothAdapter.isEnabled()) {
            // Bluetooth is not enabled :)
            textViewBlu.setText("Enabled");
            cardViewBlu.setCardBackgroundColor(Color.rgb(0,255,106));
            Toast.makeText(this.getActivity(),"Bluetooth: On",Toast.LENGTH_SHORT).show();
        } else {
            mBluetoothAdapter.enable();
            textViewBlu.setText("Disabled");
            cardViewBlu.setCardBackgroundColor(Color.rgb(90,90,90));
            Toast.makeText(this.getActivity(),"Bluetooth is turned on successfully!",Toast.LENGTH_LONG).show();
        }
    }

    /**code for password.**/
    public void askPassword(){
        AlertDialog.Builder alert = new AlertDialog.Builder(FirstFragment.this.getActivity());
        final EditText edittext = new EditText(FirstFragment.this.getActivity());
        alert.setMessage("Enter Your password:");
        alert.setTitle("Password");

        alert.setView(edittext);

        alert.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //What ever you want to do with the value
//                        Editable YouEditTextValue = edittext.getText();
                //OR
                String paswd="1234";
                String YouEditTextValue = edittext.getText().toString();
                if (YouEditTextValue.equals(paswd)){
                    NavHostFragment.findNavController(FirstFragment.this)
                            .navigate(R.id.action_FirstFragment_to_SettingFragment);
                }else {
                    Toast.makeText(FirstFragment.this.getActivity(),"Incorrect Password!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // what ever you want to do with No option.
                Toast.makeText(FirstFragment.this.getActivity(),"You have't entered Password",Toast.LENGTH_LONG).show();
            }
        });
        alert.show();
    }

    /**code for checking status of gps service.**/
    public void statusCheck() {
        final LocationManager manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        final CardView cardViewLoc=view.findViewById(R.id.cardViewLocation);
        final TextView textViewLoc=view.findViewById(R.id.textViewLocation);

        if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            textViewLoc.setText("Enabled");
            cardViewLoc.setCardBackgroundColor(Color.rgb(0,255,106));
            Toast.makeText(this.getActivity(),"Location: On",Toast.LENGTH_SHORT).show();
        }else {
            textViewLoc.setText("Disabled");
            cardViewLoc.setCardBackgroundColor(Color.rgb(90,90,90));
            buildAlertMessageNoGps();
            Toast.makeText(this.getActivity(),"Location: Off",Toast.LENGTH_SHORT).show();
        }
    }

    /**code for enabling gps service.**/
    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    public void onExit(){
        final AlertDialog.Builder exitMsg = new AlertDialog.Builder(this.getActivity());
        exitMsg.setMessage("Are you sure, you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        getActivity().finish();
                        System.exit(0);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = exitMsg.create();
        alert.show();
    }

}