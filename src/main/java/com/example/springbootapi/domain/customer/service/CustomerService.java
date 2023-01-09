package com.example.springbootapi.domain.customer.service;

import com.example.springbootapi.domain.customer.entity.Customer;
import com.example.springbootapi.domain.customer.entity.repository.CustomerRepository;
import com.example.springbootapi.domain.customer.web.dto.CustomerDto;
import com.example.springbootapi.global.entity.Files;
import com.example.springbootapi.global.entity.repository.FilesRepository;
import com.example.springbootapi.global.utils.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FileService fileService;
    private final FilesRepository filesRepository;

    @Transactional
    public void saveCustomerInfo(CustomerDto dto) {
        Customer customer = Customer
                .builder()
                .itemNumber(dto.getItemNumber())
                .userName(dto.getUserName())
                .phoneNumber(dto.getPhoneNumber())
                .contents(dto.getContents())
                .build();
        customerRepository.save(customer);


    }


    @Transactional(readOnly = true)
    public CustomerDto getCustomerInfo(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow();

        return new CustomerDto(customer);
    }

    @Transactional(readOnly = true)
    public CustomerDto getInfoByPhone(String phoneNumber) {
        Customer customer = customerRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new IllegalArgumentException("없는 번호 입니다."));

        if(customer != null) {
            return new CustomerDto(customer);
        }else {
            return new CustomerDto();
        }

//        Optional<Customer> customer = customerRepository.findByPhoneNumber(phoneNumber);

//        if(customer.isPresent()) {
//            return new CustomerDto(customer.orElseThrow());
//        }else {
//            return new CustomerDto(phoneNumber);
//        }
    }

//    @Transactional
//    public void fileUpload(CustomerDto dto, List<MultipartFile> files) throws Exception {
//        Customer customer = Customer
//                .builder()
//                .userName(dto.getUserName())
//                .itemNumber(dto.getItemNumber())
//                .phoneNumber(dto.getPhoneNumber())
//                .contents(dto.getContents())
//                .build();
//        List<Files> filesList = fileService.fileInfo(files);
//
//        // 파일이 존재할때만 처리
//        if(!files.isEmpty()){
//            for(Files e : filesList) {
//                customer.addFiles(filesRepository.save(e));
//            }
//        }
//        customerRepository.save(customer);
//    }

    @Transactional
    public void uploadCustomerInfo(CustomerDto dto) throws Exception {
        Customer customer = Customer
                .builder()
                .userName(dto.getUserName())
                .itemNumber(dto.getItemNumber())
                .phoneNumber(dto.getPhoneNumber())
                .contents(dto.getContents())
                .build();
        List<Files> fileList = fileService.fileInfo(dto.getFileList());

        if(!fileList.isEmpty()) {
            for(Files e : fileList) {
                customer.addFiles(filesRepository.save(e));
            }
        }
        customerRepository.save(customer);
    }

    @Transactional
    public void uploadFile(Long id, List<MultipartFile> files) throws Exception {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("회원 정보가 없습니다."));

        List<Files> filesList = fileService.fileInfo(files);

        if(!filesList.isEmpty()) {
            for(Files e : filesList) {
                customer.addFiles(filesRepository.save(e));
            }
        }
    }

    @Transactional
    public void deleteUser(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("없는 회원 입니다."));
        customerRepository.delete(customer);
    }


}
