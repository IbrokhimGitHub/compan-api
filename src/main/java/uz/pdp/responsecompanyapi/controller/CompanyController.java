package uz.pdp.responsecompanyapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.responsecompanyapi.entity.Company;
import uz.pdp.responsecompanyapi.payload.CompanyDto;
import uz.pdp.responsecompanyapi.payload.CompanyResult;
import uz.pdp.responsecompanyapi.payload.Result;
import uz.pdp.responsecompanyapi.service.CompanyService;

import javax.validation.Valid;

@RestController
@RequestMapping("api/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;
    @GetMapping
    public ResponseEntity<?> getAllByPage(@RequestParam Integer page){
        Page<Company> companyPage = companyService.getAll(page);
        return ResponseEntity.ok(companyPage);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id){
        CompanyResult one = companyService.getOne(id);
        return ResponseEntity.status(one.isExist()?200:409).body(one);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Result> delete(@PathVariable Integer id){
        Result delete = companyService.delete(id);
        return ResponseEntity.status(delete.isSuccess()?201:404).body(delete);
    }
    @PostMapping
    public ResponseEntity<?> add(@Valid  @RequestBody CompanyDto companyDto){
        Result add = companyService.add(companyDto);
        return ResponseEntity.status(add.isSuccess()?201:409).body(add);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@Valid @PathVariable Integer id,@RequestBody CompanyDto companyDto){
        Result edit = companyService.edit(id, companyDto);
        return ResponseEntity.status(edit.isSuccess()?202:404).body(edit);
    }





}
