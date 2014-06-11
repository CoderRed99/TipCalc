/*
 * Copyright (C) 2013 The Android Open Source Project 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */
package com.df.tipcalc;

import java.text.NumberFormat;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/** Calculates a percentage of a user-input-value
 *  and provides a total of the two. */

public class TipCalcActivity extends Activity {
	public EditText etMealAmt;
	public EditText etCustomTipAmt;
	public TextView tvTipAmtLabel;
	public TextView tvTotalAmtLabel;
	public View vCurrentTipAmt;
	private double tipPercent = 15;
	private double mealAmount = 0;
	// private double customTipAmt = 0;
	NumberFormat nm = NumberFormat.getCurrencyInstance();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tip_calc);
		
		// find the elements
		etMealAmt = (EditText) findViewById(R.id.etMealAmt);
	    etCustomTipAmt = (EditText) findViewById(R.id.etCustomTipAmt);
	    tvTipAmtLabel = (TextView) findViewById(R.id.tvTipAmtLabel);
	    tvTotalAmtLabel = (TextView) findViewById(R.id.tvTotalAmtLabel);
	    
	    etMealAmt.addTextChangedListener(new TextWatcher() 
	    {
	        @Override
	        public void afterTextChanged(Editable s) 
	        {
	            // Fires right after the text has changed
	        	// String etDisplay = s.getText().toString();
	            // double mealAmount = Double.parseDouble(etDisplay);
	            
	        	// tvDisplay.setText(s.toString());
	        	String strmealAmount = etMealAmt.getText().toString();
	        	mealAmount = Double.parseDouble(strmealAmount);
	        	if (vCurrentTipAmt != null)
	        		{
	        			onDetermineTip(vCurrentTipAmt);
	        		}
	        	// double mealAmount = Double.parseDouble(((EditText) s).getText().toString());
	        }
	        
	        @Override
	        public void onTextChanged(CharSequence s, int start, int before, int count) 
	        {
	            // Fires right as the text is being changed (even supplies the range of text)
	        }

	        @Override
	        public void beforeTextChanged(CharSequence s, int start, int count,
	                int after) 
	        {
	            // Fires right before text is changing
	        }
	    });
	    // public void setOnEditorActionListener (TextView.OnEditorActionListener l)
	    Toast.makeText(this, "afterTextChanged-meal amt", Toast.LENGTH_SHORT).show();
	
    etCustomTipAmt.addTextChangedListener(new TextWatcher() 
    {
        @Override
        public void afterTextChanged(Editable s) 
        {
            // Fires right after the text has changed
        	// String etDisplay = s.getText().toString();
            // double mealAmount = Double.parseDouble(etDisplay);
            
        	// tvDisplay.setText(s.toString());
        	String strTipAmt = etCustomTipAmt.getText().toString();
        	tipPercent = Double.parseDouble(strTipAmt);
    		
    		calculateAmounts(tipPercent);
        }
        
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) 
        {
            // Fires right as the text is being changed (even supplies the range of text)
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                int after) 
        {
            // Fires right before text is changing
        }
    });
    // public void setOnEditorActionListener (TextView.OnEditorActionListener l)
    Toast.makeText(this, "afterTextChanged-tip", Toast.LENGTH_SHORT).show();
}
	
	// Will fire when any one of the calculate percentage buttons is clicked
	public void onDetermineTip(View v) 
	{
		// etCustomTipAmt = null;
		vCurrentTipAmt = v;
		//TODO perform calculations depending on which button is clicked

		// Get the meal amount entered  THIS MAY BE HANDLED BY CHANGE TEXT LISTENER
		// double mealAmount = Double.parseDouble(etMealAmt.getText().toString());

		// Gets the tag (percent amount) associated with the view that was clicked
		tipPercent = Double.parseDouble((String) v.getTag());
		
		Toast.makeText(this, "onDetermineTip", Toast.LENGTH_SHORT).show();
		
		calculateAmounts(tipPercent);
	}
	
	public void onCustomTip(View v) 
	{		
		// Get the meal amount entered  THIS MAY BE HANDLED BY CHANGE TEXT LISTENER
		// double mealAmount = Double.parseDouble(etMealAmt.getText().toString());
		// double customTipAmt = Double.parseDouble(etCustomTipAmt.getText().toString());

		tipPercent = Double.parseDouble(etCustomTipAmt.getText().toString());

		Toast.makeText(this, "onCustomTip", Toast.LENGTH_SHORT).show();
		
		calculateAmounts(tipPercent);
	}
	
	public void calculateAmounts(double tipPercent) 
	{
		//TODO perform calculations depending on which button is clicked
		// tvTipAmtLabel.setText("");

		double tipAmt = mealAmount * (tipPercent / 100);
		tvTipAmtLabel.setText(nm.format(tipAmt));
		
		double totalAmt = mealAmount + tipAmt;
		tvTotalAmtLabel.setText(nm.format(totalAmt));
		
		
		Toast.makeText(this, "calculateAmounts", Toast.LENGTH_SHORT).show();
	}
}
