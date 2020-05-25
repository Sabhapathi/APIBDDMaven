package resources;

//enum is special class in java which has collection of constants and methods
public enum APIUris {
    addPlaceAPI("/maps/api/place/add/json"),
    getPlaceAPI("/maps/api/place/get/json"),
    deletePlaceAPI("/maps/api/place/delete/json");

    private String resourceUri;

    APIUris(String resourceUri) {
        this.resourceUri=resourceUri;
    }

    public String getResourceUri(){
        return resourceUri;
    }
}
