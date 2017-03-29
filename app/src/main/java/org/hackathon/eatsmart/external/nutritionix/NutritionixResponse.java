package org.hackathon.eatsmart.external.nutritionix;

import java.util.List;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class NutritionixResponse {
	private List<NutritionixFoodResponse> foods;

	public List<NutritionixFoodResponse> getFoods() {
		return foods;
	}

	public void setFoods(List<NutritionixFoodResponse> foods) {
		this.foods = foods;
	}
}
