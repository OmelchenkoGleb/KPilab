package com.example.save.savepo.Controller;

import com.example.save.savepo.Dao.Check;
import com.example.save.savepo.Dao.Filework;
import com.example.save.savepo.Dao.Vizhinera;
import com.example.save.savepo.Model.ReCaptchaResponce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class MainController {

    @Autowired
    Check check;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/")
    public String main(Model model){
        if (!Check.check()) {
            String massage = "Пробный срок окончен. Введите ключи активации.";
            model.addAttribute("massage",massage);
            return "/lab1/index";
        } else {
            return "/lab1/start";
        }
    }

    @PostMapping("/check")
    public String key(Model model, @RequestParam String key, @RequestParam(name = "g-recaptcha-response") String capthaResponce){
        Vizhinera v = new Vizhinera();
        String activation = "ixfd";
        StringBuilder action = v.encryption(key,"bla");
        if (!action.toString().equals(activation)){
            String massage = "Ключ неверный.";
            model.addAttribute("massage", massage);
            return "/lab1/index";
        }

        String url = "https://www.google.com/recaptcha/api/siteverify";
        String params = "?secret=6Ld7Z0kfAAAAAKTIMbRpCbaThtRX2C8OGTitgAgQ&response="+capthaResponce;
        ReCaptchaResponce reCaptchaResponce = restTemplate.exchange(url+params, HttpMethod.POST, null, ReCaptchaResponce.class).getBody();
        if (!reCaptchaResponce.isSuccess()) {
            String massage = "Не прошли проверку на бота.";
            model.addAttribute("massage", massage);
            return "/lab1/index";
        }
        model.addAttribute("option", Filework.option());
        return "/lab1/start";
    }
}
