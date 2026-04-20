package fi.metropolia.assignment18;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Holds saved recommendation lists for the application.
 */
public class RecommendationCatalog {
    
    private final List<Recommendation> recommendations = new ArrayList<>();
    
    public void add(Recommendation recommendation) {
        recommendations.add(recommendation);
    }
    
    public List<Recommendation> getAll() {
        return Collections.unmodifiableList(recommendations);
    }
    
    public Recommendation get(int index) {
        return recommendations.get(index);
    }
    
    public int size() {
        return recommendations.size();
    }
    
    public boolean isValidIndex(int index) {
        return index >= 0 && index < recommendations.size();
    }
}
