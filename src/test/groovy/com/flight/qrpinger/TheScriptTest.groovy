package com.flight.qrpinger;

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

import spock.lang.Specification
import spock.lang.Unroll

import com.flight.qrpinger.controller.UserController

@SpringBootTest
class TheScriptTest extends Specification {

    @Autowired (required = true)
    private UserController userController

    def "when context is loaded then all expected beans are created"() {
        expect: "the WebController is created"
        userController!=null
    }

    def "when 1==1 then pass"() {
        expect: "1==1"
        1==1
    }

}