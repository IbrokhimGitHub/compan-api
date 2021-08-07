package uz.pdp.responsecompanyapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDto {
    @NotNull(message = "name mustn't be empty")
    private String name;
    @NotNull(message = "phone number mustn't be empty")
    private String phoneNumber;
    @NotNull(message = "name mustn't be empty")
    private Integer addressId;
    @NotNull(message = "address mustn't be empty")
    private Integer departmentId;
}
