package com.portal.model;

import java.io.Serializable;
import java.sql.Date;

public class UserActivityHistory  implements Serializable {
	
	int user_id;
	String user_name;
	int category_id;
    double score;
	String result;
	String category_name;
	int num_of_attempts;
	String last_attempt_date;
	
	public int getNum_of_attempts() {
		return num_of_attempts;
	}
	public void setNum_of_attempts(int num_of_attempts) {
		this.num_of_attempts = num_of_attempts;
	}
	public String getLast_attempt_date() {
		return last_attempt_date;
	}
	public void setLast_attempt_date(String last_attempt_date) {
		this.last_attempt_date = last_attempt_date;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	public String toString(){  
	    return user_id +" "+ category_id + " " + score + " " + result;  
	}  
	
}
