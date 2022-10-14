package com.flight.qrpinger.domain

import spock.lang.Specification

class UserTest extends Specification {
    def "A user must have an email"() {
        setup:
//        user = User.builder().firstName("Bob").build()
        user = new com.flight.qrpinger.domain.User()
//        User user=null

        when:
        v = user.getEmail()

        then:
        v!=null
    }

    def "A is not B"() {
        expect:
        'A'!='B'
    }
}
