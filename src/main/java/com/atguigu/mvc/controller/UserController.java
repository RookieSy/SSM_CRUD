package com.atguigu.mvc.controller;

import com.atguigu.mybatis.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * Author:Rookiesy
 * Date:2022/05/14/13:45
 * Description： RESTFUL就是使用同一个请求路径，不同的请求方式，执行不同的操作
 *
 * /**
 *      * 使用RESTFul模拟用户资源的增删改查
 *      * /user    GET     查询所有用户信息
 *      * /user/id    GET     根据用户id查询用户信息
 *      * /user    POST     添加用户信息
 *      * /user/id    DELETE     删除用户信息
 *      * /user    PUT     修改用户信息
 *
 */
@Service
@Controller
public class UserController{

    @Resource
    private UserImp userImp = null;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getAll(HttpSession session){
        List<User> list = userImp.getAllUser();
        session.setAttribute("userList", list);
        return "user";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String getUserById(
            Integer id ,
            HttpSession session){

        User userByid = userImp.getUserByid(id);
        session.setAttribute("user", userByid);
        return "UserByid";

    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String insertUser(User user){

        int i = userImp.insertUser(user);
        System.out.println(i);
        return "success";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public String updateUser(User user,
                             Integer id
    ){
        List<User> list = userImp.getAllUser();
        int size = list.size();
        System.out.println(size);
        int i = userImp.updateUser(user, id);
        if(size>=id){
            System.out.println(i);
            return "success";

        }  else {
            return "fail";
        }

    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public String deleteUserById(
            Integer id,
            HttpSession session) {
        int i = userImp.deleteUserById(id);
        System.out.println(i);
        if (i == 1) {
            return "success";
        } else {

        return "fail";
        }
    }
}
