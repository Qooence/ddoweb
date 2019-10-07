package com.qooence.code.ddoweb.controller;

import com.qooence.code.usercenter.api.IAuthService;
import com.qooence.code.usercenter.common.ResultCode;
import com.qooence.code.usercenter.common.ResultJson;
import com.qooence.code.usercenter.dto.AuthUser;
import com.qooence.code.usercenter.dto.ResponseUserToken;
import com.qooence.code.usercenter.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RestController
@Api(description = "登陆注册及刷新token")
@RequestMapping("/api/v1")
public class AuthController {

    @Autowired
    private IAuthService authService;


    @PostMapping(value = "/login")
    @ApiOperation(value = "登陆", notes = "登陆成功返回token,测试管理员账号:admin,123456;用户账号：les123,admin")
    public ResultJson<ResponseUserToken> login(
            @Valid @RequestBody User user){
        final ResponseUserToken response = authService.login(user.getName(), user.getPassword());
        return ResultJson.ok(response);
    }

    @GetMapping(value = "/logout")
    @ApiOperation(value = "登出", notes = "退出登陆")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
    public ResultJson logout(HttpServletRequest request){
        String token = request.getHeader("");
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        authService.logout(token);
        return ResultJson.ok();
    }

//    @GetMapping(value = "/user")
//    @ApiOperation(value = "根据token获取用户信息", notes = "根据token获取用户信息")
//    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
//    public ResultJson getUser(HttpServletRequest request){
//        String token = request.getHeader(tokenHeader);
//        if (token == null) {
//            return ResultJson.failure(ResultCode.UNAUTHORIZED);
//        }
//        UserDetail userDetail = authService.getUserByToken(token);
//        return ResultJson.ok(userDetail);
//    }

    @PostMapping(value = "/sign")
    @ApiOperation(value = "用户注册")
    public ResultJson sign(@RequestBody AuthUser user) {
        if (StringUtils.isAnyBlank(user.getUsername(), user.getPassword())) {
            return ResultJson.failure(ResultCode.BAD_REQUEST);
        }
        AuthUser authUser = authService.register(user);
        return ResultJson.ok(authUser);
    }

    @GetMapping(value = "refresh")
    @ApiOperation(value = "刷新token")
    public ResultJson refreshAndGetAuthenticationToken(
            HttpServletRequest request){
//        String token = request.getHeader(tokenHeader);
        ResponseUserToken response = null;//authService.refresh(token);
        if(response == null) {
            return ResultJson.failure(ResultCode.BAD_REQUEST, "token无效");
        } else {
            return ResultJson.ok(response);
        }
    }
}
