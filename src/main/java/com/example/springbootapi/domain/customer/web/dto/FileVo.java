package com.example.springbootapi.domain.customer.web.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Lob;
import java.util.List;

@Data
public class FileVo {

    private String itemNumber;

    private String userName;

    private String phoneNumber;

    @Lob
    private String contents;

    private List<MultipartFile> files;

}
