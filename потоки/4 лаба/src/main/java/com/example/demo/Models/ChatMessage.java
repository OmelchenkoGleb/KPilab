package com.example.demo.Models;

import lombok.Builder;
import lombok.Getter;

import java.io.File;


@Builder
@Getter
public class ChatMessage {
    private MessageType type;

    private String content;

    private File file;

    private String sender;

    private String time;
}
