package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhang run kai
 * @version 1.0
 * @date 2021/5/16 22:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JudgeResult {

    private Integer submitId;
    private Integer status;
    private Integer timeUsed;
    private Integer memoryUsed;
    private String errorMessage;

}

