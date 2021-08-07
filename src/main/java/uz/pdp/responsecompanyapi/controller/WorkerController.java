package uz.pdp.responsecompanyapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.responsecompanyapi.entity.Worker;
import uz.pdp.responsecompanyapi.payload.WorkerDto;
import uz.pdp.responsecompanyapi.payload.WorkerResult;
import uz.pdp.responsecompanyapi.payload.Result;
import uz.pdp.responsecompanyapi.service.WorkerService;

import javax.validation.Valid;

@RestController
@RequestMapping("api/worker")
public class WorkerController {
    @Autowired
    WorkerService workerService;
    @GetMapping
    public ResponseEntity<?> getAllByPage(@RequestParam Integer page){
        Page<Worker> workerPage = workerService.getAll(page);
        return ResponseEntity.ok(workerPage);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id){
        WorkerResult one = workerService.getOne(id);
        return ResponseEntity.status(one.isExist()?200:409).body(one);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Result> delete(@PathVariable Integer id){
        Result delete = workerService.delete(id);
        return ResponseEntity.status(delete.isSuccess()?201:404).body(delete);
    }
    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody WorkerDto workerDto){
        Result add = workerService.add(workerDto);
        return ResponseEntity.status(add.isSuccess()?201:409).body(add);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@Valid@PathVariable Integer id,@RequestBody WorkerDto workerDto){
        Result edit = workerService.edit(id, workerDto);
        return ResponseEntity.status(edit.isSuccess()?202:404).body(edit);
    }





}

