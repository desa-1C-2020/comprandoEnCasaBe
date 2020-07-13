package ar.edu.unq.desapp.comprandoencasa.controllers.to;

import java.util.List;

public class TakeAwayTO {
    private List<Long> commercesId;
    private String suggestedDay;

    public List<Long> getCommercesId() {
        return commercesId;
    }

    public void setCommercesId(List<Long> commercesId) {
        this.commercesId = commercesId;
    }

    public String getSuggestedDay() {
        return suggestedDay;
    }

    public void setSuggestedDay(String suggestedDay) {
        this.suggestedDay = suggestedDay;
    }
}
