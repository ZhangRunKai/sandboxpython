package com.example.core;

import com.alibaba.fastjson.JSON;
import com.example.entity.*;
import com.example.util.ExecutorUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * @author zhang run kai
 * @version 1.0
 * @date 2021/5/16 18:18
 */
public class Judge {
    private static String inputDataPath = "/home/judge";
    public static JudgeResult judge(Submit submit) {
        JudgeResult result = new JudgeResult();
        result.setSubmitId(submit.getSubmitId());
        String path = "/home/test1/" + submit.getSubmitId();
        File file = new File(path);
        file.mkdirs();
        try {
            createFile(submit.getSubmitType(), path,
                    submit.getSubmitCode());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(32);
            System.out.println(e.getMessage());
            result.setStatus(8);
            ExecutorUtil.exec("rm -rf " + path);
            return result;
        }
        //编译代码
        String message = complie(submit.getSubmitType(), path);
        System.out.println("-----------------");
        System.out.println(message);
        System.out.println("-----------------");
//        System.exit(0);
//        return null;
        if (message != null) {
            result.setStatus(7);
            result.setErrorMessage(message);
            ExecutorUtil.exec("rm -rf " + path);
            return result;
        }
        //chmod -R 755 path
        ExecutorUtil.exec("chmod -R 755 " + path);
        //判题
        String process = process(submit.getSubmitId(), path);
        String judge_data = inputDataPath+ "/" + submit.getProblemId();
        String cmd = "python judge.py " + process + " " + judge_data + " " + path + " " + submit.getRunTime() + " " + submit.getRunMemory();
        parseToResult(cmd, result);
        ExecutorUtil.exec("rm -rf " + path);
        return result;
    }

    private static void createFile(int submitType, String path, String code) throws Exception {
        String filename = "";
        switch (submitType) {
            case 1:
                filename = "main.c";
                break;
            case 2:
                filename = "main.cpp";
                break;
            case 3:
                filename = "Main.java";
                break;
            case 4:
                filename = "main.py";
                break;
        }
        File file = new File(path + "/" + filename);
        file.createNewFile();
        OutputStream output = new FileOutputStream(file);
        PrintWriter writer = new PrintWriter(output);
        writer.print(code);
        writer.close();
        output.close();
        System.out.println(80);
    }

    private static String complie(int compilerId, String path) {
        /**
         *  '1': 'gcc','g++', '3': 'java', '4': 'python',
         */
        String cmd = "";
        switch (compilerId) {
            case 1:
                cmd = "gcc " + path + "/main.c -o " + path + "/main";
                break;
            case 2:
                cmd = "g++ " + path + "/main.cpp -o " + path + "/main";
                break;
            case 3:
                cmd = "javac " + path + "/Main.java";
                break;
            case 4:
                cmd = "python3 -m py_compile " + path + "/main.py";
                break;
        }
        return ExecutorUtil.exec(cmd).getError();
    }


    /**
     *
     * @param compileId 语言
     * @param path 路径
     * @return
     */
    private static String process(int compileId, String path) {
        switch (compileId) {
            case 1:
                return path + "/main";
            case 2:
                return path + "/main";
            case 3:
                return "java~~~-classpath~~~" + path + "~~~Main";
            case 4:
                return "python3" + path + "/__pycache__/" + "python_cacheName";
        }
        return null;
    }

    private static void parseToResult(String cmd, JudgeResult result) {
        ExecMessage exec = ExecutorUtil.exec(cmd);
        if (exec.getError() != null) {
            result.setStatus(5);
            result.setErrorMessage(exec.getError());
        } else {
            System.out.println("exec.getStdout():"+exec.getStdout().toString());
            Stdout out = JSON.parseObject(exec.getStdout(), Stdout.class);
            result.setStatus(out.getStatus());
            result.setTimeUsed(out.getMax_time().intValue());
            result.setMemoryUsed(out.getMax_memory().intValue());
            result.setErrorMessage("没有错误");
        }
    }
}
