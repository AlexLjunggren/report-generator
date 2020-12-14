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

	@Reportable(headerName = "Name", order = 1)
	@StringFormatter(format = Format.UPPERCASE)
	private String name;
	
	@Reportable(headerName = "Year", order = 0)
	private int year;
	
	private String city;
	
	@Reportable(headerName = "Group", order = 2)
	private String group;
	
	@Reportable(headerName = "Touch Date", order = 99)
	@DateFormatter(format = "yyyy-MM-dd")
	private Date touchDate;
	
    @Reportable(headerName = "Application", order = 3)
	public String getApplication() {
	    return "Report Generator";
	}

}
