package br.com.etqpadrao.etqpadrao.models;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Calendar;

@Entity
public class LoginLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String login;

    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private Calendar dtLogin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
        setaDtLogin();
    }

    private void setaDtLogin(){
        Calendar now = Calendar.getInstance();
        this.dtLogin=now;
    }
}
