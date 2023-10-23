package models.ResponseModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.qameta.allure.internal.shadowed.jackson.databind.annotation.JsonAppend;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColorsResponse {
    public DataColorResponse data;
    public SupportResponse support;
}
