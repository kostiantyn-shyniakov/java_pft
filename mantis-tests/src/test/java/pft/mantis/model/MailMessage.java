package pft.mantis.model;

/**
 * Created by kshyniakov on 20.01.2017.
 */
public class MailMessage {

    public String to;
    public String text;

    public MailMessage(String to, String text) {
        this.to = to;
        this.text = text;
    }
}
