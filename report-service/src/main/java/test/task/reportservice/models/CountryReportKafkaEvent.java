package test.task.reportservice.models;


import lombok.*;

import java.util.List;


@Data
@NoArgsConstructor
public class CountryReportKafkaEvent {
    public CountryReportKafkaEvent(String type, List<CountryDTO> countries){
        this.type = type;
        this.countries = countries;
    }
    private String type;
    private List<CountryDTO> countries;
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public List<CountryDTO> getCountry() {
        return countries;
    }
    public void setCountry(List<CountryDTO> countries) {
        this.countries = countries;
    }
}