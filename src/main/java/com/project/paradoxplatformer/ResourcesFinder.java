package com.project.paradoxplatformer;

import java.io.InputStream;
import java.lang.annotation.Native;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


import java.util.Objects;

public class ResourcesFinder{

    private static final List<String> fxmlFiles = List.of(
        
    );

    private static final List<String> imagesStreams = List.of(
        
    );

    private static final String NUMBER_PREFIX = "_";
    private static final String EXTENSION = ".png"; 
    private static final String IMAGE_DIR = "images/";

    public static List<URL> FXMLfiles() {
        return fxmlFiles.stream().map(ResourcesFinder.class::getResource).toList();
        
    }

    public static Map<String, InputStream> allImages() {
        
        return null;
                
    }

    public static URL getURL(String filePath) {
        try {
            System.out.println(filePath);
            return Optional.of(MainApplication.class.getResource(filePath))
                .filter(Objects::nonNull)
                .orElseThrow(() -> new IllegalArgumentException("Resource does not exists"));    
        } catch (Exception e) {
            System.out.println("RESOURCEEEE not found " + e.getMessage());
        }
        return null;
        
        
    }
}
