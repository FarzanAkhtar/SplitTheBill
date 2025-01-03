package com.split.splitthebill.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserShareDto {
    @JsonProperty("user_uuid")
    private String userUuid;
    private Double share;
}
