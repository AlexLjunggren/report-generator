package com.ljunggren.reportGenerator;

import java.util.Date;

import com.ljunggren.reportGenerator.annotation.DateFormatter;
import com.ljunggren.reportGenerator.annotation.Reportable;
import com.ljunggren.reportGenerator.annotation.StringFormatter;
import com.ljunggren.reportGenerator.annotation.StringFormatter.Format;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestPojo {

	@Reportable(headerName = "Name", column = "B")
	@StringFormatter(format = Format.UPPERCASE)
	private String name;
	
	@Reportable(headerName = "Year", column = "A")
	private int year;
	
	private String city;
	
	@Reportable(headerName = "Group", column = "C")
	private String group;
	
	@Reportable(headerName = "Touch Date", column = "E")
	@DateFormatter(format = "yyyy-MM-dd")
	private Date touchDate;
	
    @Reportable(headerName = "Application", column = "D")
	public String getApplication() {
	    return "Report Generator";
	}

}
