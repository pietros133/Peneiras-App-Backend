package peneiras_app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import peneiras_app.entity.enums.DominantFoot;
import peneiras_app.entity.enums.Position;

import java.time.LocalDate;

public class PlayerCreateDTO {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, max = 72)
    private String password;

    @NotNull
    private LocalDate birthDate;

    @NotNull
    private Position position;

    @NotNull
    private DominantFoot dominantFoot;

    @NotNull
    private Integer heightCm;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public DominantFoot getDominantFoot() {
        return dominantFoot;
    }

    public void setDominantFoot(DominantFoot dominantFoot) {
        this.dominantFoot = dominantFoot;
    }

    public Integer getHeightCm() {
        return heightCm;
    }

    public void setHeightCm(Integer heightCm) {
        this.heightCm = heightCm;
    }

}