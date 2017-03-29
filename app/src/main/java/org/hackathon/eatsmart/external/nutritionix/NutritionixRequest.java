package org.hackathon.eatsmart.external.nutritionix;

public class NutritionixRequest {
	
	private String query;
	private int num_servings;
	private String aggregate = "string";
	
	
	public String getQuery() {
		return query;
	}
	public int getNum_servings() {
		return num_servings;
	}
	public String getAggregate() {
		return aggregate;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public void setNum_servings(int num_servings) {
		this.num_servings = num_servings;
	}
}
