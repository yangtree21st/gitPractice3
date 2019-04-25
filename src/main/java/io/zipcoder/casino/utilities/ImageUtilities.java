package io.zipcoder.casino.utilities;

import javafx.scene.image.Image;

import java.util.TreeMap;

public class ImageUtilities {

    public static TreeMap<String, String> cardImageTreeMap = new TreeMap<>();
    //public static TreeMap<Integer,String> diceImageTreeMap = new TreeMap<>();


    public ImageUtilities() {

        //CARD PATH INITIALIZATION
        String constantPath = "File:src/main/java/io/zipcoder/casino/Images/";

        cardImageTreeMap.put("HEARTSACE", constantPath + "AH.png");
        cardImageTreeMap.put("HEARTSTWO", constantPath + "2H.png");
        cardImageTreeMap.put("HEARTSTHREE", constantPath + "3H.png");
        cardImageTreeMap.put("HEARTSFOUR", constantPath + "4H.png");
        cardImageTreeMap.put("HEARTSFIVE", constantPath + "5H.png");
        cardImageTreeMap.put("HEARTSSIX", constantPath + "6H.png");
        cardImageTreeMap.put("HEARTSSEVEN", constantPath + "7H.png");
        cardImageTreeMap.put("HEARTSEIGHT", constantPath + "8H.png");
        cardImageTreeMap.put("HEARTSNINE", constantPath + "9H.png");
        cardImageTreeMap.put("HEARTSTEN", constantPath + "10H.png");
        cardImageTreeMap.put("HEARTSJACK", constantPath + "JH.png");
        cardImageTreeMap.put("HEARTSQUEEN", constantPath + "QH.png");
        cardImageTreeMap.put("HEARTSKING", constantPath + "KH.png");
        cardImageTreeMap.put("SPADESACE", constantPath + "AS.png");
        cardImageTreeMap.put("SPADESTWO", constantPath + "2S.png");
        cardImageTreeMap.put("SPADESTHREE", constantPath + "3S.png");
        cardImageTreeMap.put("SPADESFOUR", constantPath + "4S.png");
        cardImageTreeMap.put("SPADESFIVE", constantPath + "5S.png");
        cardImageTreeMap.put("SPADESSIX", constantPath + "6S.png");
        cardImageTreeMap.put("SPADESSEVEN", constantPath + "7S.png");
        cardImageTreeMap.put("SPADESEIGHT", constantPath + "8S.png");
        cardImageTreeMap.put("SPADESNINE", constantPath + "9S.png");
        cardImageTreeMap.put("SPADESTEN", constantPath + "10S.png");
        cardImageTreeMap.put("SPADESJACK", constantPath + "JS.png");
        cardImageTreeMap.put("SPADESQUEEN", constantPath + "QS.png");
        cardImageTreeMap.put("SPADESKING", constantPath + "KS.png");
        cardImageTreeMap.put("DIAMONDSACE", constantPath + "AD.png");
        cardImageTreeMap.put("DIAMONDSTWO", constantPath + "2D.png");
        cardImageTreeMap.put("DIAMONDSTHREE", constantPath + "3D.png");
        cardImageTreeMap.put("DIAMONDSFOUR", constantPath + "4D.png");
        cardImageTreeMap.put("DIAMONDSFIVE", constantPath + "5D.png");
        cardImageTreeMap.put("DIAMONDSSIX", constantPath + "6D.png");
        cardImageTreeMap.put("DIAMONDSSEVEN", constantPath + "7D.png");
        cardImageTreeMap.put("DIAMONDSEIGHT", constantPath + "8D.png");
        cardImageTreeMap.put("DIAMONDSNINE", constantPath + "9D.png");
        cardImageTreeMap.put("DIAMONDSTEN", constantPath + "10D.png");
        cardImageTreeMap.put("DIAMONDSJACK", constantPath + "JD.png");
        cardImageTreeMap.put("DIAMONDSQUEEN", constantPath + "QD.png");
        cardImageTreeMap.put("DIAMONDSKING", constantPath + "KD.png");
        cardImageTreeMap.put("CLUBSACE", constantPath + "AC.png");
        cardImageTreeMap.put("CLUBSTWO", constantPath + "2C.png");
        cardImageTreeMap.put("CLUBSTHREE", constantPath + "3C.png");
        cardImageTreeMap.put("CLUBSFOUR", constantPath + "4C.png");
        cardImageTreeMap.put("CLUBSFIVE", constantPath + "5C.png");
        cardImageTreeMap.put("CLUBSSIX", constantPath + "6C.png");
        cardImageTreeMap.put("CLUBSSEVEN", constantPath + "7C.png");
        cardImageTreeMap.put("CLUBSEIGHT", constantPath + "8C.png");
        cardImageTreeMap.put("CLUBSNINE", constantPath + "9C.png");
        cardImageTreeMap.put("CLUBSTEN", constantPath + "10C.png");
        cardImageTreeMap.put("CLUBSJACK", constantPath + "JC.png");
        cardImageTreeMap.put("CLUBSQUEEN", constantPath + "QC.png");
        cardImageTreeMap.put("CLUBSKING", constantPath + "KC.png");

        //DICE PATH INITIALIZATION

    }

    public static Image cardImageCreator(String keyString) {
        String pathValue = cardImageTreeMap.get(keyString);
        return new Image(pathValue, 180, 240, true, true);
    }

    public static TreeMap<String, String> getCardImageTreeMap() {
        return cardImageTreeMap;
    }

    public static void setCardImageTreeMap(TreeMap<String, String> cardImageTreeMap) {
        ImageUtilities.cardImageTreeMap = cardImageTreeMap;
    }


}
