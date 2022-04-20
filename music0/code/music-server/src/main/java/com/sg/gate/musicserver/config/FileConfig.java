package com.sg.gate.musicserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//定位各种文件或头像地址
@Configuration
public class FileConfig implements WebMvcConfigurer {

//    可以将一些静态资源，比如图片，js,images..放到一个文件夹里进行重新查找
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        将相对路径映射到绝对路径
//        对歌手头像地址定位
        registry.addResourceHandler("/img/singerPic/**").addResourceLocations(
                "file:" + System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                + System.getProperty("file.separator") + "singerPic" + System.getProperty("file.separator")
        );
//      对歌曲封面图定位
        registry.addResourceHandler("/img/songPic/**").addResourceLocations(
                "file:" + System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                        + System.getProperty("file.separator") + "songPic" + System.getProperty("file.separator")
        );
//        对歌单封面图定位
        registry.addResourceHandler("/img/songListPic/**").addResourceLocations(
                "file:" + System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                        + System.getProperty("file.separator") + "songListPic" + System.getProperty("file.separator")
        );
//        用户头像定位
        registry.addResourceHandler("/avatorImages/**").addResourceLocations(
                "file:" + System.getProperty("user.dir") + System.getProperty("file.separator")
                        + "avatorImages"
                        + System.getProperty("file.separator")
        );
//        对前台上传用户头像
        registry.addResourceHandler("/img/**").addResourceLocations(
                "file:" + System.getProperty("user.dir") + System.getProperty("file.separator")
                        + "img"
                        + System.getProperty("file.separator")
        );
//        歌曲地址定位
        registry.addResourceHandler("/song/**").addResourceLocations(
                "file:" + System.getProperty("user.dir") + System.getProperty("file.separator")
                        + "song"
                        + System.getProperty("file.separator")
        );
    }
}
