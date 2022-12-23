package us.nilesh.icare;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ThirdFragment extends Fragment {

    private static final int PICK_CONTACT = 0;

    protected static final int CHOOSE_CONTACTS = 0;
    View view;
    public ImageButton addNewContact;
    private TextView textViewOne;
    private TextView textViewTwo;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_third, container, false);

        addNewContact = (ImageButton) view.findViewById(R.id.addNewContact);
        addNewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent contactPickerIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(contactPickerIntent,1);
            }
        });
        textViewOne=(TextView)view.findViewById(R.id.textView2);
        textViewTwo=(TextView)view.findViewById(R.id.textView3);

//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
//        startActivityForResult(intent, PICK_CONTACT);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /*@Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

//        switch (reqCode) {
//            case (PICK_CONTACT) :
//                if (resultCode == Activity.RESULT_OK) {
//                    Uri contactData = data.getData();
//                    Cursor c =  getActivity().getContentResolver().query(contactData, null, null, null, null);
//                    if (c.moveToFirst()) {
//                        String name = c.getString(c.getColumnIndexOrThrow(Contacts.People.NAME));
//                        String number = c.getString(c.getColumnIndexOrThrow(Contacts.People.NUMBER));
//                        BreakIterator perrsonname=null;
//                        perrsonname.setText(name);
//                        Toast.makeText(getActivity(),  name + " has number " + number, Toast.LENGTH_LONG).show();
//                    }
//                }
//                break;
//        }

//        switch (reqCode) {
//            case (PICK_CONTACT) :
//                if (resultCode == Activity.RESULT_OK) {
//                    Uri contactData = data.getData();
//                    Cursor c =  getActivity().getContentResolver().query(contactData, null, null, null, null);
//                    if (c.moveToFirst()) {
//                        String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
//                        String number=c.getString(c.getColumnIndex(ContactsContract.Contacts.NAME_RAW_CONTACT_ID));
//                        // TODO Whatever you want to do with the selected contact name.
//                        textViewOne.setText(name);
//                        textViewTwo.setText(number);
////                        String id = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
////                        String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
////                        Log.i("Names", name);
////                        if (Integer.parseInt(c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0)
////                        {
////                            // Query phone here. Covered next
////                            Cursor phones = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id,null, null);
////                            while (phones.moveToNext()) {
////                                String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//////                                textViewTwo.setText(phoneNumber);
////                                Log.i("Number", phoneNumber);
////                            }
////                            phones.close();
////                        }
//                    }
//                }
//                break;
//        }


    }*/
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        switch (requestCode){
//            case 1 :
//                if (resultCode == Activity.RESULT_OK) {
//                    Uri contactData = data.getData();
//                    ContentResolver cr = getActivity().getContentResolver();
//                    Cursor cur = cr.query(contactData, null, null, null, null);
//                    if (cur.getCount() > 0) {// thats mean some resutl has been found
//                        if(cur.moveToNext()) {
//                            String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
//                            String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
//                            Log.e("Names", name);
//                            if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0)
//                            {
//                                // Query phone here. Covered next
//                                Cursor phones = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id,null, null);
//                                while (phones.moveToNext()) {
//                                    String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//                                    Log.e("Number", phoneNumber);
//                                }
//                                phones.close();
//                            }
//
//                        }
//                    }
//                    cur.close();
//                }
//                break;
//        }
//
//    }

}
