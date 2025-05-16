package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Movie;
import utils.HikariCputil;

public class MovieDaoImpl {
	
	//  CSV content write into DB
	public int[] batchInsert(List<Movie> movies) {
		
		String sql = "INSERT INTO movie(park, serialNo, showDate, movieName, viewers)"
				+ "VALUES(?,?,?,?,?)"; 
		
		try (Connection connection = HikariCputil.getDataSource().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			
			// 關閉自動提交
			connection.setAutoCommit(false);
			
			for (Movie movie : movies) {
				
				preparedStatement.setString(1, movie.getPark());
				preparedStatement.setInt(2, movie.getSerialNo());
				preparedStatement.setDate(3, movie.getShowDate());
				preparedStatement.setString(4, movie.getMovieName());
				preparedStatement.setInt(5, movie.getViewers());

				preparedStatement.addBatch();		
			}
			
			int[] results = preparedStatement.executeBatch();
			connection.commit();
			connection.setAutoCommit(true);
			return results;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return new int[0];
	}
	
	// add a movie
	public void addMovie(Movie movie) {
		String sql = "INSERT INTO movie(park, serialNo, showDate, movieName, viewers)"
				+ "VALUES(?,?,?,?,?)";
		
		try (Connection connection = HikariCputil.getDataSource().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			
			preparedStatement.setString(1, movie.getPark());
			preparedStatement.setInt(2, movie.getSerialNo());
			preparedStatement.setDate(3, movie.getShowDate());
			preparedStatement.setString(4, movie.getMovieName());
			preparedStatement.setInt(5, movie.getViewers());
			preparedStatement.execute();
			
			System.out.println("Add movie !");			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}
	
	// delete a movie
	public void deleteMovieByName(String movieName) {
		String sql = "DELETE FROM movie WHERE movieName = ?";
		
		try (Connection connection = HikariCputil.getDataSource().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			
			preparedStatement.setString(1, movieName);
			preparedStatement.execute();
			System.out.println("刪除的電影為:" + movieName);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// find movie by id
	public Movie findMovieById(Integer movieId) {
		String sql = "SELECT * FROM movie WHERE movieId = ?";
		Movie searchMovie = null;
		
		try (Connection connection = HikariCputil.getDataSource().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			
			preparedStatement.setInt(1, movieId);
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				resultSet.next();
				String park = resultSet.getString("park");
				Integer serialNo = resultSet.getInt("serialNo");
				Date showDate = resultSet.getDate("showDate");
				String movieName = resultSet.getString("movieName");
				Integer viewers = resultSet.getInt("viewers");
				
				searchMovie = new Movie(park, serialNo, showDate, movieName, viewers);
//				System.out.println(searchMovie);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return searchMovie;
	}
	
	// update a movie
	// setter -> update
	public boolean updateMovieName(int movieId, String newName) {
	    String sql = "UPDATE movie SET movieName = ? WHERE movieId = ?";
	    try (Connection conn = HikariCputil.getDataSource().getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	         
	        ps.setString(1, newName);
	        ps.setInt(2, movieId);
	        int updated = ps.executeUpdate();
	        return updated > 0;  
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
}
