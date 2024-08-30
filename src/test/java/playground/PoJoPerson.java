package playground;

public class PoJoPerson {
    //POJO(plain old java object) or DTO

    private String name;
    private int length;



    public PoJoPerson(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLength(int length) {
        this.length = length;
    }

}
