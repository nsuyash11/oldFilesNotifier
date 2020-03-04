package com.beans;

public class FileBean {
	public String fileName;
	public double idleTimeHrs;
	
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public double getIdleTimeHrs() {
		return idleTimeHrs;
	}
	public void setIdleTimeHrs(double idleTimeHrs) {
		this.idleTimeHrs = idleTimeHrs;
	}
	
	public FileBean(String fileName, double idleTimeHrs) {
		super();
		this.fileName = fileName;
		this.idleTimeHrs = idleTimeHrs;
	}
	public FileBean() {
		super();
	}
	
	@Override
	public String toString() {
		return "FileBean [fileName=" + fileName + ", idleTimeHrs=" + idleTimeHrs + "]";
	}
		
}
