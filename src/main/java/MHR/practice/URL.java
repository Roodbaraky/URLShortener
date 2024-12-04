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
    @Id
    @GeneratedValue
    private Long id;
    @Column
    @NotNull
    private String rawURL;

    @Column
    @NotNull
    private String shortURL;
}
