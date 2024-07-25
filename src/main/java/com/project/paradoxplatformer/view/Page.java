package com.project.paradoxplatformer.view;

public interface Page<T> {
    void create(T param) throws Exception;

    static Page<String> defaultPage() {
        return new Page<String>() {

            @Override
            public void create(String param) throws Exception {
                System.out.println("Blank Page, entered in Console Mode");
            }
            
        };
    }
}
