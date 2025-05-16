package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class GetData {
	public static List<String> getData(String path) {
		List<String> dataList = new ArrayList<>();
		
		try (CSVReader csvReader = new CSVReader(
		         new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {

		    String[] line;
		    // 跳過表頭
		    csvReader.readNext(); 
		    while ((line = csvReader.readNext()) != null) {
		        dataList.add(String.join(",", line));
		    }

		} catch (IOException | CsvValidationException e) {
		    e.printStackTrace(); 
		}
		return dataList;
	}
}
