package uz.pdp.responsecompanyapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.responsecompanyapi.entity.Address;
import uz.pdp.responsecompanyapi.entity.Company;
import uz.pdp.responsecompanyapi.payload.CompanyDto;
import uz.pdp.responsecompanyapi.payload.CompanyResult;
import uz.pdp.responsecompanyapi.payload.Result;
import uz.pdp.responsecompanyapi.repository.AddressRepository;
import uz.pdp.responsecompanyapi.repository.CompanyRepository;

import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    AddressRepository addressRepository;
    public CompanyResult getOne(Integer id){
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent()) {
            return new CompanyResult(new Company(),false);
        }
        return new CompanyResult(optionalCompany.get(),true);


    }
    public Page<Company> getAll(int page){
        Pageable pageable= PageRequest.of(page,10);
        Page<Company> all = companyRepository.findAll(pageable);
        return all;
    }
    public Result delete(Integer id){
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent()) {
            return new Result("cant find company with id "+id,false);
        }
        companyRepository.deleteById(id);
        return new Result("deleted ",true);
    }
    public Result add(CompanyDto companyDto){
        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
        if (!optionalAddress.isPresent()) {
            return new Result("cant find such address",false);
        }
        Company company=new Company();
        company.setAddress(optionalAddress.get());
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        Company savedCompany = companyRepository.save(company);
        return new Result("company saved with id = "+savedCompany.getId(),true);


    }
    public Result edit(Integer id,CompanyDto companyDto){
        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
        if (!optionalAddress.isPresent()) {
            return new Result("cant find such address",false);
        }
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent()) {
            return new Result("cant find such company",false);
        }
        Company company = optionalCompany.get();
        company.setAddress(optionalAddress.get());
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        Company savedCompany = companyRepository.save(company);
        return new Result("company  with id = "+savedCompany.getId()+" edited",true);

    }



}
