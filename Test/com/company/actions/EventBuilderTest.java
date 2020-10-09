package com.company.actions;

import com.company.models.Hotel;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class EventBuilderTest {
    EventBuilder eventBuilder = new EventBuilder(new File("src/com/company/files/events3.json"));
    Hotel hotel = new Hotel();
    Stage stage;

    @Test
    public void shout() throws IOException {
        hotel.start(stage);
        this.eventBuilder.readJson(hotel);


    }
}