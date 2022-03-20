package com.fudongdong.website.controller;

import java.util.List;

import com.fudongdong.website.entity.Song;
import com.fudongdong.website.mapper.SongMapper;
import com.fudongdong.website.wrapper.SongQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dongdong.fdd
 * @date 2022/3/20 18:52
 */
@RestController
@RequestMapping("/song")
public class SongController {
    private final SongMapper songMapper;

    public SongController(SongMapper songMapper) {this.songMapper = songMapper;}

    @GetMapping("/list")
    public List<Song> list() {
        SongQuery query = new SongQuery();
        return songMapper.listEntity(query);
    }
}
