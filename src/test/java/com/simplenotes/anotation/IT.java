package com.simplenotes.anotation;

import com.simplenotes.SimpleNotesApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest()
@Profile("test")
@PropertySource("classpath:application.properties")
public @interface IT {
}
