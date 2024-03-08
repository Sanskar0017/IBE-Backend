package com.team14.IBE.TestController;

import com.team14.IBE.Service.GraphQLTestingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GraphQLController {
    GraphQLTestingService graphQLTestingService;
    @Autowired
    public GraphQLController(GraphQLTestingService graphQLTestingService){
        this.graphQLTestingService = graphQLTestingService;
    }
    @GetMapping("/sampletest")
    public ResponseEntity<String> sampleTest(){
        return ResponseEntity.ok("Hello This is Sanskar!!");
    }
    @GetMapping("/getrooms")
    public ResponseEntity<String> getRoomsFromGraphQL(){
        return graphQLTestingService.getRooms();
    }
}
