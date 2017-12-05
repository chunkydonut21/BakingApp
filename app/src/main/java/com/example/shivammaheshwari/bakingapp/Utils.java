package com.example.shivammaheshwari.bakingapp;

import android.util.Log;

import com.example.shivammaheshwari.bakingapp.model.BakingIngredients;
import com.example.shivammaheshwari.bakingapp.model.BakingRecipe;
import com.example.shivammaheshwari.bakingapp.model.BakingSteps;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    private static URL createUrl(String stringUrl) throws MalformedURLException {
        URL url = new URL(stringUrl);
        return url;
    }

    private static String httpRequest(URL url) throws IOException {
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        String response = "";
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);// milliseconds
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200), then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                response = readFromStream(inputStream);
            } else {
                Log.e("dc", "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e("dd", "Problem retrieving info", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return response;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static List<BakingRecipe> extractingJson(String jsonString) throws JSONException {

        List<BakingRecipe> recipe = new ArrayList<>();

        JSONArray root = new JSONArray(jsonString);
        for (int i = 0; i < root.length(); i++) {

            String recipeId = root.getJSONObject(i).getString("id");
            String recipeName = root.getJSONObject(i).getString("name");
            String recipeServings = root.getJSONObject(i).getString("servings");
            String recipeImage = root.getJSONObject(i).getString("image");
            JSONArray recipeIngredients = root.getJSONObject(i).getJSONArray("ingredients");

            List<BakingIngredients> ingredientList = new ArrayList<>();
            List<BakingSteps> recipeList = new ArrayList<>();

            for (int j = 0; j < recipeIngredients.length(); j++) {

                String recipeQuantity = recipeIngredients.getJSONObject(j).getString("quantity");
                String recipeMeasure = recipeIngredients.getJSONObject(j).getString("measure");
                String ingredient = recipeIngredients.getJSONObject(j).getString("ingredient");

                ingredientList.add(new BakingIngredients(recipeQuantity, recipeMeasure, ingredient));

            }

            JSONArray stepsRecipe = root.getJSONObject(i).getJSONArray("steps");

            for (int k = 0; k < stepsRecipe.length(); k++) {

                String id = stepsRecipe.getJSONObject(k).getString("id");
                String shortDescription = stepsRecipe.getJSONObject(k).getString("shortDescription");
                String description = stepsRecipe.getJSONObject(k).getString("description");
                String videoUrl = stepsRecipe.getJSONObject(k).getString("videoURL");
                String thumbnailUrl = stepsRecipe.getJSONObject(k).getString("thumbnailURL");

                recipeList.add(new BakingSteps(id, shortDescription, description, videoUrl, thumbnailUrl));

            }

            BakingRecipe bakingRecipe = new BakingRecipe(recipeName, recipeId, recipeServings, recipeImage, ingredientList, recipeList);
            recipe.add(bakingRecipe);

        }

        return recipe;
    }


    public static List<BakingRecipe> fetchData(String stringUrl) throws JSONException, IOException {

        URL url = createUrl(stringUrl);
        String jsonResponse = httpRequest(url);
        List<BakingRecipe> listOfMovies = extractingJson(jsonResponse);

        return listOfMovies;

    }
}

