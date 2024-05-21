package com.reviewer.reviewer.dto.email;


public record EmailRecordDto(String to,String []bcc, String subject, String body) {

}
