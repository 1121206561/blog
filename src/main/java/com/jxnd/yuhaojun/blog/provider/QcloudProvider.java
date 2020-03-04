package com.jxnd.yuhaojun.blog.provider;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.model.GeneratePresignedUrlRequest;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

@Service
public class QcloudProvider {
    @Value("${TengXun.Cos_secretid}")
    private String COS_SECRETID;

    @Value("${TengXun.Cos_secretkey}")
    private String COS_SECRETKEY;

    @Value("${TengXun.Cos_region}")
    private String COS_REGION;
    @Value("${TengXun.BucketName}")

    private String BucketName;

    public String FileToCos(InputStream file) {
        // 1 初始化用户身份信息（secretId, secretKey）。
        String secretId = COS_SECRETID;
        String secretKey = COS_SECRETKEY;
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的区域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region(COS_REGION);
        ClientConfig clientConfig = new ClientConfig(region);
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);
        // 指定要上传的文件;
        // 指定要上传到的存储桶
        String bucketName = BucketName;
        // 指定要上传到 COS 上对象键
        String fileName = UUID.randomUUID().toString();
        String key = fileName;
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file, new ObjectMetadata());
        cosClient.putObject(putObjectRequest);
        //获取预签名url
        GeneratePresignedUrlRequest req =
                new GeneratePresignedUrlRequest(bucketName, key, HttpMethodName.GET);
        // 设置签名过期时间(可选), 若未进行设置, 则默认使用 ClientConfig 中的签名过期时间(1小时)
        // 这里设置签名在半个小时后过期
        Date expirationDate = new Date(System.currentTimeMillis() + 30L * 60L * 1000L * 1000);
        req.setExpiration(expirationDate);
        URL url = cosClient.generatePresignedUrl(req);
        cosClient.shutdown();
        return url.toString();
    }
}
