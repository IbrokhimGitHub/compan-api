package uz.pdp.responsecompanyapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.responsecompanyapi.entity.Company;
import uz.pdp.responsecompanyapi.entity.Department;
import uz.pdp.responsecompanyapi.payload.DepartmentDto;
import uz.pdp.responsecompanyapi.payload.DepartmentResult;
import uz.pdp.responsecompanyapi.payload.Result;
import uz.pdp.responsecompanyapi.repository.AddressRepository;
import uz.pdp.responsecompanyapi.repository.CompanyRepository;
import uz.pdp.responsecompanyapi.repository.DepartmentRepository;

import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    CompanyRepository companyRepository;
    public DepartmentResult getOne(Integer id){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent()) {
            return new DepartmentResult(new Department(),false);
        }
        return new DepartmentResult(optionalDepartment.get(),true);


    }
    public Page<Department> getAll(int page){
        Pageable pageable= PageRequest.of(page,10);
        Page<Department> all = departmentRepository.findAll(pageable);
        return all;
    }
    public Result delete(Integer id){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent()) {
            return new Result("cant find department with id "+id,false);
        }
        departmentRepository.deleteById(id);
        return new Result("deleted ",true);
    }
    public Result add(DepartmentDto departmentDto){
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (!optionalCompany.isPresent()) {
            return new Result("cant find such company",false);
        }
        Department department=new Department();
        department.setCompany(optionalCompany.get());
        department.setName(departmentDto.getName());
        Department savedDepartment = departmentRepository.save(department);
        return new Result("new department saved with id "+savedDepartment.getId(),true);
    }
    public Result edit(Integer id,DepartmentDto departmentDto){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent()) {
            return new Result("cant find department with id "+id,false);
        }
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (!optionalCompany.isPresent()) {
            return new Result("cant find such company",false);
        }
        Department department = optionalDepartment.get();
        department.setCompany(optionalCompany.get());
        department.setName(departmentDto.getName());
        Department editedDepartment = departmentRepository.save(department);
        return new Result("department  with id "+editedDepartment.getId()+" edited",true);


    }



}
