package com.capella.mp3cleaner.services;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.NotSupportedException;
import com.mpatric.mp3agic.UnsupportedTagException;

/**
 * Copyright 2018 (c) Mastek UK Ltd
 * <p>
 * Created on : 03/10/2018
 *
 * @author Ramesh Rajendran
 */
public class FileNameCleaner implements TagCleaner {
    public static final String BLANK_STRING = " ";
    public String[] stripValues = {"MassTamilan.com", "-"};
    @Override
    public void clean(File file) throws IOException, InvalidDataException, UnsupportedTagException, NotSupportedException {
        Mp3File mp3file = new Mp3File(file);

        if (mp3file.hasId3v2Tag()) {
            ID3v2 id3v2Tag = mp3file.getId3v2Tag();
            id3v2Tag.setTitle(cleanString(id3v2Tag.getTitle()));
            id3v2Tag.setTrack(cleanString(id3v2Tag.getTrack()));
            id3v2Tag.setArtist(cleanString(id3v2Tag.getArtist()));
            id3v2Tag.setAlbum(cleanString(id3v2Tag.getAlbum()));
            id3v2Tag.setYear(cleanString(id3v2Tag.getYear()));
            id3v2Tag.setGenreDescription("Rock");
            id3v2Tag.setComment(BLANK_STRING);
            id3v2Tag.setLyrics(BLANK_STRING);
            id3v2Tag.setComposer(cleanString(id3v2Tag.getComposer()));
            id3v2Tag.setPublisher(BLANK_STRING);
            id3v2Tag.setOriginalArtist(cleanString(id3v2Tag.getOriginalArtist()));
            id3v2Tag.setAlbumArtist(cleanString(id3v2Tag.getAlbumArtist()));
            id3v2Tag.setOriginalArtist(cleanString(id3v2Tag.getOriginalArtist()));
            id3v2Tag.setCopyright(BLANK_STRING);
            id3v2Tag.setUrl(BLANK_STRING);
            id3v2Tag.setEncoder(BLANK_STRING);


            System.out.println("Track: " + id3v2Tag.getTrack());
            System.out.println("Artist: " + id3v2Tag.getArtist());
            System.out.println("Title: " + id3v2Tag.getTitle());
            System.out.println("Album: " + id3v2Tag.getAlbum());
            System.out.println("Year: " + id3v2Tag.getYear());
            System.out.println("Genre: " + id3v2Tag.getGenre() + " (" + id3v2Tag.getGenreDescription() + ")");
            System.out.println("Comment: " + id3v2Tag.getComment());
            System.out.println("Lyrics: " + id3v2Tag.getLyrics());
            System.out.println("Composer: " + id3v2Tag.getComposer());
            System.out.println("Publisher: " + id3v2Tag.getPublisher());
            System.out.println("Original artist: " + id3v2Tag.getOriginalArtist());
            System.out.println("Album artist: " + id3v2Tag.getAlbumArtist());
            System.out.println("Copyright: " + id3v2Tag.getCopyright());
            System.out.println("URL: " + id3v2Tag.getUrl());
            System.out.println("Encoder: " + id3v2Tag.getEncoder());
            byte[] albumImageData = id3v2Tag.getAlbumImage();
            if (albumImageData != null) {
                System.out.println("Have album image data, length: " + albumImageData.length + " bytes");
                System.out.println("Album image mime type: " + id3v2Tag.getAlbumImageMimeType());
            }
        }

        String name = cleanString(file.getName());

        String newFileName = FilenameUtils.getFullPath(file.getPath()) + FilenameUtils.removeExtension(name).trim() + ".bak";
        System.out.println("Original file: "+file.getPath());
        System.out.println("Backup file: "+newFileName);
        //mp3file.save(newFileName);

        file.renameTo(new File(FilenameUtils.removeExtension(newFileName).trim() + ".mp3"));
        //FileUtils.deleteQuietly(file);
    }


    public String cleanString(String name){
        //System.out.println("Original name : " + name);
        if(name.contains(" - MassTamilan.com")){
            name = name.replaceAll(" - MassTamilan.com", "");
        }

        if(name.contains("MassTamilan.com")){
            name = name.replaceAll("MassTamilan.com", "");
        }

        if(name.contains("-")){
            name= name.replaceAll("-", " ");
        }

        //System.out.println("Replaced : " + name);
        return name;
    }
}
