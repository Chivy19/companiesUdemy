package com.udemy.companies_crud.repository;

import com.udemy.companies_crud.entity.WebSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WebSiteRepository extends JpaRepository<WebSite, Long> {

    @Query ( value = "select * from web_site where id_company = :companyId", nativeQuery = true)
    public List<WebSite> findByCompanyId(Long companyId);

}
