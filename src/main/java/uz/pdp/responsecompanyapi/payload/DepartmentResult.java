package uz.pdp.responsecompanyapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.responsecompanyapi.entity.Department;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentResult {
    private Department department;
    private boolean exist;
}
