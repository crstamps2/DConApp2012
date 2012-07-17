/**
 * 
 */
package com.dconapp.provider.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.files.FileWriteChannel;
import com.google.appengine.api.files.GSFileOptions.GSFileOptionsBuilder;

/**
 * @author Cody
 *
 */
@SuppressWarnings("serial")
public class DataService extends HttpServlet {
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp){
		try{
		InputStreamReader input = new InputStreamReader(req.getInputStream());
		BufferedReader reader = new BufferedReader(input);
		
		// Get the file service
		FileService fileService = FileServiceFactory.getFileService();

		/**
		 * Set up properties of your new object
		 * After finalizing objects, they are accessible
		 * through Cloud Storage with the URL:
		 * http://commondatastorage.googleapis.com/my_bucket/my_object
		 */
		GSFileOptionsBuilder optionsBuilder = new GSFileOptionsBuilder()
		  .setBucket("dconjson")
		  .setKey("json")
		  .setAcl("public-read")
		  .setMimeType("application/json");

		// Create your object
		AppEngineFile writableFile = fileService.createNewGSFile(optionsBuilder.build());
		
		// Open a channel for writing
		boolean lockForWrite = false; // Do you want to exclusively lock this object?
		FileWriteChannel writeChannel = fileService.openWriteChannel(writableFile, lockForWrite);
		
		// For this example, we write to the object using the PrintWriter
		PrintWriter out = new PrintWriter(Channels.newWriter(writeChannel, "UTF8"));
		out.println(reader.readLine());

		// Close the object without finalizing.
		out.close();

		/**
		 * You can write to this file again, later, by saving the file path.
		 * It is not possible to read the file until you finalize it.
		 * Once you finalize a file, it is not possible to write to it.
		 */

		// Save the file path
		String path = writableFile.getFullPath();

		// Lock the file so no one else can access it at the same time
		lockForWrite = true;

		// Write to the unfinalized file again in a separate request
		writableFile = new AppEngineFile(path);
		writeChannel = fileService.openWriteChannel(writableFile, lockForWrite);
		writeChannel.write(ByteBuffer.wrap("And miles to go before I sleep.".getBytes()));
		
		// Finalize the object
		writeChannel.closeFinally();
		}
		catch (IOException ioe){
			System.out.println(ioe.getMessage());
		}
	}
	

}
