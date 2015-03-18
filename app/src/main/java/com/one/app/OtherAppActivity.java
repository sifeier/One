package com.one.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.net.Uri;

import com.one.R;

/**
 * Created by sifeier on 15/1/30.
 */
public class OtherAppActivity extends Activity {

    private static final String TAG = "OtherAppActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_other_app);
    }

    public void onShareImageClick(View v) {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/");
        Intent chooser = Intent.createChooser(share, "想发就发");
        if(share.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        }
    }

    public void onShareTxtClick(View v) {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        Intent chooser = Intent.createChooser(share, "想发就发");
        if(share.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        }
    }

    public void onContactClick(View v) {
        Intent contact = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
        contact.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        startActivityForResult(contact, 1);
    }

}
