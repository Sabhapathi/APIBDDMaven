package resources;

import pojo.LocDetails;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {
    public LocDetails addPlacePayload(String name, String ln, String address){
    LocDetails p = new LocDetails();
        p.setAccuracy(50);
        p.setAddress(address);
        p.setLanguage(ln);
        p.setName(name);
        p.setPhone_number("4085697807");
        p.setWebsite("www.aspenwoodsapt.net");
    List<String> myList = new ArrayList<String>();
        myList.add("apartment");
        myList.add("park");
        p.setTypes(myList);

    Location l = new Location();
        l.setLat(44.828336);
        l.setLng(-93.150110);
        p.setLocation(l);
        return p;
    }

    public String deletePlacePayload(String placeId){
        return "{\"place_id\": \""+placeId+"\"}";
    }

}
