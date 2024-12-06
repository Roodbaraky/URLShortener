package MHR.practice;

import jakarta.validation.constraints.NotNull;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema
public class URLTO {

    @NotNull
    private String rawURL;



    @NotNull
    private String shortURL;

    public String getRawURL() {
        return rawURL;
    }

    public void setRawURL(final String rawURL) {
        this.rawURL = rawURL;
    }

    public String getShortURL() {
        return shortURL;
    }

    public void setShortURL(final String shortURL) {
        this.shortURL = shortURL;
    }

}
