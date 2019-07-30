package com.smarthome.utility;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.apache.http.Consts;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.io.IdentityInputStream;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpProcessorBuilder;
import org.apache.http.protocol.RequestConnControl;
import org.apache.http.protocol.RequestContent;
import org.apache.http.protocol.RequestTargetHost;
import org.apache.http.protocol.RequestUserAgent;

public class HttpClient {

	private String response;

	public String GETRequest(String url) throws ClientProtocolException, IOException, HttpException {

		HttpProcessor processor = HttpProcessorBuilder.create().addAll(new RequestContent(), new RequestTargetHost(),
				new RequestConnControl(), new RequestUserAgent("HTTP/1.1")).build();

		// Target host
		InetSocketAddress target = new InetSocketAddress("192.168.43.221", 80);

		// Create custom connection that exposes its session input buffer
		CustomHttpConnection conn = new CustomHttpConnection(10 * 1024);
		try {

			// Open socket
			Socket socket = new Socket();
			socket.connect(target, 5000);
			// Bind it to the connection
			conn.bind(socket);

			// Create and initialize request
			HttpGet request = new HttpGet("/");
			// Force connection close
			request.addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);

			// Create and initialize execution context
			HttpClientContext context = HttpClientContext.create();
			context.setTargetHost(new HttpHost(target.getHostName(), target.getPort()));
			context.setRequestConfig(RequestConfig.DEFAULT);
			context.setAttribute(HttpClientContext.HTTP_REQUEST, request);
			context.setAttribute(HttpClientContext.HTTP_CONNECTION, conn);

			// Process request
			processor.process(request, context);

			// Send request
			conn.sendRequestHeader(request);
			if (request instanceof HttpEntityEnclosingRequest) {
				conn.sendRequestEntity(((HttpEntityEnclosingRequest) request));
			}
			conn.flush();

			SessionInputBuffer connSessionInputBuffer = conn.getSessionInputBuffer();
			IdentityInputStream instream = new IdentityInputStream(connSessionInputBuffer);
			int l;
			byte[] buff = new byte[1024];
			while ((l = instream.read(buff)) != -1) {
				System.out.println(new String(buff, 0, l, Consts.ASCII));
				response = new String(buff, 0, l, Consts.ASCII);
			}
		} finally {
			conn.close();
		}
		return response;
	}
}