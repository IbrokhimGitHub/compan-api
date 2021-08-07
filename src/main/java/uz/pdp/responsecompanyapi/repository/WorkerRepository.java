package uz.pdp.responsecompanyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.responsecompanyapi.entity.Address;
import uz.pdp.responsecompanyapi.entity.Worker;

public interface WorkerRepository extends JpaRepository<Worker,Integer> {
}
