package com.udemy.companies_crud.controller;

import com.udemy.companies_crud.dto.CompanyDTO;
import com.udemy.companies_crud.service.CompanyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/company")
@Slf4j
public class CompanyController {
    private CompanyService companyService;

    @GetMapping("getById/{id}")
    public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable Long id) {
        log.info("GET company by id: {}", id);
        return ResponseEntity.ok(companyService.readById(id));
    }

    @GetMapping("getByName/{name}")
    public ResponseEntity<CompanyDTO> getCompanyByName(@PathVariable String name) {
        log.info("GET company by name: {}", name);
        return ResponseEntity.ok(companyService.readByNames(name));
    }

    @PostMapping
    public ResponseEntity<CompanyDTO> postCompanyDTO(@RequestBody CompanyDTO companyDTO) throws Exception {
        log.info("POST company: {}", companyDTO.getName());
        return ResponseEntity.created(URI.create(this.companyService.create(companyDTO).getName())).build();
    }

    @PutMapping()
    public ResponseEntity<CompanyDTO> putCompanyDTO(@RequestBody CompanyDTO companyDTO) throws Exception {
        log.info("PUT company: {}", companyDTO.getName());
        return ResponseEntity.ok(companyService.create(companyDTO));
    }

    @DeleteMapping("{name}")
    public ResponseEntity<?>delete(@PathVariable String name) throws Exception {
        log.info("DELETE company by name: {}", name);
        companyService.deleteCompany(name);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
