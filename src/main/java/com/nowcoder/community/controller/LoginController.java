package com.nowcoder.community.controller;

import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.UserService;
import com.nowcoder.community.util.CommunityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * 注册登录控制器
 * @author 尚郑
 */
@Controller
public class LoginController implements CommunityConstant {

    @Autowired
    private UserService userService;

    /**
     * 注册按钮
     * @return 注册页面
     */
    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String getRegisterPage(){
        return "/site/register";
    }

    /**
     * 登录功能
     * @return 登录页面
     */
    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String getLoginPage(){
        return "/site/login";
    }


    /**
     * 表单提交的注册操作
     * @param user 注册用户
     * @return 注册提示信息
     */
    @RequestMapping(path = "/register",method = RequestMethod.POST)
    public String register(Model model, User user){
        Map<String, Object> map = userService.register(user);
        if(map == null || map.isEmpty()){
            //注册成功
            model.addAttribute("msg","注册成功,我们已经向你的邮箱发送一封邮件,请尽快激活！");
            model.addAttribute("target","/index");
            return "/site/operate-result";
        }else {
            //注册失败
            model.addAttribute("usernameMsg",map.get("usernameMsg"));
            model.addAttribute("passwordMsg",map.get("passwordMsg"));
            model.addAttribute("emailMsg",map.get("emailMsg"));
            return "/site/register";
        }
    }

    /**
     * 激活状态
     */
    // http://localhost:8080/community/activation/101/code
    @RequestMapping(path = "/activation/{userId}/{code}",method = RequestMethod.GET)
    public String activation(Model model, @PathVariable("userId") int userId,@PathVariable("code") String code ){
        int result = userService.activation(userId, code);
        if(result == ACTIVATION_SUCCESS){
            model.addAttribute("msg","您的账号已经激活成功,可以正常使用了!");
            model.addAttribute("target","/login");
        }else if(result == ACTIVATION_REPEAT){
            model.addAttribute("msg","无效操作,该账号已经正常使用!");
            model.addAttribute("target","/index");
        }else {
            model.addAttribute("msg","激活失败,您的激活码不正确!");
            model.addAttribute("target","/index");
        }
        return "/site/operate-result";
    }
}
