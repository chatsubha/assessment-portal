package com.portal.model;

import java.util.Date;

public class UserActivity {

	int user_id;
    int category_id;
    double score;
	String result;
	String attentedDate;
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
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
	public String getAttentedDate() {
		return attentedDate;
	}
	public void setAttentedDate(String attentedDate) {
		this.attentedDate = attentedDate;
	}
	public String toString(){  
	    return user_id +" "+ category_id + " " + score + " " + result;  
	}  
	
}
