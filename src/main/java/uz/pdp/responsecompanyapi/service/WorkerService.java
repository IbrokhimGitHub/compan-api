package uz.pdp.responsecompanyapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.responsecompanyapi.entity.Address;
import uz.pdp.responsecompanyapi.entity.Department;
import uz.pdp.responsecompanyapi.entity.Worker;
import uz.pdp.responsecompanyapi.payload.Result;
import uz.pdp.responsecompanyapi.payload.WorkerDto;
import uz.pdp.responsecompanyapi.payload.WorkerResult;
import uz.pdp.responsecompanyapi.repository.AddressRepository;
import uz.pdp.responsecompanyapi.repository.DepartmentRepository;
import uz.pdp.responsecompanyapi.repository.WorkerRepository;

import java.util.Optional;

@Service
public class WorkerService {
    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    public WorkerResult getOne(Integer id){
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent()) {
            return new WorkerResult(new Worker(),false);
        }
        return new WorkerResult(optionalWorker.get(),true);


    }
    public Page<Worker> getAll(int page){
        Pageable pageable= PageRequest.of(page,10);
        Page<Worker> all = workerRepository.findAll(pageable);
        return all;
    }
    public Result delete(Integer id){
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent()) {
            return new Result("cant find worker with id "+id,false);
        }
        workerRepository.deleteById(id);
        return new Result("deleted ",true);
    }
    public  Result add(WorkerDto workerDto){
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (!optionalDepartment.isPresent()) {
            return new Result("cant find department with id "+workerDto.getDepartmentId(),false);
        }
        Optional<Address> optionalAddress = addressRepository.findById(workerDto.getAddressId());
        if (optionalAddress.isPresent()) {
            return new Result("cant find address with id "+workerDto.getAddressId(),false);
        }
        Worker worker=new Worker();
        worker.setAddress(optionalAddress.get());
        worker.setDepartment(optionalDepartment.get());
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        workerRepository.save(worker);
        return new Result("new worker is saved",true);

    }
    public Result edit(Integer id,WorkerDto workerDto){
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (optionalWorker.isPresent()) {
            return new Result("cant find such worker to edit",false);
        }
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (!optionalDepartment.isPresent()) {
            return new Result("cant find department with id "+workerDto.getDepartmentId(),false);
        }
        Optional<Address> optionalAddress = addressRepository.findById(workerDto.getAddressId());
        if (optionalAddress.isPresent()) {
            return new Result("cant find address with id "+workerDto.getAddressId(),false);
        }
        Worker worker = optionalWorker.get();
        worker.setAddress(optionalAddress.get());
        worker.setDepartment(optionalDepartment.get());
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        workerRepository.save(worker);
        return new Result("worket is edited",true);


    }



}
