package com.demo.javaenhance.day3;

import java.lang.reflect.Method;

public interface Advice {
	void beforeMethod(Method method);
	void afterMethod(Method method);
}
