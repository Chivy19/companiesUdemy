package com.udemy.companies_crud.repository;

import com.udemy.companies_crud.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    public Optional<Company> findByName(String name);
}
