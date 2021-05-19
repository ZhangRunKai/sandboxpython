package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author zhang run kai
 * @version 1.0
 * @date 2021/5/16 17:58
 */
@Data
@TableName(value = "oj_submit")
public class Submit {

    private Integer problemId;
    /**
     * 1:C
     * 2:C++
     * 3:Java
     * 4:python
     */
    private Integer submitType;

    /**
     * 运行Id
     */
    @TableId
    private Integer submitId;

    private Integer userId;

    private Date submitTime;

    /**
     * 运行代码
     */
    private String submitCode;

    /**
     * 发送给沙箱时表示：时间空间的限制
     * 返回时表示：运行所用的时间及空间
     */
    private Integer runTime;

    private Integer runMemory;

    /**
     * 运行结果
     */
    private Integer submitResult;

    private String errorMessage;
}
