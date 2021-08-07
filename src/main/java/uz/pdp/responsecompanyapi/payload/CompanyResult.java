package uz.pdp.responsecompanyapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.responsecompanyapi.entity.Company;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyResult {
    private Company company;
    private boolean exist;
}
