package io.ljunggren.report.generator.csv;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import io.ljunggren.report.generator.Generator;
import io.ljunggren.report.generator.Record;

public class CSVGenerator extends Generator {
	
	private char delimiter = ',';
	
	public CSVGenerator(List<?> data) {
	    super(data);
	}

	public CSVGenerator(List<?> data, char delimiter) {
		super(data);
		this.delimiter = delimiter;
	}

	@Override
	public String generate() {
		try {
			Writer writer = generateWriter(getHeaders(), getRecords(), delimiter);
			return writer.toString();
		} catch (IOException e) {
			return e.getMessage();
		}
	}

	@SuppressWarnings("resource")
	private Writer generateWriter(List<String> headers, List<Record> records, char delimiter) throws IOException {
		Writer writer = new StringWriter();
		CSVPrinter printer = new CSVPrinter(
				writer, 
				CSVFormat.DEFAULT.withDelimiter(delimiter));
		printer.printRecord(headers);
		for (Record record: records) {
			List<String> values = record.getItems().stream()
					.map(item -> getValueFromItem(item))
					.collect(Collectors.toList());
			printer.printRecord(values);
		}
		printer.flush();
		return writer;
	}
	
}
