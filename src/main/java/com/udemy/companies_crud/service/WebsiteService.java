package com.udemy.companies_crud.service;

import com.udemy.companies_crud.dto.WebSiteDTO;

import java.util.List;

public interface WebsiteService {
    public List<WebSiteDTO> getWebsitesByCompanyId(Long companyId);
}
