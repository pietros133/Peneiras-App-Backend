package peneiras_app.dto;

import peneiras_app.entity.enums.DominantFoot;
import peneiras_app.entity.enums.Position;

import java.time.LocalDate;

public class PlayerUpdateDTO {

    private String name;
    private String email;
    private LocalDate birthDate;
    private Position position;
    private DominantFoot dominantFoot;
    private Integer heightCm;
    private String userImg;

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

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }
}