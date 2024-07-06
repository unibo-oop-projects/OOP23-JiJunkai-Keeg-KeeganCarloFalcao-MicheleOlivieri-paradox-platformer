package com.project.paradoxplatformer;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.Objects;

public class ResourcesFinder {

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

    public static URL getURL(String image) {
        try {
            System.out.println(image);
            return Optional.of(MainApplication.class.getResource(image))
                .filter(Objects::nonNull)
                .orElseThrow(() -> new IllegalArgumentException("Resource does not exists"));    
        } catch (Exception e) {
            System.out.println("RESOURCEEEE");
        }
        return null;
        
        
    }
}
