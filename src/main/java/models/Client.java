package models;

import jakarta.enterprise.context.SessionScoped;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder //Patrón de diseños para crear objetos de forma más fácil.
@SessionScoped
public class Client implements Serializable {
    private int client_cedula;
    private String client_name;
    private Date client_age;
}
