package com.amazon.asksdk.session;

public class InterviewRequest {
    public static final InterviewRequest BEGIN = new InterviewRequest("QSTART", "");
    
    private final String questionId;
    private final String answerText;
    
    public InterviewRequest(String questionId, String answerText) {
        this.questionId = questionId;
        this.answerText = answerText;
    }

    public String questionId() {
        return questionId;
    }

    public String answerText() {
        return answerText;
    }
    
}
