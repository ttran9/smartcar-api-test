package tran.example.smartcartest.utility.common;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/*
 * TODO: create a better name for this class.
 *  This class just holds beans that may possibly be used in more than one class.
 */
@Component
public class BeanConfig {
    @Bean
    public static BCryptPasswordEncoder passwordEncryptor() {
        return new BCryptPasswordEncoder();
    }
}
