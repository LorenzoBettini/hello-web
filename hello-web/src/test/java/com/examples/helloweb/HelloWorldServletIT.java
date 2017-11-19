package com.examples.helloweb;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;

public class HelloWorldServletIT {
	private static final String HTTP_LOCALHOST_8080_HELLO_WEB = "http://localhost:8080/hello-web";
	private WebDriver driver;

	@BeforeClass
	public static void setupClass() {
		ChromeDriverManager.getInstance().setup();
	}

	@Before
	public void setUp() {
		driver = new ChromeDriver();
	}

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
