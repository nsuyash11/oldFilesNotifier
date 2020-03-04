package com.tester;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.mailer.SSLEmail;
import com.beans.FileBean;

public class Tester {

	public static void main(String[] args) {
	
		Date date = new Date();
		
		try{
			//String propFilePath = "C:\\Users\\s.ratnakar.nachankar\\Downloads\\javaLearning\\jars\\myJars\\searchFolderPath.properties";
			String propFilePath = "searchFolderPath.properties";
			//String propFilePath = "src/com/resources/searchFolderPath.properties";
			FileReader inputFile = new FileReader(propFilePath);
			Properties props = new Properties();

			// load a properties file
			props.load(inputFile);

			String searchDir = props.getProperty("searchDir");		
			File[] filesList = new File(searchDir).listFiles();


			long currentTime;
			long fileTime;
			long idleTime;
			double idleTimeHrs;
			boolean sendFlag = false;

			List <FileBean> oldFilesBeans = new ArrayList<FileBean>();

			for(File file : filesList) {
				
				fileTime = file.lastModified();
				
				currentTime = date.getTime();
				
				idleTime = currentTime - fileTime;
				idleTimeHrs = idleTime/3600000D;
				
				if(idleTimeHrs > 1D){
					sendFlag = true;
					FileBean fbean = new FileBean();
					fbean.setFileName(file.getName());
					fbean.setIdleTimeHrs(idleTimeHrs);
					
					oldFilesBeans.add(fbean);
					
					System.out.println(file.getName() + " - OLD File");
				}
				else {
					System.out.println(file.getName() + " - Fresh File");
				}
			}
	
			
			if(sendFlag) {
				String msgBody = "Please delete the following files : \n";	
				for(FileBean file : oldFilesBeans) {
					msgBody = msgBody +  file.getFileName() + " - "  + Math.round(file.getIdleTimeHrs()) + " hrs. old\n";
				}
				msgBody += "\nThanks and Regards...\nSuyash !";
				
				System.out.println("\n Email Body : \n" + msgBody);
				
				SSLEmail.dispatchEmail(msgBody);
			}
			else {
				System.out.println("\nNo OLD file found to send mail\n");
			}
	
		}
		catch (Exception e) {
			e.printStackTrace();
		}


	}

}
