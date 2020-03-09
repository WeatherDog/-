package com.example.magjong;

import com.example.magjong.dto.PaginationDTO;
import com.example.magjong.dto.ReplyQuestionDTO;
import com.example.magjong.dto.SearchDTO;
import com.example.magjong.mapper.ReplyMapper;
import com.example.magjong.model.Reply;
import com.example.magjong.service.ReplyService;
import com.example.magjong.service.SerachService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.MapSolrParams;
import org.apache.solr.common.params.SolrParams;
import org.apache.solr.common.util.NamedList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MagjongApplicationTests {
    @Autowired
    private ReplyService replyService;
    @Autowired
    private SerachService serachService;
    @Test
    void contextLoads() {
        Integer token = 49524010;
        String description="你看地下";
        Integer questionId=13;
        Integer replyParmentId = 51;
        ReplyQuestionDTO replyQuestionDTO = replyService.addSecondLevelReply(token, description, questionId, replyParmentId);
        System.out.println("replyQuestionDTO="+replyQuestionDTO);
    }
    @Test
    void solrQueryTest() throws Exception {
        HttpSolrClient client = new HttpSolrClient.Builder("http://localhost:8983/solr/new_core").build();
        SolrQuery map = new SolrQuery("question_title:爸爸");
        map.setHighlight(true);
        map.addHighlightField("question_title");
        map.setHighlightSimplePre("<font color='red'>");
        map.setHighlightSimplePost("</font>");
        QueryResponse query = client.query(map);
        SolrDocumentList results = query.getResults();
        NamedList list = (NamedList) query.getResponse().get("highlighting");
        System.out.println(results.size());
        for (int i = 0; i < list.size(); i++){
            System.out.println("id=" + list.getName(i) + "文档中高亮显示的字段：" + list.getVal(i));
        }
        client.commit();
        client.close();
    }
    @Test
    void solrAddTest() throws Exception{
        PaginationDTO de = serachService.serach("爸爸", 1);
    }
}
