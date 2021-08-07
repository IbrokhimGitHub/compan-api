package uz.pdp.responsecompanyapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
    @NotNull(message = "name mustn't be empty")
    private String name;
    @NotNull(message = "company mustn't be empty")
    private Integer companyId;
}
