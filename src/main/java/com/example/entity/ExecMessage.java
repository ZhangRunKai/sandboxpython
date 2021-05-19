package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhang run kai
 * @version 1.0
 * @date 2021/5/16 22:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExecMessage {

    private String error;

    private String stdout;
}
