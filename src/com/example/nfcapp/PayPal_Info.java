package com.example.nfcapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.TextView;

public class PayPal_Info extends Activity {

	private TextView Titulaire;
	private TextView CardNumber;
	private Boolean CardType;
	private TextView Month;
	private TextView Years;
	private TextView CVV2;
	private TextView Adress;
	private RadioButton Visa;
	private RadioButton Master;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pay_pal_info);
		Master = (RadioButton) findViewById(R.id.radMaster);
		Visa = (RadioButton) findViewById(R.id.radVisa);
		Titulaire = (TextView) findViewById(R.id.Titulaire);
		CardNumber = (TextView) findViewById(R.id.CardNumber);
		Month = (TextView) findViewById(R.id.Month);
		Years = (TextView) findViewById(R.id.Years);
		CVV2 = (TextView) findViewById(R.id.CVV2);
		Adress = (TextView) findViewById(R.id.Adress);
		if (Master.isSelected() == true){
			
		}
	}
}
