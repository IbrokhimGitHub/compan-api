package uz.pdp.responsecompanyapi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.responsecompanyapi.entity.Address;
import uz.pdp.responsecompanyapi.payload.AddressResult;
import uz.pdp.responsecompanyapi.payload.Result;
import uz.pdp.responsecompanyapi.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;
    public AddressResult getOne(Integer id){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent()) {
            return new AddressResult(new Address(),false);
        }
        return new AddressResult(optionalAddress.get(),true);


    }
    public Page<Address> getAll(int page){
        Pageable pageable= PageRequest.of(page,10);
        Page<Address> all = addressRepository.findAll(pageable);
        return all;
    }
    public Result delete(Integer id){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent()) {
            return new Result("cant find address with id "+id,false);
        }
        addressRepository.deleteById(id);
        return new Result("deleted ",true);
    }

    public Result add(Address address){
        Address save = addressRepository.save(address);
        return new Result("Address saved with id "+save.getId(),true);
    }
    public Result edit(Integer id,Address addressIntro){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();
            address.setHomeNumber(addressIntro.getHomeNumber());
            address.setStreet(addressIntro.getStreet());
            addressRepository.save(address);
            return new Result("Address edited with id "+address.getId(),true);
        }
        return new Result("cant find address with id "+id,false);
    }





}
