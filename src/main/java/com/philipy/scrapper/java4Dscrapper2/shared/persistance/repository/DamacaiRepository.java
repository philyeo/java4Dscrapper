package com.philipy.scrapper.java4Dscrapper2.shared.persistance.repository;

import com.philipy.scrapper.java4Dscrapper2.shared.dto.DamacaiResult;
import com.philipy.scrapper.java4Dscrapper2.shared.persistance.document.DamacaiResults;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DamacaiRepository extends MongoRepository<DamacaiResult, String> {

    void insert(DamacaiResults results);
}
