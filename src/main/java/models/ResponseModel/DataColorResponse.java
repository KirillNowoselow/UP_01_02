package models.ResponseModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataColorResponse {
    public Integer id;
    public String name;
    public Integer year;
    public String color;
    @JsonProperty("pantone_value")
    public String pantoneValue;
}
