package com.example.core;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.JudgeResult;
import com.example.entity.Submit;
import com.example.mapper.SubmitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * @author zhang run kai
 * @version 1.0
 * @date 2021/5/16 23:15
 */
@Service
public class JudgeThread implements Runnable {


    private SubmitMapper submitMapper;

    private Submit submit;

    public JudgeThread(){};
    public JudgeThread(SubmitMapper submitMapper,Submit submit){
        this.submit = submit;
        this.submitMapper = submitMapper;
    }

    @Override
    public void run() {
        JudgeResult judge = Judge.judge(submit);
        submit.setSubmitTime(new Date());
        submit.setRunTime(judge.getTimeUsed());
        submit.setRunMemory(judge.getMemoryUsed());
        submit.setSubmitResult(judge.getStatus());
        System.out.println(submit.toString());
        int submit_id = submitMapper.update(submit, new QueryWrapper<Submit>().eq("submit_id", submit.getSubmitId()));
        System.out.println("i:"+submit_id);
    }
}
