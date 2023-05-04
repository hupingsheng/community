package com.nowcoder.community.controller.advice;


import com.nowcoder.community.util.CommunityUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice(annotations = Controller.class)
public class ExceptionAdvice {

    @ExceptionHandler({Exception.class})
    public void handleException(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {


        String xRequestdWith = request.getHeader("x-requested-with");
        if("XMLHttpRequest".equals(xRequestdWith)){
            response.setContentType("application/plain; charset=utf-8");

                PrintWriter writer = response.getWriter();
                writer.write(CommunityUtil.getJsonString(1, "服务器异常!"));
        }else {
            response.sendRedirect(request.getContextPath() + "/error");
        }

    }

}
