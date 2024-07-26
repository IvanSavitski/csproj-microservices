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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(value = "countries")
public class Country_ implements Serializable {

    @Column(value = "max(languages_count)")
    private Long languagesCount;

    @PrimaryKey
    @Column(value = "country_name")
    private String countryName;
}