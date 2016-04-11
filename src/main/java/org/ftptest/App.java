package org.ftptest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {	String ftpparam = "10.150.27.141/ftpmanager/ehl1234/data2/GBK";
    	String imgUrl = "201512/23/15/1009/20151223155951988_01_津NB1234_1009_02_21.jpg";
    	InputStream inputStream = null;
    	OutputStream output =  null;
    	
    	String[] path = ftpparam.split("/");
    	FTPClient ftp = new FTPClient();
		ftp.setControlEncoding(path[4]);
		ftp.connect(path[0]);
		ftp.login(path[1], path[2]);
		ftp.changeWorkingDirectory(path[3]);
		//String folders[] = imgUrl.split("/");
		//for (int j = 0; j < folders.length - 1; j++) {
		//	ftp.changeWorkingDirectory(folders[j]);
		//}
		inputStream = ftp.retrieveFileStream(imgUrl);
		ftp.disconnect();
		output=new FileOutputStream(new File("C:/Users/liuyanghe/Desktop/zzz.jpg"));
		IOUtils.copy(inputStream, output);
		inputStream.close();
		output.close();
		
    }
}
