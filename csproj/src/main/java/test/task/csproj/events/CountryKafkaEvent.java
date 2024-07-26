package test.task.csproj.events;

import lombok.Data;
import lombok.NoArgsConstructor;
import test.task.csproj.models.Country;

import java.util.List;


@Data
@NoArgsConstructor
public class CountryKafkaEvent {
    public CountryKafkaEvent(String type, List<Country> countries){
        this.type = type;
        this.countries = countries;
    }
    private String type;
    private List<Country> countries;
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public List<Country> getCountry() {
        return countries;
    }
    public void setCountry(List<Country> countries) {
        this.countries = countries;
    }
}