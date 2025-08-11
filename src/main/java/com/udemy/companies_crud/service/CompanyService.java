package com.udemy.companies_crud.service;

import com.udemy.companies_crud.dto.CompanyDTO;
import com.udemy.companies_crud.entity.Company;

public interface CompanyService {
    public CompanyDTO readById(Long id) ;
    public CompanyDTO readByNames(String name);
    public CompanyDTO create(CompanyDTO company) throws Exception;
    void deleteCompany(String name) throws  Exception;
}
