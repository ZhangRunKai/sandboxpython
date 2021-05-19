package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author zhang run kai
 * @version 1.0
 * @date 2021/5/16 17:58
 */
@Data
@ToString
@TableName(value = "oj_problem")
public class Problem {
    /**
     * 题目ID,主键
     */
    @TableId(value = "problem_id", type = IdType.AUTO)
    private Integer problemId;

    private Integer userId;

    /**
     * 题目主键
     */
    private String problemTitle;

    /**
     * 题目作者
     */
    private String problemAuthor;

    /**
     * 题目内容
     */
    private String problemContent;

    /**
     * 题目运行时间限制
     */
    private String javaTimeLimited;

    /**
     * 题目内存限制
     */
    private String javaSpaceLimited;
    /**
     * 题目运行时间限制
     */
    private String otherTimeLimited;

    /**
     * 题目内存限制
     */
    private String otherSpaceLimited;

    /**
     * 题目权限控制
     * 0:公开
     * 1：个人呢
     */
    private Integer problemPower;

    /**
     * 题目难度
     */
    private Integer problemDegree;

    /**
     * 提交数
     */
    private Integer problemNum;

    /**
     * 通过人数
     */
    private Integer problemAccept;


}
