package org.ftptest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.net.ftp.FTPClient;

/**
 * Hello world!
 *
 */
public class App 
{
	volatile static int count=0;
	static  FTPClientTemplate ftp = new FTPClientTemplate();
	static InputStream input = null;
	static  ThreadLocal<InputStream> ftpClientThreadLocal = new ThreadLocal<InputStream>();
    public static void main( String[] args ) throws IOException
    {	String ftpIp = "host45";
    	Integer ftpPort = 2121;
    	String user = "admin";
    	String password = "ehl1234";
    	String enocdeing = "UTF-8";
    	ExecutorService pool = Executors.newFixedThreadPool(100);
        ftp.setHost(ftpIp);
        ftp.setPort(ftpPort);
        ftp.setUsername(user);
        ftp.setPassword(password);
        ftp.setBinaryTransfer(true);
        ftp.setPassiveMode(false);
        ftp.setEncoding(enocdeing);
        input=new FileInputStream(new File("C:\\Users\\liuyanghe\\Desktop\\2013-05-01 091748.jpg"));
        //boolean ret = ftp.put("/group/tbdev/query/user-upload/12345678910.txt", "D:/099_temp/query/12345.txt");
        //System.out.println(ret);
        //ftp.mkdir("/1234", "/",false);
        new App().new Counter().start();
//        input= new FileInputStream(new File("C:\\Users\\liuyanghe\\Desktop\\2013-05-01 091748.jpg"));
//        for(;;){
//        	 ftp.put("/1234/"+UUID.randomUUID()+".jpg", input,true);
//        	 count++;
//        }
//        new App().new Counter().start();
        
       for(int i=0 ;i<10;i++){
        pool.submit(new Runnable() {
			@Override
			public void run() {
				while(true){
					 ftp.put("/1234/"+UUID.randomUUID()+".jpg", input,false);
					 count++;
				}
				
			}
		});
        }
        
        
      
        //ftp.disconnect();
        //ftp.mkdir("user-upload1");
        //ftp.disconnect();
        
        //String[] aa = {"/group/tbdev/query/user-upload/123.txt", "/group/tbdev/query/user-upload/SMTrace.txt"};
        //ftp.delete(aa);
		//System.out.println("ok");
    }
    
    
    
    
    
  public   class Counter extends Thread{

		@Override
		public void run() {
			while(true){
			System.out.println(count);
			count=0;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
    	
    }
}
