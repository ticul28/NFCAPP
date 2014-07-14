package com.example.nfcapp;

import java.nio.charset.Charset;

import com.v1tor.nuno.projectoiii.base.NFCActivity;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.nfc.tech.IsoDepTransceiver.OnMessageReceived;
import android.nfc.tech.NfcA;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;
import android.nfc.tech.*;


@TargetApi(Build.VERSION_CODES.GINGERBREAD_MR1) public class MainActivity extends NFCActivity{
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	public static final String MIME_TEXT_PLAIN = "text/plain";
    public static final String TAG = "NfcDemo";
    private TextView mTextView;
    private TextView tv;
    private NfcAdapter mNfcAdapter;
    private TextView tv2;
    private PendingIntent pIntent;
    private ProgressBar callProgress;
	 
	    @Override
		public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	       mTextView = (TextView) findViewById(R.id.textView_explanation);
	        tv = (TextView) findViewById(R.id.tv);
	        tv2 = (TextView) findViewById(R.id.iso);
	        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
	        pIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
	        
	        if (mNfcAdapter == null) {
	            // Stop here, we definitely need NFC
	            Toast.makeText(this, "Cet appareil ne supporte pas la tectnologie NFC.", Toast.LENGTH_LONG).show();
	            finish();
	            return;
	        }
	       
	        if (!mNfcAdapter.isEnabled()) {
	            mTextView.setText("NFC est désactivé.");
	        } else {
	            mTextView.setText("Nfc est activé");
	        } 
	       
	        handleIntent(getIntent());
	    }
	     
	    private void handleIntent(Intent intent) {
	        // TODO: handle Intent
	    }
	    public void onNewIntent(Intent intent) {
	    	 Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
	        //do something with tagFromIntent
	        NfcA nfca = NfcA.get(tagFromIntent);
	        IsoDep is = IsoDep.get(tagFromIntent);
	        try{
	            nfca.connect();
	            //Short a = nfca;
	            Short s = nfca.getSak();
	            byte[] a = nfca.getAtqa();
	            String atqa = new String(a, Charset.forName("US-ASCII"));
	            tv.setText("SAK = "+s+"\nATQA = "+atqa);
	            nfca.close();
	        }
	        catch(Exception e){
	            Log.e(TAG, "Error when reading tag");
	            tv.setText("Error");
	        }
	        try{
	        	String allo = null;
	            is.connect();
	            String s = IsoDep.class.getName();
	            
	            tv2.setText("TAG = "+s);
	            is.close();
	        }
	        catch(Exception e){
	            Log.e(TAG, "Error when reading tag");
	            //tv2.setText("Error");
	        }
	    }
	    
		@Override
		public void onTagDiscovered(Context context, Intent intent) {
			//Toast.makeText(getApplicationContext(), "Hello_World", Toast.LENGTH_LONG).show();
			Tag mytag = (Tag)intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
			Toast.makeText(getApplicationContext(), mytag.toString(), Toast.LENGTH_LONG).show();
			super.onTagDiscovered(context, intent);	
		}
		/** Called when the user clicks the Send button */
		public void sendMessage(View view) {
		    // Do something in response to button
			Intent i = new Intent(MainActivity.this, PayPal_Info.class);
			startActivity(i);
		}
}
/*	NfcAdapter nfcAdapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		//nfcAdapter.disableForegroundDispatch(this);
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public void onTagDiscovered(Context context, Intent intent) {
		//Toast.makeText(getApplicationContext(), "Hello_World", Toast.LENGTH_LONG).show();
		Tag mytag = (Tag)intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
		Toast.makeText(getApplicationContext(), mytag.toString(), Toast.LENGTH_LONG).show();
		super.onTagDiscovered(context, intent);	
	}
}*/