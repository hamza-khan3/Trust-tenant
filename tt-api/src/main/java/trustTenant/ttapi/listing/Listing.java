package trustTenant.ttapi.listing;

public class Listing {
    private int propertyId;
    private int num;
    private int numBeds;
    private float numBaths;
    private String type;
    private int sqrFootage;

    public Listing() {

    }

    public Listing(int propertyId, int num, int numBeds, float numBaths, String type, int sqrFootage) {
        this.propertyId = propertyId;
        this.num = num;
        this.numBeds = numBeds;
        this.numBaths = numBaths;
        this.type = type;
        this.sqrFootage = sqrFootage;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getNumBeds() {
        return numBeds;
    }

    public void setNumBeds(int numBeds) {
        this.numBeds = numBeds;
    }

    public float getNumBaths() {
        return numBaths;
    }

    public void setNumBaths(float numBaths) {
        this.numBaths = numBaths;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSqrFootage() {
        return sqrFootage;
    }

    public void setSqrFootage(int sqrFootage) {
        this.sqrFootage = sqrFootage;
    }
}
