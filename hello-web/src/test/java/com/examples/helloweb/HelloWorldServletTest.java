package com.examples.helloweb;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class HelloWorldServletTest {
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private RequestDispatcher requestDispatcher;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void doGet() throws Exception {
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);

		when(response.getWriter()).thenReturn(printWriter);
		when(request.getContextPath()).thenReturn("/");

		new HelloWorldServlet().doGet(request, response);

		assertEquals("Served at: /", stringWriter.toString());
	}

	@Test
	public void doPostWithoutName() throws Exception {
		when(request.getRequestDispatcher("response.jsp")).thenReturn(requestDispatcher);

		new HelloWorldServlet().doPost(request, response);

		verify(request).setAttribute("user", "World");
		verify(requestDispatcher).forward(request, response);
	}

	@Test
	public void doPostWithName() throws Exception {
		when(request.getParameter("name")).thenReturn("Dolly");
		when(request.getRequestDispatcher("response.jsp")).thenReturn(requestDispatcher);

		new HelloWorldServlet().doPost(request, response);

		verify(request).setAttribute("user", "Dolly");
		verify(requestDispatcher).forward(request, response);
	}
}
