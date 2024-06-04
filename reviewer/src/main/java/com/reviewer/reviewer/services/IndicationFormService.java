package com.reviewer.reviewer.services;
import com.reviewer.reviewer.dto.email.EmailRecordDto;
import com.reviewer.reviewer.dto.forms.*;
import com.reviewer.reviewer.dto.users.UserDto;
import com.reviewer.reviewer.dto.users.UserResponseDto;
import com.reviewer.reviewer.models.Form;
import com.reviewer.reviewer.models.IndicationForm;
import com.reviewer.reviewer.models.Indicated;
import com.reviewer.reviewer.models.QuestionForm;
import com.reviewer.reviewer.repositories.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class IndicationFormService {

    @Autowired
    private IndicationFormRepository indicationFormRepository;

    @Autowired
    private IndicatedRepository indicatedRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionFormRepository questionFormRepository;
    @Autowired
    private QuestionAnswerRepository questionAnswerRepository;
    @Autowired
    private FormRepository formRepository;

    @Autowired
    public EmailService emailService;

    @Autowired
    private UserService userService;

    @Autowired
    private DashboardService dashboardService;
    @Autowired
    private DashboardRepository dashboardRepository;

    public IndicationFormResponseDto create(@Valid IndicationFormDto data, Jwt jwtUser) throws UnknownHostException {

        userService.isInDatabase(new UserResponseDto(jwtUser));
        var user = userRepository.findById(data.userIndication());
        var form = formRepository.findById(data.formId());
        List<Indicated> indicatedList = new ArrayList<>();
        List<IndicationForm> indicationUser = new ArrayList<>();
        if(form.isEmpty()) throw new NoSuchElementException("Form not found!");
        dashboardService.addNewValueFormSent(form.get());
        for (int i = 0; i < data.indicateds().size(); i++) {
            
            var userIndicated = userRepository.findById(data.indicateds().get(i).userIndicated());
            var indicated = new Indicated(userIndicated.get());
            indicatedList.add(indicated);
            var indication = new IndicationForm(user.get(), indicated, form.get());



            if(user.get().getId().equals(indicatedList.get(i).getUserIndicated().getId()) && indicatedList.get(i).getUserIndicated().getEmail().equals(userIndicated.get().getEmail())){
                throw new NoSuchElementException("You repeated a user id in your indications! ");
            }
            else {
                indicatedRepository.save(indicated);
                indicationFormRepository.save(indication);
                indicationUser.add(indication);
            } 
          
        }

        List<IndicatedResponseDto> indicatedResponseDtos = IndicatedResponseDto.fromIndicatedList(indicatedList);
        int index = 0;
        String[] indicateds = new String[data.indicateds().size()];
        for (Indicated indicated : indicatedList) {
            System.out.println(data.indicateds());
            indicateds[index] = indicated.getUserIndicated().getEmail();
            index+=1;
        };
    
        InetAddress end = InetAddress.getLocalHost();
        var ip = end.getHostAddress();
        String body = """
                <p>Disparo de indicação referente ao <strong>%s</strong></p>
                <h3>Clique no link abaixo para responder o formulário!!</h3>
                <p>http://%s:5173/form</p>
                <p>Saudações / Best regards,
                   <br>
                   <br>
                   <strong>Bot Feedback</strong>
                   <br>
                   <br>
                   BD/XD-BR Feedback (BD/SWD-FSA1)
                   <br>
                   Robert Bosch Limitada | Caixa Postal 954 | CEP 13012-970 | BRASIL | www.bosch.com.br
                   <br>
                   Feedback.BDXD-BR@br.bosch.com
                   </p>
                
                """.formatted(user.get().getName(), ip);

        var email = new EmailRecordDto( "Feedback.BDXD-BR@br.bosch.com",indicateds ,  "Disparo de indicações Email!", body);
        emailService.sendMail(email, jwtUser);

        IndicationForm lastIndication = indicationUser.get(indicationUser.size() - 1);
        IndicationFormResponseDto responseDto = new IndicationFormResponseDto(lastIndication, indicatedResponseDtos);

        return responseDto;
    }
    public List<QuestionFormListDto> getIndicatedWithForm(String indicatedId) {
        var indication = indicationFormRepository.findAll();
        var indicated = indicatedRepository.findAll();
        var userLoggedIndicated = userRepository.findById(indicatedId);
        List<QuestionFormListDto> questionFormResponseDtos = new ArrayList<>();
        for (int i = 0; i < indicated.size(); i++) {

            if (indication.get(i).getIndicated().getUserIndicated().equals(userLoggedIndicated.get())) {
                Form form = indication.get(i).getForm();
                var userIndication = new UserDto(indication.get(i).getUserIndication());
                if (form != null) {
                    var questionForms = questionFormRepository.findAllByFormId(form.getId());
                    QuestionFormListDto questionFormDto = null;
                    for (QuestionForm questionForm : questionForms) {
                        questionFormDto = new QuestionFormListDto(questionForm, userIndication);
                    }

                    questionFormResponseDtos.add(questionFormDto);

                }
            }

        }

        if(questionFormResponseDtos.isEmpty()) throw new NoSuchElementException("Question form not found for that user!");
        return questionFormResponseDtos;
    }
    public List<QuestionFormListDto> pendingFormToRespond(String userIndicated){

        var indication = indicationFormRepository.findAll();
        var questionAnswer = questionAnswerRepository.findAllByUserId(userIndicated);
        List<QuestionFormListDto> questionFormResponseDtos = new ArrayList<>();
        for (int i = 0; i < indication.size(); i++) {

            if (indication.get(i).getIndicated().getUserIndicated().getId().equals(userIndicated)) {
                System.out.println(indication.get(i).getIndicated().getUserIndicated().getId().equals(userIndicated));
                Form form = indication.get(i).getForm();
                var userIndication = new UserDto(indication.get(i).getUserIndication());
                if (form != null) {
                    var questionForms = questionFormRepository.findAllByFormId(form.getId());
                    QuestionFormListDto questionFormDto = null;
                    for (QuestionForm questionForm : questionForms) {
                        questionFormDto = new QuestionFormListDto(questionForm, userIndication);
                    }
                    questionFormResponseDtos.add(questionFormDto);

                }
            }

        }
        if(questionAnswer.isEmpty()) throw new NoSuchElementException("This user doesnt has pending form! Wait other user indicates you!");

        return questionFormResponseDtos;
    }

}
