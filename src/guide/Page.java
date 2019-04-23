package guide;

import autre.ImagesContainer;
import javafx.scene.image.Image;

public class Page {
    String nom;
    String description;
    Image image1;
    Image image2;
    Image formule;

    public Page(String nom, String description) {
        this.nom = nom;
        this.description = description;
        if (ImagesContainer.getHashMapImage().get(nom.toLowerCase() + ".png") == null)
            this.image1 = ImagesContainer.getHashMapImage().get("transparent.png");
        else
            this.image1 = ImagesContainer.getHashMapImage().get(nom.toLowerCase() + ".png");
        if (ImagesContainer.getHashMapImage().get(nom.toLowerCase() + " (1).png") == null)
            this.image1 = ImagesContainer.getHashMapImage().get("transparent.png");
        else
            this.image2 = ImagesContainer.getHashMapImage().get(nom.toLowerCase() + " (1).png");
        if (ImagesContainer.getHashMapImage().get(nom.toLowerCase() + "formule.png") == null)
            this.formule = ImagesContainer.getHashMapImage().get("transparent.png");
        else
            this.formule = ImagesContainer.getHashMapImage().get(nom.toLowerCase() + "formule.png");
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Image getImage1() {
        return image1;
    }

    public void setImage1(Image image1) {
        this.image1 = image1;
    }

    public Image getImage2() {
        return image2;
    }

    public void setImage2(Image image2) {
        this.image2 = image2;
    }

    public Image getFormule() {
        return formule;
    }

    public void setFormule(Image formule) {
        this.formule = formule;
    }
}
