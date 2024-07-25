package test.task.csproj.events;

import lombok.*;
import java.util.List;

@Data
public class CountryEvent {

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
