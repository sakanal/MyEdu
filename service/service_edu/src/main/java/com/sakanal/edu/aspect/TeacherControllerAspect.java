package com.sakanal.edu.aspect;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sakanal.edu.entity.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class TeacherControllerAspect {
//    @Around("execution(* com.sakanal.edu.controller.TeacherController.page*(..))")
    public Object pageCurrentAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("开始对页码进行判断");

        Object[] args = joinPoint.getArgs();

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] parameterNames = methodSignature.getParameterNames();

        for (int i = 0; i < args.length; i++) {
            if (parameterNames[i].contains("current")){
                log.info("获取到了current参数位置");
                Object arg = args[i];
                if (arg instanceof Integer){
                    log.info("页码为Integer类型");
                    if((int)arg <= 0){
                        arg=1;
                    }
                }
                if (arg instanceof String)
                    arg=1;
            }
        }

        return joinPoint.proceed(args);
    }

//    @Around("execution(* com.sakanal.edu.controller.TeacherController.updateById(..))")
    public Object updateArgsJudgment(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            if (arg instanceof Teacher) {
                log.info("类型匹配成功");
                Teacher teacher = JSONObject.toJavaObject(JSONObject.parseObject(JSONObject.toJSONString(arg)), Teacher.class);
                if ("".equals(teacher.getName())) {
                    teacher.setName(null);
                }
                if ("".equals(teacher.getIntro())) {
                    teacher.setIntro(null);
                }
                if ("".equals(teacher.getCareer())) {
                    teacher.setCareer(null);
                }
                if ("".equals(teacher.getAvatar())) {
                    teacher.setAvatar(null);
                }
                arg=teacher;
            }
        }

        return joinPoint.proceed(args);
    }
}
