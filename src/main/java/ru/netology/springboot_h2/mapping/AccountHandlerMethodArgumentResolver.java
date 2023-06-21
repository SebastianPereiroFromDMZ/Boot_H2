package ru.netology.springboot_h2.mapping;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import ru.netology.springboot_h2.model.Account;


public class AccountHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {


    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(Account.class);
    }


    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception {
        String user = nativeWebRequest.getParameter("user");
        String password = nativeWebRequest.getParameter("password");

        if (isNotSet(user)) {
            user = "defaultUser";
        }

        if (isNotSet(password)) {
            password = "defaultPassword";
        }

        return new Account(user, password);
    }

    private boolean isNotSet(String value) {
        return value == null;
    }
}

