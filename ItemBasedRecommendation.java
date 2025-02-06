import java.io.File;
import java.io.IOException;

public class ItemBasedRecommendation {

    public static void main(String[] args) throws IOException, TasteException {
        // Load the data model
        DataModel model = new FileDataModel(new File("data.csv"));

        // Calculate item similarity
        ItemSimilarity similarity = new TanimotoCoefficientSimilarity(model);

        // Create an ItemBasedRecommender
        ItemBasedRecommender recommender = new GenericItemBasedRecommender(model, similarity);

        // Get recommendations for a specific user
        long userId = 1;  // Replace with the user ID you want recommendations for
        int numberOfRecommendations = 5;
        
        // Get recommendations for the given user
        var recommendations = recommender.recommend(userId, numberOfRecommendations);

        // Display recommendations
        System.out.println("Recommendations for user " + userId + ":");
        for (var recommendation : recommendations) {
            System.out.println("Item " + recommendation.getItemID() + " with preference value: " + recommendation.getValue());
        }
    }
}
