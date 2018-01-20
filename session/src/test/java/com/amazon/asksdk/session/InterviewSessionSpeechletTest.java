package com.amazon.asksdk.session;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.*;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.ui.PlainTextOutputSpeech;

@RunWith(MockitoJUnitRunner.class)
public class InterviewSessionSpeechletTest {
    private @Mock InterviewBoundary boundary;
    private InterviewSessionSpeechlet speechlet;
    private SpeechletRequestEnvelope<IntentRequest> requestEnvelope;
    private IntentRequest.Builder intentRequest;
    private Session.Builder session;
    private SpeechletResponse response;
    
    @Before
    public void setUp() {
        speechlet = new InterviewSessionSpeechlet(boundary);
    }
    
    @Test
    public void providesInterviewInteraction() throws Exception {
        havingASessionWithIntent("StartInterviewIntent");
        when(boundary.handle(InterviewRequest.BEGIN)).thenReturn(new Question("Q1", "Who cut your hair?"));
        
        response = speechlet.onIntent(requestEnvelope);
        
        assertResponseTextIs("Who cut your hair?");
    }

    @Test
    public void handlesUnknownIntentIntent() throws Exception {
        havingASessionWithIntent("UnknownIntent");
        
        response = speechlet.onIntent(requestEnvelope);
        
        assertResponseTextIs("I don't what you're talking about!");
    }
    
    private void havingASessionWithIntent(String intent) {
        intentRequest = IntentRequest.builder()
                                     .withIntent(Intent.builder()
                                                       .withName(intent)
                                                       .withSlot(Slot.builder().withName("Name").withValue("Alexa").build())
                                                       .build())
                                     .withRequestId("id");
        
        session = Session.builder().withSessionId("id").withUser(User.builder().withUserId("a").build());
        requestEnvelope = SpeechletRequestEnvelope.<IntentRequest>builder().withRequest(intentRequest.build())
                                                                           .withSession(session.build()).build();
    }
    
    private void assertResponseTextIs(String responseText) {
        assertThat(((PlainTextOutputSpeech) response.getOutputSpeech()).getText()).isEqualTo(responseText);
    }
    
}
