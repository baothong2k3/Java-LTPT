package entity;

public class Director {
    private String derector;

    public Director(String derector) {
        this.derector = derector;
    }

    public Director() {
    }

    public String getDerector() {
        return derector;
    }

    public void setDerector(String derector) {
        this.derector = derector;
    }

    @Override
    public String toString() {
        return "Derector{" +
                "derector='" + derector + '\'' +
                '}';
    }
}
