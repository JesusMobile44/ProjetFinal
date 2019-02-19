package composantes;

public class ComposanteActivable  extends Composante{

    private boolean active;

    public ComposanteActivable() {
        this.active = true;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
