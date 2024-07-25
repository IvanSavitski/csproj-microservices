package test.task.reportservice.models;

import lombok.*;

import java.util.List;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountryDTO {
    private String countryName;
    private String countryLanguages;
    private List<String> countryLanguagesList;
    private Long languagesCount;
    private boolean isCapitalPopulationBigger;
    private boolean isEnglishLanguage;
    private Long countryPopulation;
    private Long capitalPopulation;
    private Long nonCapitalPopulation;
}