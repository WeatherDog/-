package com.example.magjong.service.imp;
import com.alibaba.fastjson.JSONObject;
import com.example.magjong.dto.PaginationDTO;
import com.example.magjong.dto.ReplyDTO;
import com.example.magjong.dto.ReplyQuestionDTO;
import com.example.magjong.dto.User;
import com.example.magjong.mapper.ReplyMapper;
import com.example.magjong.mapper.UserMapper;
import com.example.magjong.model.Reply;
import com.example.magjong.service.ReplyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 *Author 雷铭涛
 *vsersion 1.0
 *
 */
@Service
public class ReplyServiceImp implements ReplyService {
    @Autowired
    private ReplyMapper replyMapper;
    @Autowired
    private UserMapper userMapper;
    /**
     *翻页信息
     * */
    public ReplyDTO pagination(Integer page, Integer questionId) {
        PageHelper.startPage(page,5);
        List<Reply> total = replyMapper.total(questionId);
        PageInfo pageInfo = new PageInfo(total);
        List list = pageInfo.getList();
        PaginationDTO paginationDTO = new PaginationDTO();
        Long pagetotal = pageInfo.getTotal();
        paginationDTO.setPagination(pagetotal.intValue(),page,pageInfo.getPages());
        List questionInfo = pageInfo.getList();
        paginationDTO.setQuesstionList(questionInfo);
        paginationDTO.setTatalPage(pageInfo.getPages());
        List userList = new ArrayList<User>();
        for(int i=0;i<list.size();i++){
            Reply reply = (Reply) list.get(i);
            User user = userMapper.selectUser(reply.getReplyUserId().longValue());
            userList.add(user);
        }
        ReplyDTO replyDTO = new ReplyDTO(paginationDTO,userList);
        return replyDTO;
    }
    /**
     *添加二级评论信息
     * */
    public ReplyQuestionDTO addSecondLevelReply(Integer token, String description, Integer questionId,Integer replyParmentId) {
        if(description==null&&description==""){
            return null;
        }
        Reply reply = new Reply();
        reply.setDescription(description);
        reply.setRank(2);
        reply.setCreateTime(System.currentTimeMillis());
        reply.setReplyUserId(token);
        reply.setLikeCount(0);
        reply.setReplyCount(0);
        reply.setQuestionId(questionId);
        reply.setStatus(true);
        reply.setReplyParmentId(replyParmentId);
        User user = userMapper.selectUser(token.longValue());
        ReplyQuestionDTO replyQuestionDTO = new ReplyQuestionDTO();
        replyQuestionDTO.setUser(user);
        replyQuestionDTO.setReply(reply);
        replyMapper.addRpely(reply);
        return replyQuestionDTO;
    }

    public List<ReplyQuestionDTO> iniSecondlevel(Integer replyParentId, Integer questionId) {
        List<Reply> secondLevels = replyMapper.getSecondLevel(questionId, replyParentId);
        ArrayList<ReplyQuestionDTO> replyQuestionDTOS = new ArrayList<>();
        for (Reply secondLevel:secondLevels){
            User user = userMapper.selectUser(secondLevel.getReplyUserId().longValue());
            ReplyQuestionDTO replyQuestionDTO = new ReplyQuestionDTO();
            replyQuestionDTO.setReply(secondLevel);
            replyQuestionDTO.setUser(user);
            replyQuestionDTOS.add(replyQuestionDTO);
        }
        return replyQuestionDTOS;
    }
}
