package com.glassdoor.test.intern.first.model.repo;

import com.glassdoor.test.intern.first.model.Payee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * MongoRespository interface for the model Payee
 */
public interface PayeeRepo extends MongoRepository<Payee, Integer> {
    Payee findById(int id);

    List<Payee> findByName(String name);

    Payee findByIdAndName(int id, String name);
}