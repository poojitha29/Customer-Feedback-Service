package edu.sjsu.cmpe.customerfeedback.ui.views;

import java.util.List;

import com.yammer.dropwizard.views.View;

public class ReportView extends View {
	final List<String> reportValues;
	final String popularProduct, popularValue, unpopularProduct, unpopularValue, freqRewiever; 
	public ReportView(List<String> reportValues) {
		super("report.mustache");
		this.reportValues = reportValues;
		this.popularProduct = reportValues.get(0);
		this.popularValue = reportValues.get(1);
		this.unpopularProduct = reportValues.get(2);
		this.unpopularValue = reportValues.get(3);
		this.freqRewiever = reportValues.get(4);
	}
	
	
	

}
