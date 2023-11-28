package org.example;


import com.google.inject.servlet.RequestScoped;

import java.text.MessageFormat;
import java.util.UUID;
import java.util.logging.Logger;

@RequestScoped
public class Message {

    private final static Logger LOGGER = Logger.getLogger(Message.class.getName());

    private final String uuid;

    public Message() {
        this.uuid = UUID.randomUUID().toString();
    }

    public String getMessage() {
        LOGGER.info(MessageFormat.format("Requested {0}", this.uuid));

        return "Hello World!";
    }
}
