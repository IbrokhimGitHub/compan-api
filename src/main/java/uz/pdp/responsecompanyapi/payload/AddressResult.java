package uz.pdp.responsecompanyapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import uz.pdp.responsecompanyapi.entity.Address;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResult {
    private Address address;
    private boolean exist;
}
