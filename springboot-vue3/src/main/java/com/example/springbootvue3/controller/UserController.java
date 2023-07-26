package com.example.springbootvue3.controller;

import com.example.springbootvue3.common.Result;
import com.example.springbootvue3.entity.User;
import com.example.springbootvue3.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserMapper userMapper;

    @GetMapping("/all")
    public Result SelectAll(@RequestParam String name)
    {
        List<User> userList = userMapper.selectAll(name);
        return Result.success(userList);
    }

    @GetMapping("/page")
    public Result  SelectByPage(@RequestParam String name, @RequestParam Integer offset, @RequestParam Integer pageSize)
    {
        List<User> userList = userMapper.selectPage(name, offset, pageSize);
        return Result.success(userList);
    }

    @GetMapping("/count")
    public Result  SelectCount(@RequestParam String name)
    {
        Integer count = userMapper.selectCount(name);
        return Result.success(count);
    }

    @PostMapping("/save")
    public Result Save(@RequestBody User user) {
        System.out.print("hellow===============");
        System.out.print(user.getName());
        userMapper.insert(user);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result Update(@RequestBody User user) {
        userMapper.update(user);
        return Result.success();
    }

    @DeleteMapping("/del")
    public Result Delete(@RequestParam Integer id) {
        userMapper.delete(id);
        return Result.success();
    }

}
