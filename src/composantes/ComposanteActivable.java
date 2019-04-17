package composantes;

public class ComposanteActivable extends Composante {

    private boolean active;

    ComposanteActivable() {
        this.active = false;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
