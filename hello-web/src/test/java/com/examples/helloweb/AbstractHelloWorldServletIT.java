package com.examples.helloweb;

import static org.junit.Assert.assertEquals;

import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class AbstractHelloWorldServletIT {

	private static final String HTTP_LOCALHOST_8080_HELLO_WEB =
		System.getProperty("com.examples.helloweb.url", "http://localhost:8080/hello-web");
	private WebDriver driver;

	public AbstractHelloWorldServletIT() {
		super();
	}

	@BeforeClass
	public static void showURL() {
		Logger.
			getLogger(AbstractHelloWorldServletIT.class.toString()).
			info("Using URL: " + HTTP_LOCALHOST_8080_HELLO_WEB);
	}

	@Before
	public void setUp() {
		driver = createWebDriver();
	}

	protected abstract WebDriver createWebDriver();

	@After
	public void tearDown() {
		if (driver != null)
			driver.quit();
	}

	@Test
	public void sayHelloWithLink() throws Exception {
		driver.get(HTTP_LOCALHOST_8080_HELLO_WEB);
	
		driver.findElement(By.linkText("Hello")).click();
	
		assertEquals("Served at: /hello-web", driver.findElement(By.tagName("body")).getText());
	}

	@Test
	public void sayHelloWithUser() throws Exception {
		driver.get(HTTP_LOCALHOST_8080_HELLO_WEB);
	
		driver.findElement(By.id("say-hello-text-input")).sendKeys("Dolly");
		driver.findElement(By.id("say-hello-button")).click();
	
		assertEquals("Hello Page", driver.getTitle());
		assertEquals("Hello, Dolly!", driver.findElement(By.tagName("h2")).getText());
	}

	@Test
	public void sayHelloWithoutUser() throws Exception {
		driver.get(HTTP_LOCALHOST_8080_HELLO_WEB);
	
		driver.findElement(By.id("say-hello-button")).click();
	
		assertEquals("Hello Page", driver.getTitle());
		assertEquals("Hello, World!", driver.findElement(By.tagName("h2")).getText());
	}

}