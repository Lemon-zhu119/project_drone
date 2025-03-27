package com.ruoyi.api.interceptor;

import com.ruoyi.common.core.domain.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.ruoyi.framework.web.service.TokenService;
import io.jsonwebtoken.Claims;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;

    // 定义白名单
    private static final List<String> WHITELISTED_PATHS = Arrays.asList(
            "/api/user/login",  // 登录接口
            "/api/user/register", // 注册接口
            "/api/user/getPassword", // 获取密码状态接口
            "/api/code/" // 发送手机验证码接口
            // 可以添加更多的白名单路径
    );

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        // 检查请求路径是否在白名单中
        for (String path : WHITELISTED_PATHS) {
            if (requestURI.startsWith(path)) {
                return true; // 如果在白名单中，直接放行
            }
        }

        // 获取用户身份信息
        LoginUser loginUser = tokenService.getLoginUser(request);
        
        if (loginUser == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: Invalid token");
            return false; // 拦截请求
        }

        // 如果需要，可以将 loginUser 存储在请求属性中，以便后续处理
        request.setAttribute("loginUser", loginUser);

        return true; // 继续处理请求
    }

    // private boolean isValidToken(String token) {
    //     if (token == null || token.isEmpty()) {
    //         return false; // Token 为空
    //     }

    //     try {
    //         // 解析 token，获取 claims
    //         Claims claims = tokenService.parseToken(token); // 假设 TokenService 中有 parseToken 方法

    //         // 检查 token 是否过期
    //         Date expiration = claims.getExpiration();
    //         if (expiration.before(new Date())) {
    //             return false; // Token 已过期
    //         }

    //         // 这里可以添加其他验证逻辑，例如检查 token 是否在黑名单中

    //         return true; // Token 有效
    //     } catch (Exception e) {
    //         return false; // 解析失败，Token 无效
    //     }
    // }
}
