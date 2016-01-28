package com.izolotov.imagesender;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;

public class ImageSender {

	private static String URL = "http://127.0.0.1:5000/";
	
	public void sendImage(File imgFile) throws ClientProtocolException, IOException {
		HttpClientBuilder clientBuilder = HttpClientBuilder.create();
		HttpClient client = clientBuilder.build();
		HttpPost post = new HttpPost(URL);
		
		MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
		ContentBody content = new FileBody(imgFile, "image/jpeg");
		entityBuilder.addPart("imageFile", content);
		HttpEntity entity = entityBuilder.build();
		
		post.setEntity(entity);
		
		System.out.println("executing request " + post.getRequestLine());
		
		HttpResponse response = client.execute(post);
		
		System.out.println(response.getStatusLine());
	}
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		if(args.length == 0) {
			System.out.println("Image file required.");
		}
		ImageSender sender = new ImageSender();
		sender.sendImage(new File(args[0]));
	}
	
}
