package com.tcarev.tests.testtgbot.persistance;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Chat message repository.
 */
public interface PersistedChatMessageRepo extends CrudRepository<PersistedChatMessage, Long> {

    /**
     * Query by chatId.
     */
    List<PersistedChatMessage> findAllByChatId(Long chatId);

    /**
     * Delete all chat messages for custom chatId.
     */
    @Transactional
    void deleteByChatId(Long chatId);
}
