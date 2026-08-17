package peneiras_app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import peneiras_app.entity.enums.Categoria;

import java.util.UUID;

@Entity
@Table(name = "clube")
public class Clube {

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
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categoria category;

    @OneToOne
    @JoinColumn(name = "endereco_id", nullable = false)
    private Endereco address;

    @Column(nullable = false)
    private String clubeImg;

    @NotNull
    @Column(nullable = false)
    private String phone;

    private String whatsapp;

    private String instagramAccount;

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

    public Categoria getCategory() {
        return category;
    }

    public void setCategory(Categoria category) {
        this.category = category;
    }

    public Endereco getAddress() {
        return address;
    }

    public String getClubeImg() {
        return clubeImg;
    }

    public void setClubeImg(String clubeImg) {
        this.clubeImg = clubeImg;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getInstagramAccount() {
        return instagramAccount;
    }

    public void setInstagramAccount(String instagramAccount) {
        this.instagramAccount = instagramAccount;
    }
}