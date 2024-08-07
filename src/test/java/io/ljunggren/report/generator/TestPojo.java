package io.ljunggren.report.generator;

import java.util.Date;

import io.ljunggren.report.generator.annotation.DateFormatter;
import io.ljunggren.report.generator.annotation.Reportable;
import io.ljunggren.report.generator.annotation.StringFormatter;
import io.ljunggren.report.generator.annotation.StringFormatter.Format;
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
