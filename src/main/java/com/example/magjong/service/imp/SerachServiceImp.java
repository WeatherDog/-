package com.example.magjong.service.imp;

import com.alibaba.fastjson.JSONObject;
import com.example.magjong.dto.PaginationDTO;
import com.example.magjong.dto.SearchDTO;
import com.example.magjong.mapper.UserMapper;
import com.example.magjong.service.SerachService;
import com.example.magjong.utlis.SolrUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * Author 雷铭涛
 * Date:2020-03-04
 * vsersion 1.0
 */
@Service
public class SerachServiceImp implements SerachService {
    @Autowired
    private UserMapper userMapper;
    public PaginationDTO serach(String key,Integer page){
        List <SearchDTO> list = new ArrayList<>();
        HttpSolrClient client = new SolrUtils().getSolrConnection();
        SolrQuery query = new SolrQuery("question_title:"+key);
        query.addHighlightField("question_title");
        query.setHighlight(true);
        query.setHighlightSimplePre("<font color='red'>");
        query.setHighlightSimplePost("</font>");
        query.setStart((page-1)*10);
        query.setRows(10);
        QueryResponse queryResponse = null;
        try {
            queryResponse = client.query(query);
            SolrDocumentList results = queryResponse.getResults();
            for (SolrDocument result:results
                 ) {
                String s = JSONObject.toJSONString(result);
                SearchDTO searchDTO = JSONObject.parseObject(s, SearchDTO.class);
                String src = userMapper.getSrc(Integer.parseInt(searchDTO.getQuestionCreator()));
                searchDTO.setUserSrc(src);
                list.add(searchDTO);
            }
            //高亮
            Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
            Set<String> highlightingTitle = highlighting.keySet();
            for(int i=0;i<list.size();i++){
                String questionTitle = highlighting.get(list.get(i).getId()).get("question_title").get(0);
                list.get(i).setQuestionTitle(questionTitle);
            }
            //插入user

            long numFound = results.getNumFound();
            int pages = (int) (numFound%10);
            PaginationDTO paginationDTO = new PaginationDTO();
            paginationDTO.setPagination(0,page,pages);
            paginationDTO.setQuesstionList(list);
            System.out.println("paginationDTO="+paginationDTO);
            return paginationDTO;
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                client.commit();
                client.close();
            } catch (SolrServerException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
            return null;

    }
}
