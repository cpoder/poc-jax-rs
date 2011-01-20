package com.octo.rest.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ServiceTester extends Composite {
	public static int MAX_CALLS = 100;
	int calls = 0;
	private class Stats {
		long[] times = new long[MAX_CALLS];
		int totalError = 0;
		CaptionPanel decoratorPanel = new CaptionPanel();
		VerticalPanel panel = new VerticalPanel();
		{
			decoratorPanel.setContentWidget(panel);
		}
	}
	private Stats stats;
	
	public static interface EndingListener {
		void onEnd();
	}
	
	private List<EndingListener> endingListeners = new ArrayList<ServiceTester.EndingListener>();
	
	private String url;
	
	public ServiceTester(String title, String url) {
		stats = new Stats();
		stats.decoratorPanel.setCaptionText(title);
		this.url = url;
		this.initWidget(stats.decoratorPanel);
	}
	
	public void addEndingListener(EndingListener listener) {
		endingListeners.add(listener);
	}
	
	public void start() {
		calls = 0;
		stats.totalError = 0;
		callService();
	}

	private void callService() {
		final long before = new Date().getTime();
		RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET,
				url);
		try {
			requestBuilder.sendRequest(null, new RequestCallback() {

				@Override
				public void onResponseReceived(Request request,
						Response response) {
					stats.times[calls] = (new Date().getTime()) - before;
					incCalls();
				}

				@Override
				public void onError(Request request, Throwable exception) {
					stats.totalError++;
					incCalls();
					exception.printStackTrace();
				}
			});
		} catch (RequestException e) {
			stats.totalError++;
			incCalls();
			e.printStackTrace();
		}
	}

	private void showAverageTime() {
		long totalTime = 0;
		for (int i=0;i<MAX_CALLS;i++) {
			totalTime += stats.times[i];
		}
		stats.panel.add(new Label("Average time : " + (totalTime / MAX_CALLS) + "ms"));
		stats.panel.add(new Label("Total errors : " + stats.totalError));
		for (EndingListener endingListener : endingListeners)
			endingListener.onEnd();
	}

	private void incCalls() {
		calls++;
		if (calls == MAX_CALLS)
			showAverageTime();
		else
			callService();
	}
}
