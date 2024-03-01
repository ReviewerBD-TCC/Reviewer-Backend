package com.reviewer.reviewer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reviewer.reviewer.models.QuestionForm;
import com.reviewer.reviewer.repositories.FormRepository;
import com.reviewer.reviewer.repositories.QuestionFormRepository;

@Service
public class FormService {

    @Autowired
    private FormRepository FormRepository;

    @Autowired
    private QuestionFormRepository questionFormRepository;
    
}
