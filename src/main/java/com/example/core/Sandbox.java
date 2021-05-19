package com.example.core;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.core.JudgeThread;
import com.example.entity.JudgeResult;
import com.example.entity.Submit;
import com.example.mapper.SubmitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author: zhangrunkai
 * @Date: 2021/05/17/11:24
 */
@Service
public class Sandbox {

    @Autowired
    private SubmitMapper submitMapper;

    private static ExecutorService judgeThreadPool = Executors.newFixedThreadPool(5);

    public void query() {
        while(true){
            try {
                List<Submit> submitResult = submitMapper.selectList(new QueryWrapper<Submit>().eq("submit_result", 9));
                System.out.println(submitResult);
                for(Submit submit:submitResult){
                    JudgeThread judgeThread = new JudgeThread(submitMapper,submit);
                    judgeThreadPool.execute(judgeThread);
                }
                Thread.sleep(3000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
