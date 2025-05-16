package model;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import Service.MovieService;
import dao.MovieDaoImpl;
import utils.GetData;

public class SearchMovie {

	public static void main(String[] args) {
		List<String> data = GetData.getData("C:/Users/user/Downloads/movie.csv");
		
		MovieService movieService = new MovieService();
		MovieDaoImpl movieDaoImpl = new MovieDaoImpl();
		
		List<Movie> movieList = movieService.getMovieData(data);
		Path  exportPath = Paths.get("output/movies_export.csv");
		
		
		try {
			movieService.exportCsv(movieList, "output/movies_export.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("OpenCSV 匯出完成： " + exportPath.toAbsolutePath());
		
//		Movie movie = new Movie("林口好棒", 20, java.sql.Date.valueOf("2025-02-12"), "西遊降魔篇", 1000);
//		
//		Movie tempMovie =  movieDaoImpl.findMovieById(1);
//		
//		boolean ok = movieDaoImpl.updateMovieName(1, "三體星球");
//		if (ok) {
//		    System.out.println("更新成功！");
//		}
//		movieDaoImpl.deleteMovieByName("西遊降魔篇");
		
//		int[] counts = movieDaoImpl.batchInsert(movieList);
//		System.out.println("原始資料共: " + data.size() + "筆資料");
//		System.out.println("成功寫入: " + Arrays.stream(counts).sum() + "筆資料");
		
//		movieList.forEach(System.out :: println);

	}
}
