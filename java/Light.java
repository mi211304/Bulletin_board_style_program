public class Light {
    public static void main(String[] args) {

        double lightSpeed = 300000.0;

        long day = 24L * 60L * 60L; 

        long distance = (long) (lightSpeed * day); 

        int distanceInt = (int) distance;
        
        System.out.println("光が1日に進む距離は" + distanceInt + "kmです。");
        System.out.println("光が1日に進む距離は" + distance + "kmです。");
        
    }
}
