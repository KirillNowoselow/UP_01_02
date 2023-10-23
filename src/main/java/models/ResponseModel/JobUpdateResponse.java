package models.ResponseModel;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobUpdateResponse {
    public String name;
    public String job;
    public Date updatedAt;
}
