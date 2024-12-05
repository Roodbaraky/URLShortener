package MHR.practice;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Entity
@Table(name = "T_URL")
@Schema
public class URL {

    @Column
    @NotNull
    @Id
    private String rawURL;

    @Column
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
