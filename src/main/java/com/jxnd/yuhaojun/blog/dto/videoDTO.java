package com.jxnd.yuhaojun.blog.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class videoDTO {
    private Long roomNumber;
    private Integer totalCount;
    private MultipartFile img_file;
}
