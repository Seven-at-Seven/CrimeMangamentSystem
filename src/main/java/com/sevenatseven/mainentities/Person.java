
package com.sevenatseven.mainentities;

import org.w3c.dom.ls.LSOutput;

abstract public class Person {
private String firstName;
private String lastName;
private String nationalId;

   public Person (String firstName,String lastName, String nationalId) {
   this.firstName = firstName;
   this.lastName = lastName;
   this.nationalId = nationalId;
   }

    String getFirstName () {
       return firstName;
    }

    String getLastName () {
       return lastName;
    }

    String getNationalId () {
   return nationalId;
   }

}
