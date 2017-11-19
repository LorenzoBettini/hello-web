package com.examples.helloweb;

import org.junit.BeforeClass;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class HelloWorldServletChromeIT extends AbstractHelloWorldServletIT {
	@BeforeClass
	public static void setupClass() {
		ChromeDriverManager.getInstance().setup();
	}

	@Override
	protected ChromeDriver createWebDriver() {
		return new ChromeDriver();
	}
}
