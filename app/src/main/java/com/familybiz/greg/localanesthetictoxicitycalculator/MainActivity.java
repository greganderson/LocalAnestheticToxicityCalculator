package com.familybiz.greg.localanesthetictoxicitycalculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.familybiz.greg.localanesthetictoxicitycalculator.formulas.Marcaine;

import java.util.ArrayList;


public class MainActivity extends Activity {

	private ArrayList<DrugRow> mDrugRows;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mDrugRows = new ArrayList<>();

		LinearLayout rootLayout = new LinearLayout(this);
		rootLayout.setOrientation(LinearLayout.VERTICAL);

		TextView titleView = new TextView(this);
		titleView.setText(getString(R.string.title_label));
		rootLayout.addView(titleView, new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT));


		// Input line

		LinearLayout inputLine = new LinearLayout(this);
		inputLine.setOrientation(LinearLayout.HORIZONTAL);
		rootLayout.addView(inputLine, new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT));

		EditText patientWeightInput = new EditText(this);
		patientWeightInput.setInputType(InputType.TYPE_CLASS_NUMBER);
		patientWeightInput.setHint(getString(R.string.patient_weight_hint));

		Button metricButton = new Button(this);
		metricButton.setText(getString(R.string.kilograms_text));

		inputLine.addView(patientWeightInput, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
		inputLine.addView(metricButton, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));

		// Amide Anesthetic
		// TODO: Is this supposed to have an 's'?
		LinearLayout amideLayout = new LinearLayout(this);
		amideLayout.setOrientation(GridLayout.VERTICAL);

		String[] amideList = {
		"Amide Anesthetic", "Max Dose", "Max Dose with Epi",
		"Marcaine", "360.00", "",
		"Xylocaine", "540.00", "840.00",
		"Carbocaine", "540.00", "840.00",
		"Citanest", "960.00", "",
		"Naropin", "360.00", ""};

		for (int i = 0; i < amideList.length; i+=3) {
			DrugRow row = new DrugRow(this, amideList[i], new Marcaine());
			mDrugRows.add(row);
			amideLayout.addView(row, new LinearLayout.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT));
		}

		rootLayout.addView(amideLayout, new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT));

		// Ester Anesthetics

		setContentView(rootLayout);


		TextWatcher watcher = new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				try {
					double weight = Double.parseDouble(s.toString());
					updateMaxDosages(weight);
				}
				catch (NumberFormatException e) {
					// TODO: Implement
					Log.i("Exception", "Number");
				}
				catch (NullPointerException e) {
					// TODO: Implement
					Log.i("Exception", "Null");
				}
			}

			@Override
			public void afterTextChanged(Editable s) { }
		};
		patientWeightInput.addTextChangedListener(watcher);
	}

	private void updateMaxDosages(double weight) {
		Log.i("Changed", "Called");
		for (DrugRow drug : mDrugRows)
			drug.computeMaxDose(weight);
	}
}
