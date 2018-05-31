package com.zdl.springdemo.Controller;

import com.zdl.springdemo.config.WebSecurityConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController {


    /**
     * 登录成功的指定跳转页面 WebSecurityConfig  里面控制
     * @param accunt
     * @param model
     * @return
     */
    @GetMapping("/")
    public String index(@SessionAttribute(WebSecurityConfig.SESSION_KEY) String accunt, Model model) {
        model.addAttribute("name", accunt);
        return "index";
    }


    /**
     * 加载登录界面
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }



    @PostMapping("/loginPost")
    public @ResponseBody Map<String, Object> loginpost(String account, String password, HttpSession session) {
        Map<String, Object> map = new HashMap<>();


        if (!"111111".equals(password)) {

            map.put("success", false);
            map.put("msg", "密码错误");
            return map;
        }
        // 设置session
        session.setAttribute(WebSecurityConfig.SESSION_KEY,account);
        map.put("success", true);
        map.put("msg", "登录成功");
        return map;
    }

}
