package com.zys.day16.controller;

import com.zys.day16.model.User;
import com.zys.day16.param.UserParam;
import com.zys.day16.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public String index(){
        return "redirect:/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "user/userAdd";
    }

    //BindingResult拿到接口参数验证的返回信息
    //Model主要用于传递后端方法处理参数到前端
    @RequestMapping("/add")
    public String add(@Valid UserParam userParam, BindingResult result, Model model){
        //判断参数是否错误，如有错误则直接返回页面
        String errorMsg = "";
        if(result.hasErrors()){
            List<ObjectError> list = result.getAllErrors();
            for(ObjectError error : list){
                errorMsg = errorMsg+error.getCode()+"-"+error.getDefaultMessage();
            }
            model.addAttribute("errorMsg",errorMsg);
            return "user/userAdd";
        }
        //判断是否重复添加
        User u = userRepository.findByUserName(userParam.getUserName());
        if(u != null){
            model.addAttribute("errorMsg","该用户"+u+"已存在");
            return "user/userAdd";
        }
        User user = new User();
        //给User类设置值
        BeanUtils.copyProperties(userParam,user);
        user.setRegTime(new Date());
        //添加
        userRepository.save(user);
        return "redirect:/list";
    }

    @RequestMapping("/list")
    public String list(Model model,@RequestParam(value = "page",defaultValue = "0") Integer page,
                       @RequestParam(value = "size",defaultValue = "6") Integer size){
        //根据ID降序排列
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        //得到分页后的结果集页面
        Pageable pageable = PageRequest.of(page,size,sort);
        Page<User> users = userRepository.findList(pageable);
        model.addAttribute("users",users);
        return "user/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model,Long id){
        User user = userRepository.findById((long)id);
        model.addAttribute("user", user);
        return "user/userEdit";
    }

    @RequestMapping("/edit")
    public String edit(@Valid UserParam userParam,BindingResult result,Model model){
        String errorMsg = "";
        //参数校验
        if(result.hasErrors()){
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                errorMsg = errorMsg+error.getCode()+error.getDefaultMessage();
            }
            model.addAttribute("errorMsg",errorMsg);
            model.addAttribute("user",userParam);
            return "user/userEdit";
        }
        //修改用户
        User user = new User();
        BeanUtils.copyProperties(userParam,user);
        user.setRegTime(new Date());
        userRepository.save(user);
        return "redirect:/list";
    }

    @RequestMapping("/delete")
    public String delete(Long id){
        userRepository.deleteById(id);
        return "redirect:/list";
    }
}
