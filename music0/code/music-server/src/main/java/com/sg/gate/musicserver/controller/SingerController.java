package com.sg.gate.musicserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.sg.gate.musicserver.domain.Singer;
import com.sg.gate.musicserver.service.SingerService;
import com.sg.gate.musicserver.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@RequestMapping("/singer")
public class SingerController {

    @Autowired
    private SingerService singerService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addSinger(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
//        trim方法的作用是去掉字符串之间的空格
        String name = request.getParameter("name").trim();
        String sex = request.getParameter("sex").trim();
        String pic = "/img/singerPic/hhh.jpg";
        String birth = request.getParameter("birth").trim();
        String location = request.getParameter("location").trim();
        String introduction = request.getParameter("introduction").trim();
//        把String转换成date形式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
//       为什么要分两步写，因为这样将第二步放在try-catch中，这个birthDate还可以用
        Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Singer singer = new Singer();
        singer.setName(name);
        singer.setSex(Byte.parseByte(sex));
        singer.setPic(pic);
        singer.setBirth(birthDate);
        singer.setLocation(location);
        singer.setIntroduction(introduction);
//        进行插入的操作
        boolean flag = singerService.add(singer);
        if (flag) {
            jsonObject.put(Constants.CODE,1);
            jsonObject.put(Constants.MSG,"添加成功");
            return jsonObject;
        }
        jsonObject.put(Constants.CODE,0);
        jsonObject.put(Constants.MSG,"添加失败");
        return jsonObject;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object delete(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        boolean flag = singerService.delete(Integer.parseInt(id));
        if (flag) {
            jsonObject.put(Constants.CODE,1);
            jsonObject.put(Constants.MSG,"删除成功");
            return jsonObject;
        }
        jsonObject.put(Constants.CODE,0);
        jsonObject.put(Constants.MSG,"删除失败");
        return jsonObject;
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateSinger(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        String name = request.getParameter("name").trim();
        String sex = request.getParameter("sex").trim();
        String birth = request.getParameter("birth").trim();
        String location = request.getParameter("location").trim();
        String introduction = request.getParameter("introduction").trim();
//        把String转换成date形式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
//       为什么要分两步写，因为这样将第二步放在try-catch中，这个birthDate还可以用
        Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Singer singer = new Singer();
        singer.setId(Integer.parseInt(id));
        singer.setName(name);
        singer.setSex(new Byte(sex));
        singer.setBirth(birthDate);
        singer.setLocation(location);
        singer.setIntroduction(introduction);
        singer.setBirth(birthDate);
//        进行修改的操作
        boolean flag = singerService.update(singer);
        if (flag) {
            jsonObject.put(Constants.CODE,1);
            jsonObject.put(Constants.MSG,"修改成功");
            return jsonObject;
        }
        jsonObject.put(Constants.CODE,0);
        jsonObject.put(Constants.MSG,"修改失败");
        return jsonObject;
    }

    @RequestMapping(value = "/selectByPrimaryKey",method = RequestMethod.GET)
    public Object selectSinger(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        boolean flag = singerService.select(Integer.parseInt(id));
        if (flag) {
            jsonObject.put(Constants.CODE,1);
            jsonObject.put(Constants.MSG,"查询成功");
            return jsonObject;
        }
        jsonObject.put(Constants.CODE,0);
        jsonObject.put(Constants.MSG,"查询失败");
        return jsonObject;
    }

    @RequestMapping(value = "/allSinger",method = RequestMethod.GET)
    public Object allSinger() {
        return singerService.selectAllSingers();
    }

    @RequestMapping(value = "/singOfName",method = RequestMethod.GET)
    public Object singOfName(HttpServletRequest request) {
        String name = request.getParameter("name").trim();
        return singerService.singOfName(name);
    }

    @RequestMapping(value = "/singerOfSex",method = RequestMethod.GET)
    public Object selectBySex(HttpServletRequest request) {
        String sex = request.getParameter("sex");
        return singerService.selectBySex(Byte.parseByte(sex));
    }

//    更新歌手图片
    @RequestMapping(value = "/updateSingerPic",method = RequestMethod.POST)
    public Object updateSingerPic(@RequestParam("file")MultipartFile avatorFile,@RequestParam("id")Integer id) {
        JSONObject jsonObject = new JSONObject();
        if (avatorFile.isEmpty()) {
            jsonObject.put(Constants.CODE,0);
            jsonObject.put(Constants.MSG,"文件上传失败");
            return jsonObject;
        }
//        给文件名加上毫秒值的目的是，为了避免两个人上传了一样的文件，然后被覆盖
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
//        获取文件真实路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                + System.getProperty("file.separator") + "singerPic";
//        如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }
//        实际的文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
//        存储到数据库里的相对文件地址 （不要少了singerPic后面的/）
        String storeAvatorPath = "/img/singerPic/" + fileName;
        try {
//            进行上传
            avatorFile.transferTo(dest);
//            上传之后，对数据库进行更新，才能将上传的路径修改到数据库中
            Singer singer = new Singer();
            singer.setId(id);
            singer.setPic(storeAvatorPath);
            boolean flag = singerService.update(singer);
            if (flag) {
                jsonObject.put(Constants.CODE,1);
                jsonObject.put(Constants.MSG,"上传成功");
                jsonObject.put("pic",storeAvatorPath);
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
}
