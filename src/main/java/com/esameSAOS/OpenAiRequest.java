package com.esameSAOS;

import java.util.List;
import java.util.ArrayList;

public class OpenAiRequest {

    private String model;

    private List<Message> messages;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}