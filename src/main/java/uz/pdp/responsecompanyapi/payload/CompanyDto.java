package uz.pdp.responsecompanyapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    @NotNull(message = "company name mustn't be empty")
    private String corpName;
    @NotNull(message = "director name mustn't be empty")
    private String directorName;
    @NotNull(message = "address mustn't be empty")
    private Integer addressId;
}
