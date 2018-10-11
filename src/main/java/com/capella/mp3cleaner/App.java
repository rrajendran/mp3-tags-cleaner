package com.capella.mp3cleaner;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

import com.capella.mp3cleaner.services.FileNameCleaner;
import com.capella.mp3cleaner.services.TagCleaner;
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
public class App {
    public static void main(String[] args) {
        File file = new File("/Users/ramesh.rajendran/Downloads/ratsasan");
        TagCleaner tagCleaner = new FileNameCleaner();
        Stream.of(file.listFiles()).forEach(f -> {
            try {
                tagCleaner.clean(f);
                System.out.println("---------------------");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidDataException e) {
                e.printStackTrace();
            } catch (UnsupportedTagException e) {
                e.printStackTrace();
            } catch (NotSupportedException e) {
                e.printStackTrace();
            }
        });
    }


}
