package org.comicteam.exceptions;

public class InvalidDescriptorException extends Exception {
    public InvalidDescriptorException() {
        super("descriptor.xml is invalid");
    }
}
