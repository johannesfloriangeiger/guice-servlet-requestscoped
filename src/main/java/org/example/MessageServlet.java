package org.example;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Singleton
public class MessageServlet extends HttpServlet {

    private final MessageService messageService;

    @Inject
    public MessageServlet(final MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    protected void doGet(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws IOException {
        final var message = this.messageService.get();

        httpServletResponse.setContentType(MediaType.TEXT_PLAIN);
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        httpServletResponse.setContentLength(message.length());

        try (final var printWriter = httpServletResponse.getWriter()) {
            printWriter.write(message);
        }
    }
}
