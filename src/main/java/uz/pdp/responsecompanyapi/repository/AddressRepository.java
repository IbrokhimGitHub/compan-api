package uz.pdp.responsecompanyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.responsecompanyapi.entity.Address;

public interface AddressRepository extends JpaRepository<Address,Integer> {

}
