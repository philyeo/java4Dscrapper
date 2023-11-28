package com.philipy.scrapper.java4Dscrapper2.shared.persistance.document;

import com.philipy.scrapper.java4Dscrapper2.shared.dto.DamacaiResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "damacai_results") // Specifies the MongoDB collection name
public class DamacaiResults {
    @Id
    private String id;

    private String drawDate;
    private DamacaiResult result;

    public DamacaiResults(String drawDate, DamacaiResult result) {

        this.drawDate = drawDate;
        this.result = result;
    }
}
