package com.atguigu.mvc.controller;


import com.atguigu.mybatis.Utils.SqlSessionUtils;
import com.atguigu.mybatis.mapper.UserMapper;
import com.atguigu.mybatis.pojo.User;
import com.atguigu.mybatis.pojo.UserExample;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServlet;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * @Author: RookieSy
 * @Date: 2022/5/9  10:55
 * @Description:
 *
 */
@Repository
public class UserImp {


    SqlSession sqlSession = SqlSessionUtils.getSqlSession();
    UserMapper mapper = sqlSession.getMapper(UserMapper.class);

    //查询所有用户信息

    public List<User> getAllUser() {
        List<User>  list = mapper.selectByExample(null);
//        for (User user : list) {
//            System.out.println(user);}
        return list;

    }
//根据id进行用户查询
    public User getUserByid(Integer id){

        User user = mapper.selectByPrimaryKey(id);
        System.out.println(user);
        return user;

    }

   //添加一个用户信息
    public int insertUser(User user){
        int insert = mapper.insert(user);
        return insert;
    }


//根据id对一条数据进行修改

    public int updateUser(User user,
                             Integer id
                             ){
        UserExample example = new UserExample();
            example.createCriteria().andIdEqualTo(id);
            int i = mapper.updateByExample(user, example);

        return i;
    }

//根据id删除一条用户信息

    public int deleteUserById(
            Integer id){
        int user = mapper.deleteByPrimaryKey(id);

        return user;

    }


}
