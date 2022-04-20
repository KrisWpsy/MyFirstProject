package com.sg.gate.musicserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.sg.gate.musicserver.domain.Consumer;
import com.sg.gate.musicserver.service.ConsumerService;
import com.sg.gate.musicserver.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object insertConsumer(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String phoneNum = request.getParameter("phoneNum");
        String email = request.getParameter("email");
        String birth = request.getParameter("birth");
        String introduction = request.getParameter("introduction");
        String location = request.getParameter("location");
        String avator = request.getParameter("avator");

        if(username==null||username.equals("")){
            jsonObject.put(Constants.CODE,0);
            jsonObject.put(Constants.MSG,"用户名不能为空");
            return jsonObject;
        }

        List<Consumer> consumer0 = consumerService.getByUsername(username);
        if(!consumer0.isEmpty()){
            jsonObject.put(Constants.CODE,0);
            jsonObject.put(Constants.MSG,"用户名已存在");
            return jsonObject;
        }

        if(password == null || password.equals("")){
            jsonObject.put(Constants.CODE,0);
            jsonObject.put(Constants.MSG,"密码不能为空");
            return jsonObject;
        }

        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date birthDate = new Date();
        try {
            birthDate = simpleDateFormat.parse(birth);
        } catch (Exception e) {
            e.printStackTrace();
        }
            Consumer consumer = new Consumer();
            consumer.setUsername(username);
            consumer.setPassword(password);
            consumer.setSex(sex);
            consumer.setPhoneNum(phoneNum);
            consumer.setEmail(email);
            consumer.setIntroduction(introduction);
            consumer.setLocation(location);
            consumer.setAvator(avator);
            consumer.setBirth(birthDate);
            boolean flag = consumerService.addConsumer(consumer);
            if (flag) {
                jsonObject.put(Constants.CODE,1);
                jsonObject.put(Constants.MSG,"添加成功");
                return jsonObject;
            }
            jsonObject.put(Constants.CODE,0);
            jsonObject.put(Constants.MSG,"添加失败");
            return jsonObject;
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateConsumer(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String phoneNum = request.getParameter("phoneNum");
        String email = request.getParameter("email");
        String birth = request.getParameter("birth");
        String introduction = request.getParameter("introduction");
        String location = request.getParameter("location");

        if(username==null||username.equals("")){
            jsonObject.put(Constants.CODE,0);
            jsonObject.put(Constants.MSG,"用户名不能为空");
            return jsonObject;
        }
        if(password==null||password.equals("")){
            jsonObject.put(Constants.CODE,0);
            jsonObject.put(Constants.MSG,"密码不能为空");
            return jsonObject;
        }
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date birthDate = new Date();
        try {
            birthDate = simpleDateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Consumer consumer = new Consumer();
        consumer.setId(Integer.parseInt(id));
        consumer.setUsername(username);
        consumer.setPassword(password);
        consumer.setSex(sex);
        consumer.setPhoneNum(phoneNum);
        consumer.setEmail(email);
        consumer.setIntroduction(introduction);
        consumer.setLocation(location);
        consumer.setBirth(birthDate);
        boolean flag = consumerService.updateConsumer(consumer);
        if (flag) {
            jsonObject.put(Constants.CODE,1);
            jsonObject.put(Constants.MSG,"修改成功");
            return jsonObject;
        }
        jsonObject.put(Constants.CODE,0);
        jsonObject.put(Constants.MSG,"修改失败");
        return jsonObject;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteConsumer(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id");
        boolean flag = consumerService.deleteConsumer(Integer.parseInt(id));
        if (flag) {
            jsonObject.put(Constants.CODE,1);
            jsonObject.put(Constants.MSG,"删除成功");
            return jsonObject;
        }
        jsonObject.put(Constants.CODE,0);
        jsonObject.put(Constants.MSG,"删除失败");
        return jsonObject;
    }

    @RequestMapping(value = "selectByPrimaryKey",method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest request) {
        String id = request.getParameter("id");
        return consumerService.selectById(Integer.parseInt(id));
    }

    @RequestMapping(value = "/allConsumer",method = RequestMethod.GET)
    public Object allConsumer() {
        return consumerService.selectAllConsumer();
    }

    @RequestMapping(value = "/updateConsumerPic",method = RequestMethod.POST)
    public Object updateConsumerPic(@RequestParam("file") MultipartFile mpFile, @RequestParam("id")Integer id) {
        JSONObject jsonObject = new JSONObject();
        if(mpFile.isEmpty()){
            jsonObject.put(Constants.CODE,0);
            jsonObject.put(Constants.MSG,"文件上传失败");
            return jsonObject;
        }
        String fileName = System.currentTimeMillis() + mpFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "avatorImages";
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        File last = new File(filePath + System.getProperty("file.separator") + fileName);
        String avator = "/avatorImages/" + fileName;
        try {
            mpFile.transferTo(last);
            Consumer consumer = new Consumer();
            consumer.setId(id);
            consumer.setAvator(avator);
            boolean flag = consumerService.updateConsumer(consumer);
            if (flag) {
                jsonObject.put(Constants.CODE,1);
                jsonObject.put(Constants.MSG,"上传成功");
//                上传成功之后，要把这个相对路径传到前台，否则前台拿不到数据无法显示
                jsonObject.put("avator",avator);
                return jsonObject;
            }
            jsonObject.put(Constants.CODE,0);
            jsonObject.put(Constants.MSG,"上传失败");
            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return jsonObject;
        }
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Object login(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean flag = consumerService.login(username, password);
        if (username==null||username.equals("")){
            jsonObject.put(Constants.CODE,0);
            jsonObject.put(Constants.MSG,"用户名不能为空");
            return jsonObject;
        }
        if(password==null||password.equals("")){
            jsonObject.put(Constants.CODE,0);
            jsonObject.put(Constants.MSG,"密码不能为空");
            return jsonObject;
        }
        if (flag) {
            jsonObject.put(Constants.CODE,1);
            jsonObject.put(Constants.MSG,"登陆成功");
//            要把登录的这个的对象传送到前台，因为前台需要从这个对象中获取数据
//            这里要用get方法取得第一个元素，因为username加了unique约束，所以这恶鬼集合只会有一个元素
            jsonObject.put("userMsg",consumerService.getByUsername(username).get(0));
            return jsonObject;
        }
        jsonObject.put(Constants.CODE,0);
        jsonObject.put(Constants.MSG,"用户名或密码错误");
        return jsonObject;
    }
}
