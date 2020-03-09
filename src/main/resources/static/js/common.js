function paginationProduction(paginationDTO,tagId,attrName) {
    pageSize='';
    pagination="";
    $.each(paginationDTO.pages,function (index,obj) {
        pageSize+='<li>'+
            '<a href="javascript:;" onclick="paginationClick('+obj+')" name='+attrName+'>'+obj+'</a></li>';
    });
    var showPreviousFT="";
    if(paginationDTO.showPrevious){
        showPreviousFT+= '<li>'+
            '<a href="#" onclick="paginationClick(1)" aria-label="Previous">'+
            '<span aria-hidden="true" >&laquo;</span></a></li>';
    }
    var showAfterFT="";
    if(paginationDTO.showAfter){
        showAfterFT+='<li>'+
            '<a href="#" onclick="paginationClick('+paginationDTO.tatalPage+')" aria-label="Next" id="Aeli">'+
            '<span aria-hidden="true">&raquo;</span></a></li>'
    }
    pagination+='<div"><nav aria-label="Page navigation">'+
        '<ul class="pagination">'+showPreviousFT+
        ' <li>'+pageSize+showAfterFT+
        '</ul></nav>';
    console.log(paginationDTO);
    $(tagId).append(pagination);
}
//文件名称生成器
function fileNameGenerator() {
    var now=new Date();
    var year = now.getFullYear(); //得到年份
    var month = now.getMonth();//得到月份
    var date = now.getDate();//得到日期
    var hour = now.getHours();//得到小时
    var minu = now.getMinutes();//得到分钟
    month = month + 1;
    if (month < 10) month = "0" + month;
    if (date < 10) date = "0" + date;
    var number = now.getSeconds()%43; //这将产生一个基于目前时间的0到42的整数。
    var time = year + month + date+hour+minu;
    return time+"_"+number;
}