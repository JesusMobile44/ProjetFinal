package autre;

import javafx.scene.image.Image;

import java.util.HashMap;

public class ImagesContainer {

    private static HashMap <String, Image> hashMapImage = new HashMap<>();

    public ImagesContainer() {
        for (int i=0; i<2; i++){
            hashMapImage.put("ampèremètre ("+(i+1)+").png", new Image("autre/images/amperemètre (" +(i+1)+").png"));
        }
        for (int i=0; i<4; i++){
            hashMapImage.put("ampoule ("+(i+1)+").png", new Image("autre/images/ampoule (" +(i+1)+").png"));
        }
        for (int i=0; i<2; i++){
            hashMapImage.put("condensateur ("+(i+1)+").png", new Image("autre/images/condensateur (" +(i+1)+").png"));
        }
        for (int i=0; i<4; i++){
            hashMapImage.put("diode ("+(i+1)+").png", new Image("autre/images/diode (" +(i+1)+").png"));
        }
        for (int i=0; i<11; i++){
            hashMapImage.put("fil ("+(i+1)+").png", new Image("autre/images/fil (" +(i+1)+").png"));
        }
        for (int i=0; i<2; i++){
            hashMapImage.put("fusible ("+(i+1)+").png", new Image("autre/images/fusible (" +(i+1)+").png"));
        }
        for (int i=0; i<2; i++){
            hashMapImage.put("haut-parleur ("+(i+1)+").png", new Image("autre/images/haut-parleur (" +(i+1)+").png"));
        }
        for (int i=0; i<4; i++){
            hashMapImage.put("mise à terre ("+(i+1)+").png", new Image("autre/images/mise à terre (" +(i+1)+").png"));
        }
        for (int i=0; i<2; i++){
            hashMapImage.put("ohmmètre ("+(i+1)+").png", new Image("autre/images/ohmètre (" +(i+1)+").png"));
        }
        for (int i=0; i<4; i++){
            hashMapImage.put("résisteur ("+(i+1)+").png", new Image("autre/images/resisteur (" +(i+1)+").png"));
        }
        for (int i=0; i<4; i++){
            hashMapImage.put("source ("+(i+1)+").png", new Image("autre/images/source (" +(i+1)+").png"));
        }
        for (int i=0; i<8; i++){
            hashMapImage.put("switch ("+(i+1)+").png", new Image("autre/images/switch ("+(i+1)+").png"));
        }
        for (int i=0; i<2; i++){
            hashMapImage.put("voltmètre ("+(i+1)+").png", new Image("autre/images/voltmètre ("+(i+1)+").png"));
        }
        for (int i=0; i<4; i++){
            hashMapImage.put("interrupteur ("+(i+1)+").png", new Image("autre/images/interrupteur ("+(i+1)+").png"));
        }

        hashMapImage.put("ampèremètre.png", new Image("autre/images/amperemètre.png"));
        hashMapImage.put("ampoule.png", new Image("autre/images/ampoule.png"));
        hashMapImage.put("condensateur.png", new Image("autre/images/condensateur.png"));
        hashMapImage.put("diode.png", new Image("autre/images/diode.png"));
        hashMapImage.put("fil.png", new Image("autre/images/fil.png"));
        hashMapImage.put("fusible.png", new Image("autre/images/fusible.png"));
        hashMapImage.put("haut-parleur.png", new Image("autre/images/haut-parleur.png"));
        hashMapImage.put("interrupteur.png", new Image("autre/images/interrupteur.png"));
        hashMapImage.put("mise à terre.png", new Image("autre/images/mise à terre.png"));
        hashMapImage.put("ohmmètre.png", new Image("autre/images/ohmètre.png"));
        hashMapImage.put("résisteur.png", new Image("autre/images/resisteur.png"));
        hashMapImage.put("source.png", new Image("autre/images/source.png"));
        hashMapImage.put("switch.png", new Image("autre/images/switch.png"));
        hashMapImage.put("voltmètre.png", new Image("autre/images/voltmètre.png"));
        hashMapImage.put("sandbox.png", new Image("autre/images/sandbox.png"));
        hashMapImage.put("vide.png", new Image("autre/images/vide.png"));
        hashMapImage.put("transparent.png", new Image("autre/images/transparent.png"));
        hashMapImage.put("back (1).png", new Image("autre/images/back (1).png"));

    }

    public static HashMap<String, Image> getHashMapImage() {
        return hashMapImage;
    }

    public static void setHashMapImage(HashMap<String, Image> hashMapImage) {
        ImagesContainer.hashMapImage = hashMapImage;
    }
}
