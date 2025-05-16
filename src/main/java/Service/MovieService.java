package Service;


import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;

import model.Movie;


public class MovieService {
	
	// get original data from CSV
	public List<Movie> getMovieData(List<String> data) {
		List<Movie> movieList = new ArrayList<>();
		
		for (int i = 0; i < data.size(); i++) {
			
			CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
			String[] tokens = null;
			
			try {
				tokens = parser.parseLine(data.get(i));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Movie movie = new Movie();
			
			movie.setMovieId(i + 1);
			movie.setPark(tokens[0]);
			movie.setSerialNo(Integer.parseInt(tokens[1]));			
			
			java.sql.Date sqlDate = java.sql.Date.valueOf(
				    LocalDate.parse(tokens[2], DateTimeFormatter.ofPattern("yyyyMMdd"))
				);
				movie.setShowDate(sqlDate);
			
			movie.setMovieName(tokens[3]);
			
			if (tokens[4] == null | tokens[4].isEmpty()) {
				movie.setViewers(0);
			} else {
				movie.setViewers(Integer.parseInt(tokens[4]));
			}
			
						
			movieList.add(movie);
		}		
		return movieList;
	}
	
	
	// export CSV from database
	// 輸出資料庫中所有電影資料到指定的 CSV 檔案
	 public void exportCsv(List<Movie> movies, String csvOutputPath) throws IOException {
	        CSVFormat format = CSVFormat.DEFAULT
	                .builder()
	                .setHeader("movieId", "park", "serialNo", "showDate", "movieName", "viewers")
	                .setSkipHeaderRecord(false)
	                .build();

	        try (
	            Writer writer = Files.newBufferedWriter(Paths.get(csvOutputPath));
	            CSVPrinter printer = new CSVPrinter(writer, format)
	        ) {
	            for (Movie m : movies) {
	                printer.printRecord(
	                    m.getMovieId(),
	                    m.getPark(),
	                    m.getSerialNo(),
	                    m.getShowDate(),
	                    m.getMovieName(),
	                    m.getViewers()
	                );
	            }
	        }
	    }
}
