package com.reviewer.reviewer.dto.email;
 

import org.springframework.web.multipart.MultipartFile;

public record EmailRecordDto(Long id, String to, String[] cc,String []bcc, MultipartFile[] attchment, String subject, String body) {

}
