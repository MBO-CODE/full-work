package com.sda.spring.service.impl;

import com.sda.spring.components.CompanyMapper;
import com.sda.spring.dto.CompanyCreateDto;
import com.sda.spring.dto.CompanyInfoDto;
import com.sda.spring.exception.CompanyNotFoundException;
import com.sda.spring.model.Company;
import com.sda.spring.repository.CompanyRepository;
import com.sda.spring.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository repository;
    private final CompanyMapper companyMapper;

    @Autowired
    public CompanyServiceImpl(CompanyRepository repository, CompanyMapper companyMapper) {
        this.repository = repository;
        this.companyMapper = companyMapper;
    }

    @Override
    public CompanyInfoDto create(CompanyCreateDto companyCreateDto, String userName) {
        return companyMapper.toDto(repository.save(companyMapper.toEntity(companyCreateDto, userName)));
    }

    @Override
    public List<CompanyInfoDto> getAllCompanies(Integer pageNo, Integer pageSize, String sortBy) {

        Pageable pageable = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
        Page<Company> pageResult = repository.findAll(pageable);
        return companyMapper.toDtoList(pageResult.getContent());
    }

    @Override
    public List<CompanyInfoDto> getAllCompanies() {
        return (List)repository.findAll();
    }


    @Override
    public void populateDb(List<Company> companies) {
        repository.saveAll(companies);
    }

    @Override
    public CompanyInfoDto findById(Integer id) {
        Optional<Company> company = repository.findById(id);
        if(!company.isPresent()) {
            throw new CompanyNotFoundException("No company with ID:" + id + "exists.");
        } else {
            return companyMapper.toDto(company.get());
        }
    }


}
