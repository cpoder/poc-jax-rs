package com.octo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class BeanRepo {
	private List<SomeBean> someBeans = new ArrayList<SomeBean>();

	public BeanRepo() {
		for (int i = 0; i < 10; i++) {
			SomeBean someBean = new SomeBean();
			someBean.setSomeString("hello" + i);
			someBean.setSomeInteger(i);
			for (int j = 0; j < 10; j++) {
				OtherBean otherBean = new OtherBean();
				otherBean.setOtherInteger(j);
				otherBean.setOtherString("coucou" + j);
				someBean.getOtherBeans().add(otherBean);
			}
			someBeans.add(someBean);
		}
	}
	
	public List<SomeBean> getAll() { return someBeans; }
	public SomeBean get(int index) { return someBeans.get(index); }
	public void save(SomeBean someBean) {
		if (someBean.getSomeInteger() != null) {
			someBeans.set(someBean.getSomeInteger(), someBean);
		} else {
			someBean.setSomeInteger(someBeans.size());
			someBeans.add(someBean);
		}
	}
}
