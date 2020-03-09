package com.example.magjong.utlis;

import cn.ucloud.ufile.UfileClient;
import cn.ucloud.ufile.api.ApiError;
import cn.ucloud.ufile.api.object.ObjectConfig;
import cn.ucloud.ufile.auth.BucketAuthorization;
import cn.ucloud.ufile.auth.ObjectAuthorization;
import cn.ucloud.ufile.auth.UfileBucketLocalAuthorization;
import cn.ucloud.ufile.auth.UfileObjectLocalAuthorization;
import cn.ucloud.ufile.bean.PutObjectResultBean;
import cn.ucloud.ufile.bean.UfileErrorBean;
import cn.ucloud.ufile.exception.UfileClientException;
import cn.ucloud.ufile.exception.UfileServerException;
import cn.ucloud.ufile.http.OnProgressListener;
import cn.ucloud.ufile.http.UfileCallback;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.Request;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Author 雷铭涛
 * Date:2020-02-25
 * vsersion 1.0
 */
@Service
public class FileUtlis {
    @Value("${uclound.publicKey}")
    private String publicKey;
    @Value("${uclound.privateKey}")
    private String privateKey;
    @Value("${uclound.address}")
    private String address;
    public String upload(MultipartFile upFile,String mimeType) throws IOException {
        ObjectAuthorization BUCKET_AUTHORIZER = new UfileObjectLocalAuthorization(publicKey, privateKey);
        ObjectConfig config = new ObjectConfig("cn-bj","ufileos.com");
        String fileName = fileName();
        try {
            PutObjectResultBean response = UfileClient.object(BUCKET_AUTHORIZER, config)
                    .putObject(upFile.getInputStream(), mimeType)
                    .nameAs(fileName)
                    .toBucket("keke")
                    /**
                     * 是否上传校验MD5, Default = true
                     */
                    //  .withVerifyMd5(false)
                    /**
                     * 指定progress callback的间隔, Default = 每秒回调
                     */
                    //  .withProgressConfig(ProgressConfig.callbackWithPercent(10))
                    /**
                     * 配置进度监听
                     */
                    .setOnProgressListener(new OnProgressListener() {
                        public void onProgress(long bytesWritten, long contentLength) {

                        }
                    })
                    .execute();
                    return fileName;
        } catch (UfileClientException e) {
            e.printStackTrace();
        } catch (UfileServerException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String fileName(){
            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String fileName = simpleDateFormat.format(date);
            return fileName;
    }
}
