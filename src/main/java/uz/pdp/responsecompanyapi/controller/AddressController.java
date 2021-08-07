package uz.pdp.responsecompanyapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.responsecompanyapi.entity.Address;
import uz.pdp.responsecompanyapi.payload.AddressResult;
import uz.pdp.responsecompanyapi.payload.Result;
import uz.pdp.responsecompanyapi.service.AddressService;

import javax.validation.Valid;

@RestController
@RequestMapping
public class AddressController {
    @Autowired
    AddressService addressService;
    @GetMapping
    public ResponseEntity<?> getAllByPage(@RequestParam Integer page){
        Page<Address> addressPage = addressService.getAll(page);
        return ResponseEntity.ok(addressPage);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id){
        AddressResult one = addressService.getOne(id);
        return ResponseEntity.status(one.isExist()?200:409).body(one);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Result> delete(@PathVariable Integer id){
        Result delete = addressService.delete(id);
        return ResponseEntity.status(delete.isSuccess()?201:404).body(delete);
    }
    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody Address address){
        Result add = addressService.add(address);
        return ResponseEntity.status(add.isSuccess()?201:409).body(add);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@Valid @PathVariable Integer id,@RequestBody Address address){
        Result edit = addressService.edit(id, address);
        return ResponseEntity.status(edit.isSuccess()?202:404).body(edit);
    }

}
