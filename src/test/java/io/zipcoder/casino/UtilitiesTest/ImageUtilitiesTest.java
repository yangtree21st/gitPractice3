package io.zipcoder.casino.UtilitiesTest;

import io.zipcoder.casino.utilities.ImageUtilities;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.TreeMap;

public class ImageUtilitiesTest {

    ImageUtilities imageUtilities;

    @Before
    public void generateImageUtilities() {
        imageUtilities = new ImageUtilities();
    }

    @Test
    public void constructorTestSize() {
        //given
        Integer expected = 52;
        //when
        Integer actual = imageUtilities.getCardImageTreeMap().size();
        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setTreeMapSmall(){
        //given
        TreeMap<String, String> expectedTreeMap = new TreeMap<>();
        //when
        String constantPath = "File:src/main/java/io/zipcoder/casino/Images/";
        expectedTreeMap.put("HEARTSACE", constantPath + "AH.png");
        expectedTreeMap.put("HEARTSTWO", constantPath + "2H.png");
        expectedTreeMap.put("HEARTSTHREE", constantPath + "3H.png");
        expectedTreeMap.put("HEARTSFOUR", constantPath + "4H.png");
        expectedTreeMap.put("HEARTSFIVE", constantPath + "5H.png");
        expectedTreeMap.put("HEARTSSIX", constantPath + "6H.png");
        expectedTreeMap.put("HEARTSSEVEN", constantPath + "7H.png");
        expectedTreeMap.put("HEARTSEIGHT", constantPath + "8H.png");
        expectedTreeMap.put("HEARTSNINE", constantPath + "9H.png");
        expectedTreeMap.put("HEARTSTEN", constantPath + "10H.png");
        expectedTreeMap.put("HEARTSJACK", constantPath + "JH.png");
        expectedTreeMap.put("HEARTSQUEEN", constantPath + "QH.png");
        expectedTreeMap.put("HEARTSKING", constantPath + "KH.png");
        imageUtilities.setCardImageTreeMap(expectedTreeMap);
        TreeMap<String,String> actualTreeMap = imageUtilities.getCardImageTreeMap();

        //then

        Assert.assertEquals(expectedTreeMap,actualTreeMap);

    }

    @Test
    public void setTreeMap() {
        //given
        TreeMap<String, String> cardImageTreeMap = new TreeMap<>();
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

    }


}
