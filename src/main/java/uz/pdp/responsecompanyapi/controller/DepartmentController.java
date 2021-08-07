package uz.pdp.responsecompanyapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.responsecompanyapi.entity.Department;
import uz.pdp.responsecompanyapi.payload.DepartmentDto;
import uz.pdp.responsecompanyapi.payload.DepartmentResult;
import uz.pdp.responsecompanyapi.payload.Result;
import uz.pdp.responsecompanyapi.service.DepartmentService;

import javax.validation.Valid;

@RestController
@RequestMapping
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;
    @GetMapping
    public ResponseEntity<?> getAllByPage(@RequestParam Integer page){
        Page<Department> departmentPage = departmentService.getAll(page);
        return ResponseEntity.ok(departmentPage);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id){
        DepartmentResult one = departmentService.getOne(id);
        return ResponseEntity.status(one.isExist()?200:409).body(one);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Result> delete(@PathVariable Integer id){
        Result delete = departmentService.delete(id);
        return ResponseEntity.status(delete.isSuccess()?201:404).body(delete);
    }
    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody DepartmentDto departmentDto){
        Result add = departmentService.add(departmentDto);
        return ResponseEntity.status(add.isSuccess()?201:409).body(add);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@Valid @PathVariable Integer id,@RequestBody DepartmentDto departmentDto){
        Result edit = departmentService.edit(id, departmentDto);
        return ResponseEntity.status(edit.isSuccess()?202:404).body(edit);
    }

}

