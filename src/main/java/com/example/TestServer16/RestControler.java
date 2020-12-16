package com.example.TestServer16;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
public class RestControler {
    @CrossOrigin(origins = "http://localhost:3000")

    @RequestMapping(value = "/Sasha", method = RequestMethod.GET)
    public TestObg.TestObj2[] respMetod(){
        System.out.println("--------------------------");
        TestObg.TestObj2 testObg = new TestObg.TestObj2("Саша"," Java Developer") ;
        TestObg.TestObj2 testObg2 = new TestObg.TestObj2("Гриша"," JS Developer");
        TestObg.TestObj2 [] arr = {testObg,testObg2};
        return arr ;
    }

}
