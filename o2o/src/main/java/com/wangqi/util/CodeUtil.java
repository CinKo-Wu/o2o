package com.wangqi.util;

import com.google.code.kaptcha.Constants;

import javax.servlet.http.HttpServletRequest;

public class CodeUtil {
    /**
     * 判断验证码是否正确
     * @param request
     * @return
     */
    public static boolean checkVerifyCode(HttpServletRequest request) {
        String verifyCodeExcepted = (String) request.getSession().getAttribute(
                Constants.KAPTCHA_SESSION_KEY
        );
        String verifyCodeActual = HttpServletRequestUtil.getString(request, "verifyCodeActual");
        if(verifyCodeActual == null || !verifyCodeActual.equals(verifyCodeExcepted)) {
            return false;
        }
        return true;
    }
}
