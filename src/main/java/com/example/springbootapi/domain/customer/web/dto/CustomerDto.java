package com.example.springbootapi.domain.customer.web.dto;

import com.example.springbootapi.domain.customer.entity.Customer;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Lob;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {

    private String itemNumber;

    private String userName;

    private String phoneNumber;

    @Lob
    private String contents;

//    private List<Files> files;

    private List<MultipartFile> fileList = new ArrayList<>();

    public CustomerDto(Customer entity) {
        this.itemNumber = entity.getItemNumber();
        this.userName = entity.getUserName();
        this.phoneNumber = entity.getPhoneNumber();
        this.contents = entity.getContents();
    }

    public CustomerDto(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
