package com.company.models;

import java.io.File;
import java.io.IOException;

public interface Parser {

    JsonInfo[] readJson(File fileName) throws IOException;
}