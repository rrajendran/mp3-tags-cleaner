package com.capella.mp3cleaner.services;

import java.io.File;
import java.io.IOException;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.NotSupportedException;
import com.mpatric.mp3agic.UnsupportedTagException;

/**
 * Copyright 2018 (c) Mastek UK Ltd
 * <p>
 * Created on : 03/10/2018
 *
 * @author Ramesh Rajendran
 */
public interface TagCleaner {

    /**
     * Clean the file
     * @param file
     */
    void clean(File file) throws IOException, InvalidDataException, UnsupportedTagException, NotSupportedException;
}
