package com.example.magjong.utlis;


import org.apache.solr.client.solrj.impl.HttpSolrClient;

/**
 * Author 雷铭涛
 * Date:2020-03-04
 * vsersion 1.0
 */
public class SolrUtils {
    public HttpSolrClient getSolrConnection(){
        return new HttpSolrClient.Builder("http://localhost:8983/solr/new_core").build();
    }
}
