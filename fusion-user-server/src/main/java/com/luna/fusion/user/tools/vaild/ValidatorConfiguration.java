package com.luna.fusion.user.tools.vaild;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangshenxiang
 */
@Configuration
public class ValidatorConfiguration {
    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
            .configure()
            .addProperty("hibernate.validator.fail_fast", "false")
            .buildValidatorFactory();
        return validatorFactory.getValidator();
    }
}