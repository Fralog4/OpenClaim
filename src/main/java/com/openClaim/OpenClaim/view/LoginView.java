package com.openClaim.OpenClaim.view;


import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("/login")
@PageTitle("Login")
public class LoginView extends Div {


    public LoginView() {
        addClassName("login-view");

        LoginI18n baseForm=LoginI18n.createDefault();

        LoginI18n.Header header = new LoginI18n.Header();
        header.setTitle("Login");
        header.setDescription("Welcome to OpenClaim, please log in");
        baseForm.setHeader(header);

        LoginI18n.Form form = new LoginI18n.Form();
        form.setUsername("Username");
        form.setPassword("Password");
        form.setSubmit("Login");
        form.setForgotPassword("Forgot password?");

        baseForm.setForm(form);

        LoginI18n.ErrorMessage errorMessages = new LoginI18n.ErrorMessage();
        errorMessages.setUsername("Invalid username");
        errorMessages.setPassword("Invalid password");
        baseForm.setErrorMessage(errorMessages);

        LoginOverlay loginOverlay = new LoginOverlay();
        loginOverlay.setI18n(baseForm);
        loginOverlay.setAction("login");
        loginOverlay.setOpened(true);
        loginOverlay.addClassName("login-overlay");
        add(loginOverlay);



    }
}
