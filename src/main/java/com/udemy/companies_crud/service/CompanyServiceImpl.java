package com.udemy.companies_crud.service;

import com.udemy.companies_crud.dto.CompanyDTO;
import com.udemy.companies_crud.entity.Category;
import com.udemy.companies_crud.entity.Company;
import com.udemy.companies_crud.entity.WebSite;
import com.udemy.companies_crud.repository.CompanyRepository;
import com.udemy.companies_crud.repository.WebSiteRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final WebsiteService websiteService;
    private final WebSiteRepository webSiteRepository;

    @Override
    public CompanyDTO readById(Long id) {
        log.info("Reading company by ID: {}", id);
        CompanyDTO companyDTO = new CompanyDTO();
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Company not found with ID: " + id));
        BeanUtils.copyProperties(company, companyDTO);
        companyDTO.setWebSites(websiteService.getWebsitesByCompanyId(id));
        return companyDTO;
    }

    @Override
    public CompanyDTO readByNames(String name) {
        log.info("Reading company by name: {}", name);
        Company company = companyRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Company not found with name: " + name));
        CompanyDTO companyDTO = new CompanyDTO();
        BeanUtils.copyProperties(company, companyDTO);
        companyDTO.setWebSites(websiteService.getWebsitesByCompanyId(companyDTO.getId()));
        return companyDTO;
    }

    @Override
    public CompanyDTO create(CompanyDTO company) throws Exception {
        log.info("Creating company: {}", company);
        company.getWebSites().forEach(webSite -> {
            if (java.util.Arrays.stream(Category.values()).noneMatch(category -> webSite.getCategory().equals(category))) {
                webSite.setCategory(Category.NONE);
            }
        });
        final Company newCompany;
        if (company.getId() != null) {
            newCompany = companyRepository.findById(company.getId()).get();
        } else {
            newCompany = new Company();
        }

        BeanUtils.copyProperties(company, newCompany);
        company.getWebSites().forEach(ws -> {
            WebSite newWebSite = new WebSite();
            if(ws.getId() != null) {
                newWebSite = webSiteRepository.findById(ws.getId()).get();
                BeanUtils.copyProperties(ws, newWebSite);
                newCompany.getWebSite().add(newWebSite);
            }else{
                BeanUtils.copyProperties(ws, newWebSite);
                newCompany.getWebSite().add(newWebSite);
            }
        });

        Company savedCompany = companyRepository.save(newCompany);
        CompanyDTO savedCompanyDTO = readById(savedCompany.getId());
        log.info("Company created with ID: {}", savedCompanyDTO.getId());
        return savedCompanyDTO;
    }

    @Override
    public void deleteCompany(String name) throws Exception {
        log.info("Deleting company with name: {}", name);
        CompanyDTO companyDTO = readByNames(name);
        if (Optional.ofNullable(companyDTO).isPresent()) {
            companyRepository.deleteById(companyDTO.getId());
            log.info("Company with ID: {} deleted successfully", companyDTO.getId());
        }
        log.warn("Company with name: {} not found for deletion", name);
    }
}
