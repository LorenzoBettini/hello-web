package com.examples.helloweb;

import org.junit.BeforeClass;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.FirefoxDriverManager;

public class HelloWorldServletFirefoxIT extends AbstractHelloWorldServletIT {
	@BeforeClass
	public static void setupClass() {
		FirefoxDriverManager.getInstance().setup();
	}

	@Override
	protected FirefoxDriver createWebDriver() {
		return new FirefoxDriver();
	}
}
