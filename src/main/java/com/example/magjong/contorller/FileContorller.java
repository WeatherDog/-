package com.example.magjong.contorller;
import com.example.magjong.utlis.FileUtlis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author 雷铭涛
 * Date:2020-02-24
 * vsersion 1.0
 */
@Controller
public class FileContorller {
    @Autowired
    private FileUtlis fileUtlis;
    @PostMapping("/uploadImage")
    @ResponseBody
    public Map uploadImage(@RequestParam(name = "file")List<MultipartFile> files) throws IOException {
        String fileName = null;
        List<String> list = new ArrayList<String>();
        for(int i=0;i<files.size();i++){
            MultipartFile multipartFile = files.get(i);
            String mimeType = multipartFile.getContentType();
            System.out.println("mimeType="+mimeType);
            fileName = fileUtlis.upload(multipartFile,mimeType);
            list.add("http://keke.cn-bj.ufileos.com/"+fileName);
        }
        HashMap<String, Object> result = new HashMap<>();
        result.put("errno",0);
        result.put("data",list);
//        System.out.println("file="+file.getName());
//        System.out.println("getOriginalFilename="+file.getOriginalFilename().split(".")[1]);
//        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\lei\\Desktop\\kk.png");
//        fileOutputStream.write(bytes);
        return result;
    }
}
