package com.ljunggren.reportGenerator;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class CSVGenerator extends GeneratorBase {
	
	private char delimiter;

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
