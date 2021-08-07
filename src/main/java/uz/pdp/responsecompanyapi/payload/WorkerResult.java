package uz.pdp.responsecompanyapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.responsecompanyapi.entity.Worker;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerResult {
    private Worker worker;
    private boolean exist;
}
