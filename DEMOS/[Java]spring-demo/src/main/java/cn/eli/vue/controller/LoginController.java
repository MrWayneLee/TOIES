package cn.eli.vue.controller;

import cn.eli.vue.api.CommonResult;
import cn.eli.vue.api.IErrorCode;
import cn.eli.vue.entity.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public CommonResult login(@RequestBody User user) {
        if (user.getUsername().equals("admin") && user.getPassword().equals("123456"))
            return CommonResult.success("admin");
        else
            return CommonResult.validateFailed();
    }
}
