package com.zys.day6.controller;

import com.zys.day6.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Thymeleaf 表达式
 * 变量表达式：${...} ex:${session.user.name}
 * 选择或星号表达式：*{...} ex:*{customer.name},指的是th:object中的object的属性,在父标签下表达。而不是整个上下文
 * 文字国际化表达式：#{...} ex:#{header.address.city},指的是从外部文件中获取信息，由key索引value
 * URL 表达式：@{...} ex:@{/order/list}
 * 片段表达式：~{...} ex:<div th:insert="~{commons :: main}">...</div>
 */
@Controller
public class ExampleController {

    @RequestMapping("/")
    public String index(ModelMap map){
        map.addAttribute("message","https://github.com/dcfwow/spring-boot-study/tree/master");
        return "hello";
    }

    @RequestMapping("/string")
    public String string(ModelMap map){
        map.addAttribute("username","dcfwow");
        return "string";
    }

    @RequestMapping("/if")
    public String ifunless(ModelMap map){
        map.addAttribute("flag","yes");
        return "if";
    }

    @RequestMapping("/list")
    public String list(ModelMap map) {
        map.addAttribute("users", getUserList());
        return "list";
    }

    @RequestMapping("/url")
    public String url(ModelMap map){
        map.addAttribute("type","link");
        map.addAttribute("pageId","spring cloud/2017/09/11");
        map.addAttribute("img","http://www.ityouknow.com/assets/images/neo.jpg");
        return "url";
    }

    @RequestMapping("/eq")
    public String eq(ModelMap map){
        map.addAttribute("name","dcfwow");
        map.addAttribute("age",23);
        map.addAttribute("flag","yes");
        return "eq";
    }

    @RequestMapping("/switch")
    public String switchcase(ModelMap map){
        map.addAttribute("sex","man");
        return "switch";
    }

    @RequestMapping("/inline")
    public String inline(ModelMap map){
        map.addAttribute("username","dcfwow");
        return "inline";
    }

    @RequestMapping("/object")
    public String object(HttpServletRequest request){
        request.setAttribute("request","i am request");
        request.getSession().setAttribute("session","i am session");
        return "object";
    }

    @RequestMapping("/utility")
    public String utility(ModelMap map){
        map.addAttribute("userName","dcfwow");
        map.addAttribute("users",getUserList());
        map.addAttribute("count",12);
        map.addAttribute("date",new Date());
        return "utility";
    }

    private List<User> getUserList(){
        List<User> list=new ArrayList<User>();
        User user1=new User("大牛",12,"123456");
        User user2=new User("小牛",6,"123563");
        User user3=new User("纯洁的微笑",66,"666666");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        return  list;
    }
}
