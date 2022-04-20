package com.sg.gate.musicserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.sg.gate.musicserver.domain.Song;
import com.sg.gate.musicserver.service.SongService;
import com.sg.gate.musicserver.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;


@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    private SongService songService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addSong(HttpServletRequest request, @RequestParam("file")MultipartFile mpFile) {
        JSONObject jsonObject = new JSONObject();
//        createTime和updateTime没有获取，采用系统自动生成。
        String singerId = request.getParameter("singerId").trim();
        String name = request.getParameter("name").trim();
        String introduction = request.getParameter("introduction").trim();
        String pic = "/img/songPic/tubiao.jpg"; //默认图片
        String lyric = request.getParameter("lyric").trim();
//        上传歌曲文件
        if (mpFile.isEmpty()) {
            jsonObject.put(Constants.CODE,0);
            jsonObject.put(Constants.MSG,"歌曲上传失败");
            return jsonObject;
        }
        String fileName = System.currentTimeMillis() + mpFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song";
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
//        存储到数据库里的相对文件地址 （不要少了songPic后面的/）
        String storeAvatorPath = "img/songPic/" + fileName;
        try {
            mpFile.transferTo(dest);
            Song song = new Song();
            song.setSingerId(Integer.parseInt(singerId));
            song.setName(name);
            song.setIntroduction(introduction);
            song.setPic(pic);
            song.setUrl(storeAvatorPath);
            song.setLyric(lyric);
            boolean flag = songService.add(song);
            if (flag) {
                jsonObject.put(Constants.CODE,1);
                jsonObject.put(Constants.MSG,"添加成功");
                return jsonObject;
            }
            jsonObject.put(Constants.CODE,0);
            jsonObject.put(Constants.MSG,"添加失败");
            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return jsonObject;
        }

    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteSong(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id");
        boolean flag = songService.delete(Integer.parseInt(id));
        if (flag) {
            jsonObject.put(Constants.CODE,1);
            jsonObject.put(Constants.MSG,"删除成功");
            return jsonObject;
        }
        jsonObject.put(Constants.CODE,1);
        jsonObject.put(Constants.MSG,"删除失败");
        return jsonObject;
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateSong(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        String name = request.getParameter("name").trim();
        String introduction = request.getParameter("introduction").trim();
        String lyric = request.getParameter("lyric").trim();
        Song song = new Song();
        song.setId(Integer.parseInt(id));
        song.setName(name);
        song.setIntroduction(introduction);
        song.setLyric(lyric);
        boolean flag = songService.update(song);
        if (flag) {
            jsonObject.put(Constants.CODE,1);
            jsonObject.put(Constants.MSG,"修改成功");
            return jsonObject;
        }
        jsonObject.put(Constants.CODE,0);
        jsonObject.put(Constants.MSG,"修改失败");
        return jsonObject;
    }


    /**
     * 这个方法是用来查看歌单页面的歌曲管理和用户管理的收藏页面都会用到的方法
     * 第一种情况的songId是ListSongController里的selectListSong方法返回的
     * 第二种情况的songId是CollectController里的collectOfUserId方法返回的collect对象中有singerId
     * @param request
     * @return
     */
    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    public Object selectBySongId(HttpServletRequest request) {
//        这个songId是从前台传过来的就相当于song的id属性
        String songId = request.getParameter("songId");
        return songService.selectBySongId(Integer.parseInt(songId));
    }

    //    根据歌手id查询歌曲
    @RequestMapping(value = "/singer/detail",method = RequestMethod.GET)
    public Object selectBySingerId(HttpServletRequest request) {
        String singerId = request.getParameter("singerId");//这个地方没有接收到
        return songService.selectBySinger(Integer.parseInt(singerId));
    }

    @RequestMapping(value = "/allSong",method = RequestMethod.GET)
    public Object selectAll() {
        return songService.selectAllSongs();
    }

    /**
     * 这个方法很重要，是用来从歌单页面添加歌曲到歌单用的。
     * 里面的songName是从前端add添加框那里获得的歌曲名字参数
     * @param request
     * @return
     */
    @RequestMapping(value = "/songOfSongName",method = RequestMethod.GET)
    public Object selectByName(HttpServletRequest request) {
        String songName = request.getParameter("songName");
        return songService.songOfName(songName);
    }

    @RequestMapping(value = "/updateSongPic",method = RequestMethod.POST)
    public Object updateSongPic(@RequestParam("file")MultipartFile mpfile,@RequestParam("id")Integer id) {
        JSONObject jsonObject = new JSONObject();
        String fileName = System.currentTimeMillis() + mpfile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                            + System.getProperty("file.separator") + "songPic";
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        File last = new File(filePath + System.getProperty("file.separator") + fileName);
        String avtorPath = "/img/songPic/" + fileName;
        try {
            mpfile.transferTo(last);
            Song song = new Song();
            song.setId(id);
            song.setPic(avtorPath);
            boolean flag = songService.update(song);
            if (flag) {
                jsonObject.put(Constants.CODE,1);
                jsonObject.put(Constants.MSG,"修改成功");
                return jsonObject;
            }
            jsonObject.put(Constants.CODE,0);
            jsonObject.put(Constants.MSG,"修改失败");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return jsonObject;
        }
    }

    @RequestMapping(value = "/updateSongUrl",method = RequestMethod.POST)
    public Object updateSongUrl(@RequestParam("file")MultipartFile mpFile,@RequestParam("id")Integer id) {
        JSONObject jsonObject = new JSONObject();
        String fileName = System.currentTimeMillis() + mpFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song";
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        File last = new File(filePath + System.getProperty("file.separator") + fileName);
        String avtorPath = "/song/" + fileName;
        try {
            mpFile.transferTo(last);
            Song song = new Song();
            song.setId(id);
            song.setUrl(avtorPath);
            boolean flag = songService.update(song);
            if (flag) {
                jsonObject.put(Constants.CODE,1);
                jsonObject.put(Constants.MSG,"修改成功");
                return jsonObject;
            }
            jsonObject.put(Constants.CODE,0);
            jsonObject.put(Constants.MSG,"修改失败");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return jsonObject;
        }
    }

    @RequestMapping(value = "/likeSongOfName",method = RequestMethod.GET)
    public Object likeSongOfName(HttpServletRequest request) {
//        因为是模糊查询所以要在参数前后加上%
        return songService.likeSongOfName("%" + request.getParameter("songName") + "%");
    }

}
