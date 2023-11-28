package org.example;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.servlet.ServletScopes;

import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MessageService {

    private final Provider<Message> messageProvider;

    @Inject
    public MessageService(final Provider<Message> messageProvider) {
        this.messageProvider = messageProvider;
    }

    public String get() {
        try {
            final var executorService = Executors.newFixedThreadPool(1);

            final var streamBuilder = Stream.<String>builder();
            streamBuilder.accept(this.messageProvider.get().getMessage());

            final var callable = ServletScopes.transferRequest(() -> this.messageProvider.get().getMessage());
            streamBuilder.accept(executorService.submit(callable).get());

            return streamBuilder.build()
                    .collect(Collectors.joining(" "));
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }
}
