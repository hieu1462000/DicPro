package Commandline;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
public class CustomVoice {
    private String name;
    private Voice voice;
    public CustomVoice(String name)
    {
        this.voice=VoiceManager.getInstance().getVoice(name);
        this.voice.allocate();
    }
    public void say(String string)
    {
        this.voice.speak(string);
    }
}
