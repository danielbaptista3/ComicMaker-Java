package org.comicteam.exceptions;

public class DescriptorNotFoundException extends Exception {
    public DescriptorNotFoundException() {
        super("descriptor.xml is not found");
    }
}
