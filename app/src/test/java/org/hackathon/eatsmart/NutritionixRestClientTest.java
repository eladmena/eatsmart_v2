package org.hackathon.eatsmart;

import org.hackathon.eatsmart.external.nutritionix.NutritionixFoodResponse;
import org.hackathon.eatsmart.external.nutritionix.NutritionixRestClient;
import org.junit.Ignore;
import org.junit.Test;

public class NutritionixRestClientTest {
	
	private NutritionixRestClient client = new NutritionixRestClient();
	@Test
	@Ignore
	public void getNutrientsTest() {
		String ingredients = "3 tablespoons butter, 2 tablespoons olive oil, 0.5 teaspoon dried parsley, 0.5 teaspoon dried basil, 0.125 teaspoon dried oregano, 1.5 cloves garlic, minced 0.25 teaspoon salt, 1 1/2 teaspoons lemon juice, 1.5 teaspoons white cooking wine, 2 skinless boneless chicken breast halves, sliced 0.5 pound fresh asparagus trimmed and cut into thirds, 1 cup sliced fresh mushrooms";
		int numberOfServing = 4;

		NutritionixFoodResponse response = client.getNutrients(ingredients, numberOfServing);
		System.out.println(response);

	}
	@Test
	public void getNutrientsMockTest() {
		String ingredientsFull = "100 gram onion, pinch salt, 1 teaspoon butter, 2 eggs";
		int numberOfServing = 1;

		NutritionixFoodResponse response = client.getNutrientsMock(ingredientsFull, numberOfServing);
		String ingredientsPartial = "pinch salt, 1 teaspoon butter, 2 eggs";
		NutritionixFoodResponse responsePartial = client.getNutrientsMock(ingredientsPartial, numberOfServing);
		System.out.println(responsePartial);
		System.out.println(response);

	}
}
