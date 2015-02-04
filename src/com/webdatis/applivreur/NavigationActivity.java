package com.webdatis.applivreur;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class NavigationActivity extends TabActivity {
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.navigation);
	

		// create the TabHost that will contain the Tabs
		TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);

		TabSpec tab1 = tabHost.newTabSpec("commande");
		TabSpec tab2 = tabHost.newTabSpec("compte");
		TabSpec tab3 = tabHost.newTabSpec("support");

		// Set the Tab name and Activity
		// that will be opened when particular Tab will be selected
		tab1.setIndicator("", getApplicationContext().getResources().getDrawable(R.drawable.ico_cart_active));
		tab1.setContent(new Intent(this, Commandes.class));

		tab2.setIndicator("", getApplicationContext().getResources().getDrawable(R.drawable.ico_user_active));
		tab2.setContent(new Intent(this, Compte.class));

		tab3.setIndicator("", getApplicationContext().getResources().getDrawable(R.drawable.ico_support_active));
		tab3.setContent(new Intent(this, Support.class));

		/** Add the tabs to the TabHost to display. */
		tabHost.addTab(tab1);
		tabHost.addTab(tab2);
		tabHost.addTab(tab3);

	}
}