package com.octo.rest.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class MainModule implements EntryPoint {
	public void onModuleLoad() {
		ServiceTester.MAX_CALLS = 200;
		final ServiceTester cxfTester = new ServiceTester("CXF stats", "http://poc-cxf.appspot.com/someService");
		RootPanel.get().add(cxfTester);
		final ServiceTester jerseyTester = new ServiceTester("Jersey stats", "http://poc-jersey.appspot.com/someService");
		RootPanel.get().add(jerseyTester);
		final ServiceTester resteasyTester = new ServiceTester("RESTEasy stats", "http://poc-resteasy.appspot.com/someService");
		RootPanel.get().add(resteasyTester);
//		cxfTester.addEndingListener(new EndingListener() {
//			public void onEnd() {
				jerseyTester.start();
//			}
//		});
//		jerseyTester.addEndingListener(new EndingListener() {
//			public void onEnd() {
				resteasyTester.start();
//			}
//		});
		cxfTester.start();
	}
}