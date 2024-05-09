package models;

import jakarta.enterprise.context.SessionScoped;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@SessionScoped
public class Toy implements Serializable {
    private int toy_id;
    private String toy_name;
    private String toy_category;
    private Double toy_price;
    private int toy_stock;
}
