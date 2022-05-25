package com.lenokia.appointment.user.api;

import com.lenokia.appointment.common.utils.AuthContextHolder;
import com.lenokia.appointment.user.service.UserInfoService;
import com.lenokia.appointment.user.util.IpUtil;
import com.lenokia.yygh.model.user.UserInfo;
import com.lenokia.yygh.vo.user.LoginVo;
import com.lenokia.yygh.vo.user.UserAuthVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.lenokia.appointment.common.Result.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserInfoApiController {

    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation(value = "会员登录")
    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo, HttpServletRequest request) {
        loginVo.setIp(IpUtil.getIpAddr(request));
        Map<String, Object> info = userInfoService.login(loginVo);
        return Result.ok(info);
    }

    //用户认证接口
    @PostMapping("auth/userAuth")
    public Result userAuth(@RequestBody UserAuthVo userAuthVo, HttpServletRequest request) {
        //传递两个参数，第一个参数用户id，第二个参数认证数据vo对象
        // Long userId = AuthContextHolder.getUserId(request);
        userInfoService.userAuth(1L,userAuthVo);
        return Result.ok();
    }

    //获取用户id信息接口
    @GetMapping("auth/getUserInfo")
    public Result getUserInfo(HttpServletRequest request) {
        // String userId = AuthContextHolder.getUserName(request);
        UserInfo userInfo = userInfoService.getById(1L);
        return Result.ok(userInfo);
    }


}

