package stepDefinitions;


import cucumber.api.java.Before;

public class Hooks {
    @Before("@DeletePlace")
    public void beforeScenario() throws Throwable {
        StepDefinitions m= new StepDefinitions();
        if(m.place_id==null){
        m.add_place_payload_with_something_something_something("Sabha","English","202, Saveria Livinston, Bangalore");
        m.user_calls_something_with_http_request("addPlaceAPI","POST");
        m.verifyPlace_idCreatedMapsToUsingGetPlaceAPI("Sabha","getPlaceAPI");
        }
    }

}
