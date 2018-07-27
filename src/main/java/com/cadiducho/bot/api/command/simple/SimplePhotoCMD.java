package com.cadiducho.bot.api.command.simple;

import com.cadiducho.telegrambotapi.Chat;
import com.cadiducho.telegrambotapi.Message;
import com.cadiducho.telegrambotapi.User;
import com.cadiducho.telegrambotapi.exception.TelegramException;
import java.time.Instant;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Comando que responde con una foto
 * @author Cadiducho
 */
public class SimplePhotoCMD extends SimpleCommand {

    private final List<String> photos;

    public SimplePhotoCMD(List<String> aliases, String photoId) {
        this(aliases, ReplyPattern.TO_ANSWERED, Arrays.asList(photoId));
    }
    
    public SimplePhotoCMD(List<String> aliases, List<String> photos) {
        this(aliases, ReplyPattern.TO_ANSWERED, photos);
    }
    
    public SimplePhotoCMD(List<String> aliases, ReplyPattern replyPattern, List<String> photos) {
        super(aliases, replyPattern);
        this.photos = photos;
    }
    
    @Override
    public void execute(final Chat chat, final User from, final String label, final String[] args, final Integer messageId, final Message replyingTo, Instant instant) throws TelegramException {
        Random rand = new Random(instant.getNano());
        String photoId = photos.get(rand.nextInt(photos.size()));
        
        getBot().sendPhoto(chat.getId(), photoId, null, false, replyTheCommandTo(messageId, replyingTo.getMessage_id()), null);
    }
}
