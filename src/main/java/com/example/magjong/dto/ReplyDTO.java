package com.example.magjong.dto;

import lombok.Data;

import java.util.List;

@Data
/**
 * 返回翻页数据
 * pagination 翻页传输基础的数据
 * userList 翻页回去的用户基础数据
 * */
public class ReplyDTO {
    private PaginationDTO paginationDTO;
    private List userList;
    public ReplyDTO(){}
    public ReplyDTO(PaginationDTO paginationDTO,List userList){
        this.paginationDTO = paginationDTO;
        this.userList = userList;
    }
}
