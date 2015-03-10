package com.familybiz.greg.localanesthetictoxicitycalculator;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.familybiz.greg.localanesthetictoxicitycalculator.formulas.Formula;

/**
 * Created by Greg Anderson
 *
 * Represents an nx3 grid.
 */
public class DrugRow extends LinearLayout {

	public static final String KILOS = "kg";
	public static final String LBS = "lbs";

	private String mDrugName;
	private double mMaxDose;
	private double mMaxDoseWithEpi;
	private String mUnits;
	private Formula mFormula;

	private TextView mDrugNameView;
	private TextView mMaxDoseView;
	private TextView mUnitsView;
	private TextView mMaxDoseWithEpiView;
	private TextView mUnitsWithEpiView;

	public DrugRow(Context context, String drugName, Formula formula) {
		super(context);
		setOrientation(HORIZONTAL);

		mDrugName = drugName;
		mFormula = formula;
		mUnits = KILOS;

		mDrugNameView = new TextView(context);
		mMaxDoseView = new TextView(context);
		mUnitsView = new TextView(context);
		mMaxDoseWithEpiView = new TextView(context);
		mUnitsWithEpiView = new TextView(context);

		mDrugNameView.setText(mDrugName);
		mUnitsView.setText(mUnits);
		mUnitsWithEpiView.setText(mUnits);

		addView(mDrugNameView, new LayoutParams(
				0,
				ViewGroup.LayoutParams.WRAP_CONTENT,
				0));
		addView(mMaxDoseView, new LayoutParams(
				0,
				ViewGroup.LayoutParams.WRAP_CONTENT,
				0));
		addView(mUnitsView, new LayoutParams(
				0,
				ViewGroup.LayoutParams.WRAP_CONTENT,
				0));
		addView(mMaxDoseWithEpiView, new LayoutParams(
				0,
				ViewGroup.LayoutParams.WRAP_CONTENT,
				0));
		addView(mUnitsWithEpiView, new LayoutParams(
				0,
				ViewGroup.LayoutParams.WRAP_CONTENT,
				0));
	}

	public String getDrugName() {
		return mDrugName;
	}

	public void computeMaxDose(double weight) {
		// TODO: Find a better way to cut off to two decimals
		double maxDose = (double)((int)(mFormula.compute(weight) * 100)) / 100;
		mMaxDoseView.setText(maxDose + "");
		Log.i("Dose", "The dose is: " + maxDose);
	}

	public void toggleUnits() {
		mUnits = mUnits.equals(KILOS) ? LBS : KILOS;
		mUnitsView.setText(mUnits);
		mUnitsWithEpiView.setText(mUnits);
	}
}
