package io.zipcoder.casino.UtilitiesTest;

import io.zipcoder.casino.utilities.ImageUtilities;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.TreeMap;

public class ImageUtilitiesTest {

    ImageUtilities imageUtilities;
    ImageView imageView;

    @Before
    public void generateImageUtilities() {
        imageUtilities = new ImageUtilities();
        imageView = new ImageView();

    }

    @Test
    public void constructorTestSizeTest() {
        //given
        Integer expected = 52;
        //when
        Integer actual = imageUtilities.getCardImageTreeMap().size();
        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setTreeMapSmallTest(){
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
    public void getHeartAceFromTreeMapTest(){
        //given
        String expectedPath = "File:src/main/java/io/zipcoder/casino/Images/AH.png";
        //when
        String actualPath = imageUtilities.getCardImageTreeMap().get("HEARTSACE");
        //then
        Assert.assertEquals(expectedPath,actualPath);
    }

    @Test
    public void getSpadesKingFromTreeMapTest(){
        //given
        String expectedPath = "File:src/main/java/io/zipcoder/casino/Images/KS.png";
        //when
        String actualPath = imageUtilities.getCardImageTreeMap().get("SPADESKING");
        //then
        Assert.assertEquals(expectedPath,actualPath);
    }

    @Test
    public void checkNullForNonexsistentKeys(){
        //given
        //when
        String actualPath = imageUtilities.getCardImageTreeMap().get("SPADESKINGS");
        //then
        Assert.assertEquals(null,actualPath);
    }

    /*
   @Test
    public void createImageFromKey(){
        //given
       String keyString = "CLUBSTHREE";
       Double expectdHeight = 240.0;

       //when
       imageView = new ImageView(imageUtilities.cardImageCreator(keyString));
       Image actual = imageView.getImage();
       Double actualHeight = actual.getRequestedHeight();
       //then
       Assert.assertEquals(expectdHeight, actualHeight);
   }
   */

}
