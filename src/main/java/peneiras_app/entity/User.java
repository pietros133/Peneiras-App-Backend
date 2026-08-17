package peneiras_app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import peneiras_app.entity.enums.PeDominante;
import peneiras_app.entity.enums.Posicao;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String email;

    @NotBlank
    @Size(min = 8, max = 72)
    @Column(nullable = false)
    private String password;

    @NotNull
    @Column(nullable = false)
    private LocalDate birthDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Posicao position;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PeDominante dominantFoot;

    @NotNull
    @Column(nullable = false)
    private Integer heightCm;

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco address;

    @Column
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

    public Posicao getPosition() {
        return position;
    }

    public void setPosition(Posicao position) {
        this.position = position;
    }

    public PeDominante getDominantFoot() {
        return dominantFoot;
    }

    public void setDominantFoot(PeDominante dominantFoot) {
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

    public Endereco getAddress() {
        return address;
    }
}