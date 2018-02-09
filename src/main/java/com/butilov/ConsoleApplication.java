package com.butilov;

import com.butilov.controller.ConsoleController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Dmitry Butilov
 * on 09.02.18.
 */
public class ConsoleApplication {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);

        ConsoleController consoleController = context.getBean(ConsoleController.class);
        consoleController.convert();
    }
}