package com.nowcoder.community.controller;

import com.google.code.kaptcha.Producer;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.UserService;
import com.nowcoder.community.util.CommunityConstant;
import com.nowcoder.community.util.CommunityUtil;
import com.nowcoder.community.util.RedisKeyUtil;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
@Slf4j
public class LoginController implements CommunityConstant {

    @Autowired
    private UserService userService;

    @Autowired
    private Producer kaptchaProducer;

    @Value("${server.servlet.context-path}")
    private String contextPath;


    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 输入register路径时，传给前端的注册页面
     * @return
     */
    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String getRegisterPage(){
        log.info("跳转到注册界面");
        return "/site/register";
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String register(Model model, User user){
        Map<String, Object> map = userService.register(user);
        if(map == null || map.isEmpty()){
            model.addAttribute("msg","注册成功，我们已经向您的邮箱发送一封激活邮件，请尽快激活");
            model.addAttribute("target","/index");
            return "/site/operate-result";
        }else {
            model.addAttribute("usernameMsg",map.get("usernameMsg"));
            model.addAttribute("passwordMsg",map.get("passwordMsg"));
            model.addAttribute("emailMsg",map.get("emailMsg"));
            return "/site/register";
        }
    }

    // http://localhost:8080/community/activation/101/code
    @RequestMapping(path = "/activation/{userId}/{code}", method = RequestMethod.GET)
    public String activation(Model model, @PathVariable("userId") Integer userId, @PathVariable("code") String code){
        Integer result = userService.activation(userId, code);
        if(result == ACTIVATION_SUCCESS){
            model.addAttribute("msg","激活成功，您的账号可以正常使用了!");
            model.addAttribute("target","/login");
        }else if(result == ACTIVATION_REPEATE){
            model.addAttribute("msg","无效操作，该账号已经激活过!");
            model.addAttribute("target","/index");
        }else {
            model.addAttribute("msg","激活失败，您提供的激活码不正确!");
            model.addAttribute("target","/index");
        }

        return "/site/operate-result";
    }


    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String getLoginPage(){
        log.info("跳转到登录界面");
        return "/site/login";
    }

    /**
     * 获取图形验证码
     */
    @RequestMapping(path = "/kaptcha", method = RequestMethod.GET)
    public void getKaptcha(HttpServletResponse response){
        //生成验证码
        String text = kaptchaProducer.createText();
        BufferedImage image = kaptchaProducer.createImage(text);

        //将验证码存入session
//        session.setAttribute("kaptcha",text);

        //之前用session保存验证码，session会自动将sessionId存入cookie中发送给浏览器
        //所以不用担心验证码的归属问题

        //验证码的归属   生成一个随机字符串作为一个key，一方面作为redis的key,一方面作为cookie发送给浏览器，下次访问自动携带
        String kaptchaOwner = CommunityUtil.generateUUID();
        Cookie cookie = new Cookie("kaptchaOwner", kaptchaOwner);
        cookie.setMaxAge(60);   //验证码有效时间60s
        cookie.setPath(contextPath);
        response.addCookie(cookie);
        //验证码存入redis
        String redisKey = RedisKeyUtil.getKaptchaKey(kaptchaOwner);
        redisTemplate.opsForValue().set(redisKey, text, 60, TimeUnit.SECONDS);



        log.info("=============");
        System.out.println("==========");
        //将图片输出到浏览器
        response.setContentType("image/png");
        try {
            OutputStream os = response.getOutputStream();
            ImageIO.write(image,"png",os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @param username
     * @param password
     * @param code
     * @param rememberMe
     * @param model
     * @param response
     * @return
     */
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(String username, String password, String code, boolean rememberMe,
                        Model model, HttpServletResponse response, @CookieValue("kaptchaOwner") String kaptchaOwner){
        //先验证验证码 忽略大小写
//        String kaptcha = (String) session.getAttribute("kaptcha");

        //从cookie中获取kaptchaOwner,拼接成key
        //从redis中根据key获取value
        String kaptcha = null;
        //cookie不为空
        if(StringUtils.isNoneBlank(kaptchaOwner)){
            String redisKey = RedisKeyUtil.getKaptchaKey(kaptchaOwner);
            kaptcha = (String) redisTemplate.opsForValue().get(redisKey);
        }

        if(StringUtils.isBlank(code) || StringUtils.isBlank(kaptcha) || !kaptcha.equalsIgnoreCase(code)){
            model.addAttribute("codeMsg","验证码错误");
            return "/site/login";
        }

        //检查账号，密码
        Integer expiredSeconds = rememberMe ? REMEMBERME_EXPIRED_SECONDS :DEFAULT_EXPIRED_SECONDS;
        Map<String, Object> map = userService.login(username, password, expiredSeconds);
        System.out.println("rememberMe::::" + rememberMe);
        //登录成功，返回登录凭证ticket
        if(map.containsKey("ticket")){
            Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
            cookie.setPath(contextPath);
            cookie.setMaxAge(expiredSeconds);
            response.addCookie(cookie);
            //重定向渲染页面
            return "redirect:/index";
        }else {
            model.addAttribute("usernameMsg",map.get("usernameMsg"));
            model.addAttribute("passwordMsg",map.get("passwordMsg"));
            return "/site/login";
        }
    }


    /**
     * 退出登录
     * @param ticket   浏览器访问自动携带cookie, 服务端可以使用@CookieValue 获取指定的cookie
     * @return
     */
    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout(@CookieValue("ticket") String ticket) {
        userService.logout(ticket);
        //重定向渲染页面
        return "redirect:/login";
    }
}

