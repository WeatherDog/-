package com.example.magjong.service;

import com.example.magjong.dto.PaginationDTO;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.List;

/**
 * Author 雷铭涛
 * Date:2020-03-04
 * vsersion 1.0
 */
public interface SerachService {
    public PaginationDTO serach(String key,Integer page) throws IOException, SolrServerException;
}
