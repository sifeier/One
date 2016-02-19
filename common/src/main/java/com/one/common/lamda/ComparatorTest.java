package com.one.common.lamda;

import java.util.Collections;
import java.util.List;

/**
 * Created by buke on 16/2/19.
 */
public class ComparatorTest {

    public static void main(String[] args) {
        List<Person> personList = Person.createShortList();

        Collections.sort(personList,
                (Person p1, Person p2) -> p1.getSurName().compareTo(p2.getSurName()));
    }

}
