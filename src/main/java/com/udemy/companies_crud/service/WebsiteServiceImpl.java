package com.udemy.companies_crud.service;

import com.udemy.companies_crud.dto.WebSiteDTO;
import com.udemy.companies_crud.repository.WebSiteRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class WebsiteServiceImpl implements WebsiteService {
    private final WebSiteRepository webSiteRepository;

    @Override
    public List<WebSiteDTO> getWebsitesByCompanyId(Long companyId) {
        return webSiteRepository.findByCompanyId(companyId)
                .stream()
                .map(webSite -> WebSiteDTO.builder()
                        .id(webSite.getId())
                        .name(webSite.getName())
                        .category(webSite.getCategory())
                        .description(webSite.getDescription())
                        .build())
                .toList();
    }
}
