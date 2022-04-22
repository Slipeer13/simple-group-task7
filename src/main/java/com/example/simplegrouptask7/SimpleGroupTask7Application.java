//todo Ошибка в именовании пакета. В итоге, приложение не запускается.
//      Так же, не применяется camelCase в названиях пакетов.
//      Присутствуют лишние импорты в классах проекта.
package com.example.simplegrouptask7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimpleGroupTask7Application {

    public static void main(String[] args) {
        SpringApplication.run(SimpleGroupTask7Application.class, args);
    }

}
