import java.util.Random;

public class Dice {
    private final Random rand;
    private final int numFaces;
    private int faceValue;
    
    public Dice(int _numFaces) {
        rand = new Random();
        numFaces = _numFaces;
        roll();
    }
    public void roll() {
        int curr_faceValue = 1 + rand.nextInt(numFaces);
        setFaceValue(curr_faceValue);
    }
    
    private void setFaceValue (int value) {
        if (value <= numFaces)
            faceValue = value;
    }
    
    public int getFaceValue() {
        return faceValue;
    }
    
    public int getNumFaces() {
        return numFaces;
    }
    
    public String toString() {
        return "number of Faces " + numFaces + "current face value " + faceValue;
    }
}