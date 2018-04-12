package ftn.isa.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Group implements Serializable{

	private static final long serialVersionUID = 1063875163196479478L;
	
	private Date date;
	
	public Group(Date date) {
		this.date=date;
	}
	
	public String getDate(){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		int m=calendar.get(Calendar.MONTH)+1;
		return calendar.get(Calendar.DATE)+"."+m+"."+calendar.get(Calendar.YEAR)+".";
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public Date getDateAsDate(){
		return date;
	}
	
}
