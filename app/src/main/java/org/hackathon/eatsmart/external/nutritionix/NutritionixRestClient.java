package org.hackathon.eatsmart.external.nutritionix;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class NutritionixRestClient {
	
	//taken from https://developer.nutritionix.com/admin/applications/1409614464029
	private static final String APP_KEY = "fc99c494be4e2a0eeeb53b2a3dd001fb";
	private static final String APP_ID = "634d16c5";

	public NutritionixFoodResponse getNutrients(String ingredients, int numberOfServing) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.set("x-app-id", APP_ID);
        requestHeaders.set("x-app-key", APP_KEY);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		//restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        
		NutritionixRequest request = new NutritionixRequest();
		request.setNum_servings(numberOfServing);
		request.setQuery(ingredients);
		
		HttpEntity<NutritionixRequest> requestUpdate = new HttpEntity<>(request, requestHeaders);

		
		NutritionixResponse response = restTemplate.postForObject("https://trackapi.nutritionix.com/v2/natural/nutrients", requestUpdate, NutritionixResponse.class);
        return response.getFoods().get(0);
	}

	public NutritionixFoodResponse getNutrientsMock(String ingredients, int numberOfServing) {
		NutritionixFoodResponse response = new NutritionixFoodResponse();
		if (ingredients.contains("butter")) {
			response.serving_weight_grams += 4.73;
			response.nf_calories += 33.92;
			response.nf_total_fat += 3.84;
			response.nf_saturated_fat += 2.43;
			response.nf_cholesterol += 10.17;
			response.nf_sodium += 30.42;
			response.nf_protein += 0.04;
			response.nf_potassium += 1.14;
		}

		if (ingredients.contains("eggs")) {
			response.serving_weight_grams += 100;
			response.nf_calories += 143;
			response.nf_total_fat += 9.51;
			response.nf_saturated_fat += 3.13;
			response.nf_cholesterol += 372;
			response.nf_sodium += 142;
			response.nf_total_carbohydrate += 0.72;
			response.nf_sugars += 0.37;
			response.nf_protein += 12.56;
			response.nf_potassium += 138;
		}

		if (ingredients.contains("salt")) {
			response.serving_weight_grams += 0.33;
			response.nf_sodium += 128.89;
			response.nf_potassium += 0.03;
		}

		if (ingredients.contains("salt")) {
			response.serving_weight_grams += 0.33;
			response.nf_sodium += 128.89;
			response.nf_potassium += 0.03;
		}

		if (ingredients.contains("onion")) {
			response.serving_weight_grams += 100;
			response.nf_calories += 44;
			response.nf_total_fat += 0.19;
			response.nf_saturated_fat += 0.03;
			response.nf_sodium += 3;
			response.nf_total_carbohydrate += 10.15;
			response.nf_dietary_fiber += 1.4;
			response.nf_sugars += 4.73;
			response.nf_protein += 1.36;
			response.nf_potassium += 166;
		}


		return response;
	}

}
