package com.split.splitthebill.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailedDto {
    private String name;
    @JsonProperty("phone_number")
    private String phoneNumber;
    private String password;
    private String uuid;
}
