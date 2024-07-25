package test.task.csproj.repository;


import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;
import test.task.csproj.models.Country;
import test.task.csproj.models.Country_;

import java.util.List;

@Repository
public interface CountryRepository extends CassandraRepository<Country, String> {
    @Query("SELECT * FROM psql3.countries WHERE country_languages LIKE 'English%';")
    List<Country> findCountriesWithEnglish();

    @Query("SELECT * FROM psql3.countries WHERE languages_count > 2 ALLOW FILTERING;")
    List<Country> findCountriesWithMoreThenThreeLanguages();

    @Query("SELECT * FROM psql3.countries  WHERE country_languages_list CONTAINS 'English' AND country_languages_list CONTAINS 'French' ALLOW FILTERING;")
    List<Country> findCountriesWithFrancheEnglish();

    @Query("SELECT * FROM psql3.countries WHERE country_population > 10000000 ALLOW FILTERING;")
    List<Country> findCountriesWithMoreThenTenMillionPopulation();

    @Query("SELECT * FROM psql3.countries WHERE is_capital_population_bigger = true ALLOW FILTERING;")
    List<Country> findCountriesWhere_CapitalCitizensPopulation_isMoreThan_OthersCitiesPopulation();

    @Query("SELECT * FROM psql3.countries WHERE is_english_language = false ALLOW FILTERING;")
    List<Country> findCountriesWithoutEnglish();
    @Query("SELECT max(languages_count) FROM psql3.countries;")
    List<Country_> findCountriesWithBiggestLanguagesCount();

    @Query("SELECT * FROM psql3.countries;")
    List<Country> findAllCountries();
}