package com.udemy.companies_crud.controller;

import com.udemy.companies_crud.dto.CompanyDTO;
import com.udemy.companies_crud.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(
        name = "Companies resource",
        description = "Controller for managing companies and their websites"
)
public class CompanyController {
    private CompanyService companyService;

    @Operation(
            summary = "Get company by ID"
    )
    @GetMapping("getById/{id}")
    public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable Long id) {
        log.info("GET company by id: {}", id);
        return ResponseEntity.ok(companyService.readById(id));
    }

    @Operation(
            summary = "Get company by name"
    )
    @GetMapping("getByName/{name}")
    public ResponseEntity<CompanyDTO> getCompanyByName(@PathVariable String name) {
        log.info("GET company by name: {}", name);
        return ResponseEntity.ok(companyService.readByNames(name));
    }

    @Operation(summary = "Create a new company")
    @PostMapping
    public ResponseEntity<CompanyDTO> postCompanyDTO(@RequestBody CompanyDTO companyDTO) throws Exception {
        log.info("POST company: {}", companyDTO.getName());
        return ResponseEntity.created(URI.create(this.companyService.create(companyDTO).getName())).build();
    }

    @Operation(summary = "Update an existing company")
    @PutMapping()
    public ResponseEntity<CompanyDTO> putCompanyDTO(@RequestBody CompanyDTO companyDTO) throws Exception {
        log.info("PUT company: {}", companyDTO.getName());
        return ResponseEntity.ok(companyService.create(companyDTO));
    }

    @Operation(summary = "Delete a company by name")
    @DeleteMapping("{name}")
    public ResponseEntity<?>delete(@PathVariable String name) throws Exception {
        log.info("DELETE company by name: {}", name);
        companyService.deleteCompany(name);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
