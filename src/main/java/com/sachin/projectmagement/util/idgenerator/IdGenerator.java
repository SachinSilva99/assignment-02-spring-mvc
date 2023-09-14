package com.sachin.projectmagement.util.idgenerator;

public interface IdGenerator {
    final String VALID_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public String generateRandomID(int length);
}
