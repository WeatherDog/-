package com.example.magjong.service.imp;

import com.example.magjong.dto.PaginationDTO;
import com.example.magjong.mapper.QuesstionMapper;
import com.example.magjong.model.Quesstion;
import com.example.magjong.service.ProfileService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Author 雷铭涛
 * Date:2020-03-01
 * vsersion 1.0
 */
public class ProfileServiceImp implements ProfileService {
    @Autowired
    private QuesstionMapper quesstionMapper;
    @Override
    public void list(Integer page, String token) {

    }
}
