package com.example.magjong.service.imp;

import com.alibaba.fastjson.JSONObject;
import com.example.magjong.dto.PaginationDTO;
import com.example.magjong.dto.QuesitonDTO;
import com.example.magjong.dto.ReplyQuestionDTO;
import com.example.magjong.dto.User;
import com.example.magjong.mapper.QuesstionMapper;
import com.example.magjong.mapper.ReplyMapper;
import com.example.magjong.mapper.UserMapper;
import com.example.magjong.model.Quesstion;
import com.example.magjong.model.Reply;
import com.example.magjong.service.QuestionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class QuestionServiceImp implements QuestionService {
    @Autowired
    private QuesstionMapper quesstionMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ReplyMapper replyMapper;
    public PaginationDTO list(Integer page) {
        PageHelper.startPage(page,3);
        List<Quesstion> quesstions = quesstionMapper.list();
        PageInfo pageInfo = new PageInfo(quesstions);
        Integer quesstionListSize = 3;
        Integer offsizi = (page-1)*quesstionListSize;
        //取出quesstion数据
        List<Quesstion> quesstionList = quesstionMapper.getList(offsizi);
        List<QuesitonDTO> quesitonDTOS = new ArrayList<>();
        PaginationDTO paginationDTO = new PaginationDTO();
        //循环取出的quqsstionList对象
        for (Quesstion q:quesstionList
             ) {
            //创建quesitionDTO对象
            QuesitonDTO quesitonDTO = new QuesitonDTO();
            //使用beanUtils对象复制question属性到
            BeanUtils.copyProperties(q,quesitonDTO);
            Integer creator = q.getCreator();
            quesitonDTO.setUser(userMapper.selectUser(creator.longValue()));
            quesitonDTOS.add(quesitonDTO);
        }
        paginationDTO.setQuesstionList(quesitonDTOS);
        //取出question表的行数
        Integer commationLine = quesstionMapper.getCommationLine();
        //        设置paginationDTO对象属性
        paginationDTO.setPagination(commationLine,page,pageInfo.getPages());
        System.out.println("paginationDTO="+paginationDTO);
        return paginationDTO;
    }
    public PaginationDTO myQuestion(Integer page,String token) {
        Integer quesstionListSize = 3;
        Integer offsizi = (page-1)*quesstionListSize;
        //取出quesstion数据
        List<Quesstion> quesstionList = quesstionMapper.getList(offsizi);
        List<QuesitonDTO> quesitonDTOS = new ArrayList<>();
        PaginationDTO paginationDTO = new PaginationDTO();
        //循环取出的quqsstionList对象
        for (Quesstion q:quesstionList
        ) {
            //创建quesitionDTO对象
            QuesitonDTO quesitonDTO = new QuesitonDTO();
            //使用beanUtils对象复制question属性到
            BeanUtils.copyProperties(q,quesitonDTO);
            Integer creator = q.getCreator();
            quesitonDTO.setUser(userMapper.selectUser(creator.longValue()));
            quesitonDTOS.add(quesitonDTO);
        }
        paginationDTO.setQuesstionList(quesitonDTOS);
        //取出question表的行数
        Integer commationLine = quesstionMapper.getCommationLine();
        //        设置paginationDTO对象属性
        paginationDTO.setPagination(commationLine,page,quesstionListSize);
        return paginationDTO;
    }
    public Quesstion lookArticle(Long id){
        Quesstion quesstion = quesstionMapper.getById(id);
        return quesstion;
    }
    public ReplyQuestionDTO addRpely(Integer token,String description,Integer questionId) {
        Reply reply = new Reply();
        reply.setDescription(description);
        System.out.println("QuestionService desc="+System.currentTimeMillis());
        reply.setRank(0);
        Long time = System.currentTimeMillis();
        reply.setCreateTime(time);
        reply.setReplyUserId(token);
        reply.setLikeCount(0);
        reply.setReplyCount(0);
        reply.setQuestionId(questionId);
        replyMapper.addRpely(reply);
        Integer id = replyMapper.getId(time);
        reply.setId(id);
        if(replyMapper.count(questionId)>5){
            return null;
        };
        User user = userMapper.selectUser(token.longValue());
        ReplyQuestionDTO replyQuestionDTO = new ReplyQuestionDTO();
        replyQuestionDTO.setReply(reply);
        replyQuestionDTO.setUser(user);
        return replyQuestionDTO;
    }
}
