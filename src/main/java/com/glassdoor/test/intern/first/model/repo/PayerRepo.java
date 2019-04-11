package com.glassdoor.test.intern.first.model.repo;

import com.glassdoor.test.intern.first.model.Payer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * MongoRepository interface for the model Payer
 */
public interface PayerRepo extends MongoRepository<Payer, Integer> {
    Payer findById(int id);

    List<Payer> findByName(String name);

    Payer findByIdAndName(int id, String name);
}
