package com.example.entity;

/**
 * @author zhang run kai
 * @version 1.0
 * @date 2021/5/16 22:09
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Stdout {

    /**
     * {'status': 0, 'max_memory': 23328L, 'max_time': 207L}
     */
    private Integer status;
    private Long max_memory;
    private Long max_time;
}
