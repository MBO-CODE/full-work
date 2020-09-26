package com.sda.spring.controller;

import com.sda.spring.dto.CompanyInfoDto;
import com.sda.spring.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Controller
public class CompanyControllerThymeLeaf {

    private final CompanyService companyService;

    @Autowired
    public CompanyControllerThymeLeaf(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RequestMapping(value = "/showAll", method = RequestMethod.GET)
    public String getAllCompanies(Model model) {
        List<CompanyInfoDto> companyList = companyService.getAllCompanies();
        model.addAttribute("list", companyList);
        return "companyTable";
    }


}
