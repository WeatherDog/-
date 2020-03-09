package com.example.magjong.dto;

import com.example.magjong.model.Quesstion;
import lombok.Data;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Data
public class PaginationDTO {
    //返回数据
    private List quesstionList;
    //是否有最向前按钮
    private boolean showPrevious;
    //是否有后前按钮
    private boolean showAfter;
    //当前页面
    private Integer page;
    //页面显示页码
    private List pages;
    //
    private Integer tatalPage;
    public PaginationDTO(){
        this.pages = new ArrayList<Integer>();
    }
    /**
     * summationLine 查询的也总条数
     * page 当前页数
     * maxPage最大页数
     * */
    public void setPagination(Integer summationLine,
                              Integer page,
                              Integer maxpage) {
        this.tatalPage = maxpage;
        //赋值
        this.page = page;
        //设置是否有向前按钮
        if(maxpage>5&&page>=3){
            this.showPrevious = true;
        }else {
            this.showPrevious = false;
        }
        //设置最大边界值
        Integer maxBoundary = maxpage;
        //设置是否有向后按钮
        System.out.println(this.page<=(maxpage-3));
        if(maxpage>5&&this.page<=(maxpage-3)){
            this.showAfter=true;
        }else {
            this.showAfter = false;
        }
        if(maxpage<=5){
            System.out.println("1");
            for(int i=0;i<maxpage;i++){
                this.pages.add(i+1);
            }
        }else if(this.page<3){
            System.out.println("2");
            for(int i=0;i<=4;i++){
                this.pages.add(i+1);
            }
        }else if ((this.page+2)>=maxBoundary){
            System.out.println("3");
            Integer iniPageSize = 0;
            for(int i = 4;i>=0;i--){
                this.pages.add(maxpage-iniPageSize);
                iniPageSize++;
            }
            Collections.sort(this.pages);
        }else {
            System.out.println("4");
            Integer iniPageSize = page-1;
            for(int i=0;i<=4;i++){
                this.pages.add(iniPageSize);
                iniPageSize++;
            }
        }

    }
}
