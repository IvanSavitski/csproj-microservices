package test.task.csproj.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;
import test.task.csproj.events.CountryKafkaEvent;
import test.task.csproj.models.Country;
import test.task.csproj.models.Country_;
import test.task.csproj.repository.CountryRepository;

import java.util.List;

@Service
@Slf4j
public class CountryService {

    private final CountryRepository countryRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public CountryService(CountryRepository countryRepository,
                          KafkaTemplate<String, Object> kafkaTemplate) {
        this.countryRepository = countryRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Cacheable(value = "country", key = "#root.method.name")
    public List<Country> getCountriesWithEnglish() {

        log.info(countryRepository.findCountriesWithEnglish().toString());

        return countryRepository.findCountriesWithEnglish();
    }

    @Cacheable(value = "country", key = "#root.method.name")
    public List<Country> getCountriesWithMoreThenThreeLanguages() {
        return countryRepository.findCountriesWithMoreThenThreeLanguages();
    }

    @Cacheable(value = "country", key = "#root.method.name")
    public List<Country> getCountriesWithFrancheEnglish() {
        return countryRepository.findCountriesWithFrancheEnglish();
    }

    @Cacheable(value = "country", key = "#root.method.name")
    public List<Country> getCountriesWithMoreThenTenMillionPopulation() {
        return countryRepository.findCountriesWithMoreThenTenMillionPopulation();
    }

    @Cacheable(value = "country", key = "#root.method.name")
    public List<Country> getCountriesWhere_CapitalCitizensPopulation_isMoreThan_OthersCitiesPopulation() {
        return countryRepository.findCountriesWhere_CapitalCitizensPopulation_isMoreThan_OthersCitiesPopulation();
    }

    @Cacheable(value = "country", key = "#root.method.name")
    public List<Country> getCountriesWithoutEnglish() {
        return countryRepository.findCountriesWithoutEnglish();
    }

    @Cacheable(value = "country", key = "#root.method.name")
    public List<Country_> getCountriesWithBiggestLanguagesCount() {
        return countryRepository.findCountriesWithBiggestLanguagesCount();
    }

    @Cacheable(value = "country", key = "#root.method.name")
    public List<Country> getAllCountries() {
        List<Country> countries = countryRepository.findAllCountries();
        log.info(countries.toString());

        CountryKafkaEvent event = new CountryKafkaEvent("CountriesSending", countries);
        log.info(String.valueOf(event));

        this.kafkaTemplate.send("countries_sending_topic", event);

        return countries;
    }
}
