package model;

import java.sql.Date;

public class Movie {
	private Integer movieId;
	private String park;
	private Integer serialNo;
	private Date showDate;
	private String movieName;
	private Integer viewers;
	
	public Movie() {
	}
	
	
	public Movie(String park, Integer serialNo, Date showDate, String movieName, Integer viewers) {
		this.park = park;
		this.serialNo = serialNo;
		this.showDate = showDate;
		this.movieName = movieName;
		this.viewers = viewers;
	}



	public Integer getMovieId() {
		return movieId;
	}

	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

	public String getPark() {
		return park;
	}

	public void setPark(String park) {
		this.park = park;
	}

	public Integer getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}

	public Date getShowDate() {
		return showDate;
	}

	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public Integer getViewers() {
		return viewers;
	}

	public void setViewers(Integer viewers) {
		this.viewers = viewers;
	}

	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", park=" + park + ", serialNo=" + serialNo + ", showDate=" + showDate
				+ ", movieName=" + movieName + ", viewers=" + viewers + "]";
	}
	
	
}
