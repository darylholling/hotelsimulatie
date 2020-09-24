package com.company.person;

import com.company.actions.Event;
import com.company.actions.Move;
import com.company.hotel.Position;

public interface Person {

    void person (Position position, Event events, Move move);
}
