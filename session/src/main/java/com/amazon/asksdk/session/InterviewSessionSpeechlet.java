package com.amazon.asksdk.session;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.ui.PlainTextOutputSpeech;

public class InterviewSessionSpeechlet implements SpeechletV2 {

    private final InterviewBoundary interviewBoundary;
    
    public InterviewSessionSpeechlet(InterviewBoundary interviewBoundary) {
        this.interviewBoundary = interviewBoundary;
    }

    @Override
    public void onSessionStarted(SpeechletRequestEnvelope<SessionStartedRequest> requestEnvelope) {
        
    }

    @Override
    public SpeechletResponse onLaunch(SpeechletRequestEnvelope<LaunchRequest> requestEnvelope) {
        return null;
    }

    @Override
    public SpeechletResponse onIntent(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
       return handleStartInterview(requestEnvelope);
    }

    private SpeechletResponse handleStartInterview(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
        if ("StartInterviewIntent".equals(requestEnvelope.getRequest().getIntent().getName())) {
             return presentResponseWith(interviewBoundary.handle(InterviewRequest.BEGIN).text());
            
        }
        return presentResponseWith("I don't what you're talking about!");
    }

    private SpeechletResponse presentResponseWith(String questionText) {
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(questionText);
        SpeechletResponse response = new SpeechletResponse();
        response.setOutputSpeech(speech);
        return response;
    }

    @Override
    public void onSessionEnded(SpeechletRequestEnvelope<SessionEndedRequest> requestEnvelope) {
        
    }

}
