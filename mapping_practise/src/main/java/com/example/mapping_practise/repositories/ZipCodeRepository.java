package com.example.mapping_practise.repositories;

import com.example.mapping_practise.model.ZipCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZipCodeRepository extends JpaRepository<ZipCode,Long> {
}
