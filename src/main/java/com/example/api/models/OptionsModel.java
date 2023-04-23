package com.example.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionsModel {
    private String Field;
    private String Type;
    private String Null;
    private String key;
    private String Default;
    private String Extra;
}
