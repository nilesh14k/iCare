package us.nilesh.icare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ContactActivity extends AppCompatActivity {
    private TextView name,description;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        getSupportActionBar().setTitle("Contacts");
        extras=getIntent().getExtras();
        name=(EditText)findViewById(R.id.detail1);
        description=(EditText)findViewById(R.id.detail2);
        if (extras!=null){
            name.setText(extras.getString("name"));
            description.setText(extras.getString("description"));
        }
        final FloatingActionButton fabBack=(FloatingActionButton) findViewById(R.id.floatingActionButtonBack);
        fabBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent mainMenu=new Intent(ContactActivity.this, MainActivity.class);
        startActivity(mainMenu);
        finish();
    }
}