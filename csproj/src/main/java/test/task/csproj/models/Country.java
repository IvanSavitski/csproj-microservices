package test.task.csproj.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(value = "countries")
public class Country implements Serializable {

    @PrimaryKey
    @Column(value = "country_name")
    private String countryName;

    @Column(value = "country_languages")
    private String countryLanguages;

    @Column(value = "country_languages_list")
    private List<String> countryLanguagesList;

    @Column(value = "languages_count")
    private Long languagesCount;

    @Column(value = "is_capital_population_bigger")
    private boolean isCapitalPopulationBigger;

    @Column(value = "is_english_language")
    private boolean isEnglishLanguage;  /**/

    @Column(value = "country_population")
    private Long countryPopulation;

    @Column(value = "capital_population")
    private Long capitalPopulation;

    @Column(value = "non_capital_population")
    private Long nonCapitalPopulation;
}
